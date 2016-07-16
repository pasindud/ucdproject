/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import FLOG_GUI.SelectMultiPlayer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Pasindu
 */
public class Server {
    private static String channelName = "";
    private Multiplayer multiplayer;
    private String serverQueueName = "";
    private ArrayList<String> playerNames = new ArrayList<String>();
    // Consumer producer client
    Catcher serverCatcher = new Catcher();
    Thrower serverThrower = new Thrower();
    private boolean isServerStart = false;
    private Game game = null;
    
    public Server(String channelName){
        this.channelName = channelName;
        multiplayer = new Multiplayer();
        serverThrower.addThrowListener(serverCatcher);
        this.serverQueueName = multiplayer.getServerQueue(channelName);
    }
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(100);
    public void start(){
        if (isServerStart) {
            System.err.println("Server is already started");
            return;
        }
        game = new Game();
        Runnable task = () -> {
         List<String> messages = multiplayer.readQueue(serverQueueName);
            for (String message : messages) {
                decodeServerMessage(message);
                System.err.println("[GameServer][User:" + serverQueueName + "] M - " + message);
            }
        };
        long delay = 0;
        long intervel = 2;
        executor.scheduleWithFixedDelay(task, delay, intervel, TimeUnit.SECONDS);
    
        /* Thread backgroundServerQueueCheck =  new CheckQueueThread(serverQueueName, serverThrower);
        backgroundServerQueueCheck.start();*/
        isServerStart = true;
    }
    
    public void startGame(){
        // Format - 201 startgame <player names>
        // TODO player names should not have spaces.
        String strPlagerNames = String.join(",", playerNames);
        strPlagerNames = strPlagerNames.replaceFirst("^,", "");
        String message = "201 startgame " + strPlagerNames;
        multiplayer.broadcast(channelName, playerNames, message);
        game.startNewRound();
    }
    
    /**
     * Listens to messages thrown by checkQueueThread.
     */
    private class Catcher implements ThrowListener {
        @Override
        public void Catch(String message) {
            System.out.println("Server:caught " + message);
             decodeServerMessage(message);
        }
    }
    
    /**
     * Decode the message received by the server.
     */
    private synchronized void decodeServerMessage(String message){
        System.out.println("Decoding message to server message - [" + message + "]");
        String[] segments = message.split(" ");
        if (segments.length < 2) {
            return;
        }
        String code = segments[0];
        String content = segments[1];
        String name = "";
        Integer round;
        switch(code) {
            // New user joined format - 100 <player name>
            case "100": 
                String playerName = content;
                // setServerStatus("User " + content + " joined ");
                String playerQueue = multiplayer.getClientQueue(channelName, playerName);
                
                // Message to acknowledge that the server received the message 
                String clientMessage = "200 ackJoinServer";
                multiplayer.publishToQueue(playerQueue, clientMessage);
                name = content.trim();
                playerNames.add(name);
                game.addPlayer(name);
                break;
            // User start .   
            case "102":
                
                break;
            case "105":
                // Format - 104 letters <player name> a,s,d,g,e,q,q,r,t
                String letters = segments[2];
                letters = letters.replaceFirst("^,", "");
                String[] lettersArray = letters.split(",");
                break;
            case "304": // Initial Letters for next round.
                name = segments[2];
                round = Integer.parseInt(segments[3]);
                String firstLetter = segments[4];
                String secondLetter = segments[5];
                int userIndex = game.getIndexByPlayerName(name);
                String[] initialLetters = {firstLetter, secondLetter};
                game.getPlayerRoundForRound(name, round).setIntialLetters(initialLetters);
                break;
            case "106": // Use has finished the message.
                if (segments.length < 8) {
                    System.err.println("Game round end message format invalid Message " + message);
                    return;
                }
                handleEndRound(segments);
                break;
            default:
                System.err.println("Found nothing");
                break;
        }
    }
    int numberOfUsersFinishedRound = 0;
    public void handleEndRound(String[] segments) {
        String name = segments[2];
        int round = Integer.parseInt(segments[3]);
        String[] initialLetters = segments[4].split(",");
        String[] otherLetters = segments[5].split(",");
        String completedTime = segments[6];
        boolean isAutoGenUsed = Boolean.valueOf(segments[7]);
        String word = "";
        if (segments.length == 9) {
            word = segments[8];
        }
        
        PlayerRound currentRound = game.getPlayerRoundForRound(name, round);
        
        currentRound.setWord(new WordElement(word));
        currentRound.setIsWordSearchUsed(isAutoGenUsed);
        currentRound.setOtherLetters(otherLetters);
        currentRound.setIntialLetters(initialLetters);
        currentRound.calculateScore();
        
        ++numberOfUsersFinishedRound;
        int totalScore = game.getPlayerfromName(name).getNowTotalScore();
        int score = currentRound.getScore();
        
        
        // Example - 107 roundScore <player name> <round number>  <score> <totalScore>
        //           107 roundScore pasindu 1 10 100
        String message = "204 roundScore" + " " + name + " " + round + " " + score + " " +  totalScore;
        
        multiplayer.broadcast(channelName, playerNames, message);
        
        if (playerNames.size() == numberOfUsersFinishedRound) {
            numberOfUsersFinishedRound = 0;
            // All users sent the round details.
        } else {
        
        }
    }
}
