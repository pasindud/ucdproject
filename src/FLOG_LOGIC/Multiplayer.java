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

    
    /**
     * Setup API keys.
     */
    Multiplayer(){
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
    
}
