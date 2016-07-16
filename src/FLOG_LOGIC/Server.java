/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import FLOG_GUI.SelectMultiPlayer;
import java.util.ArrayList;

/**
 *
 * @author Pasindu
 */
public class Server {
    private static String channelName = "";
    private Multiplayer multiplayer;
    private String serverQueueName = "";
    private ArrayList<String> playerNames = new ArrayList<String>();
    Catcher serverCatcher = new Catcher();
    Thrower serverThrower = new Thrower();
    private boolean isServerStart = false;
    private Game game = null;
    // Consumer producer client
    public Server(String channelName){
        
        this.channelName = channelName;
        multiplayer = new Multiplayer();
        serverThrower.addThrowListener(serverCatcher);
        this.serverQueueName = multiplayer.getServerQueue(channelName);
    }
    
    public void start(){
        if (isServerStart) {
            System.err.println("Server is already started");
            return;
        }
        game = new Game();
        Thread backgroundServerQueueCheck =  new CheckQueueThread(serverQueueName, serverThrower);
        backgroundServerQueueCheck.start();
        isServerStart = true;
    }
    
    public void startGame(){
        // Format - 201 startgame <player names>
        // TODO player names should not have spaces.
        String strPlagerNames = String.join(",", playerNames);
        strPlagerNames = strPlagerNames.replaceFirst("^,", "");
        String message = "201 startgame " + strPlagerNames;
        multiplayer.broadcast(channelName, playerNames, message);
    }
    
    /**
     * Listens to messages thrown by checkQueueThread.
     */
    private class Catcher implements ThrowListener {
        @Override
        public void Catch(String message) {
            System.out.println("Server caught " + message);
             decodeServerMessage(message);
        }
    }
    
    /**
     * Decode the message received by the server.
     */
    private synchronized void decodeServerMessage(String message){
        System.out.println("Decoding message to server message - " + message);
        String[] segments = message.split(" ");
        if (segments.length < 2) {
            return;
        }
        String code = segments[0];
        String content = segments[1];
        switch(code) {
            // New user joined format - 100 <player name>
            case "100": 
                String playerName = content;
                // setServerStatus("User " + content + " joined ");
                String playerQueue = multiplayer.getClientQueue(channelName, playerName);
                
                // Message to acknowledge that the server received the message 
                String clientMessage = "200 ackJoinServer";
                multiplayer.publishToQueue(playerQueue, clientMessage);
                String name = content.trim();
                playerNames.add(name);
                game.addPlayer(name);
                break;
            // User start the gui- ack game start .   
            case "103":
                System.out.println(content);
                break;
            case "104":
                System.out.println(content);
                break;
            case "105":
                // Format - 104 letters <player name> a,s,d,g,e,q,q,r,t
                String letters = segments[2];
                letters = letters.replaceFirst("^,", "");
                String[] lettersArray = letters.split(",");
                break;
        }
        
    }
}
