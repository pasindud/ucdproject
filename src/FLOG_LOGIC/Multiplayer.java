/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import com.shephertz.app42.paas.sdk.java.user.User;  
import com.shephertz.app42.paas.sdk.java.user.User.Profile;  
import com.shephertz.app42.paas.sdk.java.user.UserService; 

import com.shephertz.app42.paas.sdk.java.App42API;  
import com.shephertz.app42.paas.sdk.java.App42Log;
import com.shephertz.app42.paas.sdk.java.App42Response;
import com.shephertz.app42.paas.sdk.java.message.Queue;
import com.shephertz.app42.paas.sdk.java.message.QueueService;
import com.shephertz.app42.paas.sdk.java.storage.Query;  
import com.shephertz.app42.paas.sdk.java.storage.QueryBuilder;  
import com.shephertz.app42.paas.sdk.java.storage.Storage;  
import com.shephertz.app42.paas.sdk.java.storage.StorageService;  
import com.shephertz.app42.paas.sdk.java.storage.QueryBuilder.Operator; 
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * @author Pasindu
 */
public class Multiplayer {
    /* shephertz.com API key. */
    private final String API_KEY = "c3fe63a386934d7ef46c80c4c14e0fc824894022523a1039bd2ee7cb67095d34";
    /* shephertz.com SECRET_KEY key. */
    private final String SECRET_KEY = "adeab7141118da5a7613a227370be5fc2685e7028080c28cfec3e452f2c1a8c4";
    /* Flog no sql database name. */
    private final String DB_NAME = "flogdb";
    /* Game users online status collection name. */
    private final String USER_ONLINE_COLLECTION_NAME = "usersonline";
    /* Time from  which the users where online. */
    private final long USER_ONLINE_TIME_FROM_NOW = 300000;
    /* Json field for username. */
    private final String JSON_USERNAME = "username";
    /* Json field for the last time the user was online. */
    private final String JSON_LAST_ONLINE = "last_online";
    /* Epoch time also know as unix time. */
    private long epoch;

    private boolean DEBUG = false;
    /**
     * Setup API keys.
     */
    public Multiplayer(){
        App42API.initialize(API_KEY, SECRET_KEY);
        // Used for debugging only.
        // App42Log.setDebug(true);
    }
    
    /**
     * Get the users currently online.
     * @return List of users currently online. 
     */
    public ArrayList<String> getOnlineUsers() throws JSONException{
        List<String> nowOnlineUsers = new ArrayList<>();
        try {
            epoch = System.currentTimeMillis() - USER_ONLINE_TIME_FROM_NOW;
            epoch = (epoch / 1000);

            Query query = QueryBuilder.build(JSON_LAST_ONLINE, epoch, 
                    Operator.GREATER_THAN);
            StorageService storageService = App42API.buildStorageService();   
            Storage storage;  
            storage = storageService.findDocumentsByQuery(DB_NAME, 
                    USER_ONLINE_COLLECTION_NAME, query);

            ArrayList<Storage.JSONDocument> jsonDocList = 
                    storage.getJsonDocList();              
            for(int i=0; i<jsonDocList.size(); i++) {  
                String json = jsonDocList.get(i).getJsonDoc();
                JSONObject jsonoutput = new JSONObject(json);
                nowOnlineUsers.add((String)jsonoutput.get(JSON_USERNAME));
            }
        } catch(Exception e){
            System.out.println("Getonlineusers - exception message - " 
                    + e.getMessage());
        }
        return (ArrayList<String>) nowOnlineUsers;
    }
    
    /**
    * This would be called by a background method every 5 mins 
    * to update the last time the user was online.
    * @param username of the user to the set the latest online time.
    */
    public void setOnline(String username) {
        try {
            epoch = System.currentTimeMillis();
            epoch = (epoch / 1000);

            String dbName = DB_NAME;  
            String collectionName = USER_ONLINE_COLLECTION_NAME;   
            JSONObject json = new JSONObject();  
            json.put(JSON_LAST_ONLINE, epoch);  
            json.put(JSON_USERNAME, username);   
            StorageService storageService = App42API.buildStorageService();   
            Storage storage = storageService.saveOrUpdateDocumentByKeyValue(
                    dbName,
                    collectionName, 
                    JSON_USERNAME, 
                    username,
                    json);
            
            System.out.println("User " + username + " online time updated.");
        } catch(org.json.JSONException e){
            System.out.println("Set online JSONException - " + e.getMessage());
        } catch (Exception e){
            System.out.println("Set online error - " + e.getMessage());
        }
    }
 
    /** 
     * Authenticate the user credentials.
     * @param username of the user logging in.
     * @param password of the user logging in. 
     * @return Whether the authentication credentials are correct or not.
     */
    public boolean login(String username, String password){
        UserService userService = App42API.buildUserService();
        try {
            User user = userService.authenticate(username, password);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    /**
     * Register new users.
     * @param username of the user been registered.
     * @param email of the user been registered.
     * @param password of the user been registered.
     * @return Whether the user was registered or not.
     */
    public boolean registerUser(String username, String email, String password){
        boolean done = false;
        try {
            UserService userService = App42API.buildUserService();
            Profile profile = new User().new Profile();
            userService.createUser(username, password, email);
            return true;   
        } catch(Exception e){
            return false;
        }
    }
    
    /**
     * Create a queue for the given name.
     */
    private boolean createQueue(String name){
        String queueDescription = "a";
        QueueService queueService = App42API.buildQueueService();  
        try{ 
        Queue queue = queueService.createPullQueue(name, queueDescription); 
        String jsonResponse = queue.toString();   
        System.out.println("Log createQueue - " + queue.toString());
        } catch(Exception e){
            App42Response app42response = queueService.purgePullQueue(name);   
            // No internet
            // Queue has already been created.
        }
        return true;
    }

    /**
     *
     * @param channelName the name of the channel to create.
     */
    public boolean createServer(String channelName){
        createQueue(getServerQueue(channelName));
        return true;
    }
    
    /**
     * Builds the client queue name.
     */
    public String getClientQueue(String channelName, String playerName){
        return channelName + "_" + playerName;
    }
    
    /**
     * Make the name of the channel server queue.
     * TODO fix issue of non server user pull messages from the server queue.
     */
    public String getServerQueue(String channelName){
        return channelName + "_server";
    }
    
    /**
     * This is joining a new non server user.
     */
    public void joinNewPlayer(String playerName, String channelName){
        try {
        // First create a player queue.
        // Typical player queue name is - playername_channel
        String queueName = getClientQueue(channelName, playerName);
        createQueue(queueName);
        
        // Send the message to the channel queue.
        
        // Message format to put in the server queue - 100 <player name>
        String message = "100 " + playerName;
        publishToQueue(getServerQueue(channelName), message);
        
        } catch(Exception e){
            // No internet 
            // Queue already created.
        }
    }
    
    /**
     * This is starting a new game
     */
    public void startNewgame( String channelName){
        try {
        // Send the message to the channel queue.
        
        // Message format to put in the server queue - 100 <player name>
        String message = "101 Start";
        publishToQueue(getServerQueue(channelName), message);
        
        } catch(Exception e){
            // No internet 
            // Queue already created.
        }
    }
    /**
     * Join the new user.
     * 
     * Start the game
     *  Distribute letters
     *  Subsitute letters
     *  End the round
     *  Calculate the game score
     *  Run the penalty
     *  Start the next round again
     * End the game.
     * 
     */
    
    public boolean publishToQueue(String queueName, String message){
        long  expiryTime = 10000;  
        QueueService queueService = App42API.buildQueueService();   
        Queue queue = queueService.sendMessage(queueName, message, expiryTime);
        
        if (DEBUG) {
            System.out.println(queue.toString());
        }
        return true;
    }
    
    public List<String> readQueue(String queueName){
        if (DEBUG) {
            System.out.println("Reading queue - " + queueName);
        }
        List<String> messages = new ArrayList<String>();
        try{
        long  receiveTimeOut = 10000; 
        QueueService queueService = App42API.buildQueueService();   
        Queue queue = queueService.getMessages(queueName, receiveTimeOut);    
        ArrayList<Queue.Message> messageList = queue.getMessageList();    
        for(Queue.Message message : messageList)    
        {   
            messages.add(message.getPayLoad());
            if (DEBUG) {
                System.out.println("correlationId is " + message.getCorrelationId());    
                System.out.println("messageId is " + message.getMessageId());    
                System.out.println("payLoad is " + message.getPayLoad());    
            }      
        }
        } catch (Exception e){
            // No internet.
            // Queue has not been created.
        }
        return messages;
    }
    
    public void broadcast(String channelName,
            ArrayList<String> playerNames, String message){
        for (String player : playerNames) {
            String playerQueueName = getClientQueue(channelName, player);
            publishToQueue(playerQueueName, message);
        }
    }
}
