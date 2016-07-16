/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import java.util.*;
/**
 *
 * @author Pasindu
 * Handles the whole game flow.
 */
public class Game {
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private int currentRound = -1;
    public void addPlayer(String playerName){
        Player player = new Player();
        player.setName(playerName);
        player.setListIndex(playerList.size());
        playerList.add(player);
    }
    
    public int getIndexByPlayerName(String name){
        for (int i = 0; i < playerList.size(); i++) {
            String currentName = playerList.get(i).getName();
            if (name.equals(currentName)) {
                return i;
            }
        }
        return -1;
    }
    
    public int getCurrentRound(){
        return currentRound;
    }
    
    public void startNewRound(){
        int NUMBER_OF_VOWELS = 6;
        int NUMBER_OF_CONSOTANTS = 6;
        
        String[] intialLetters = {"", ""};
        
        String[] otherLetters = new String[10]; 
        currentRound++;
        
        // TODO(pasindu) Refactor this code.
        for (int i = 0; i < playerList.size(); i++) {
            List<String> letters = Utils.getRadomLetters(
                                        NUMBER_OF_VOWELS, NUMBER_OF_CONSOTANTS);
            intialLetters[0] = letters.get(0);
            intialLetters[1] = letters.get(1);
            
            playerList.get(i).getPlayerRound(currentRound)
                    .setIntialLetters(intialLetters);
            
            int k = 0;
            for (int j = 2; j < letters.size(); j++) {
                otherLetters[k] = letters.get(j);
                k++;
            }
            
            playerList.get(i).getPlayerRound(currentRound)
                    .setOtherLetters(otherLetters);
        }
    }
    
    private void calculateRoundLetterValues(int round){
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).getPlayerRound(round).calculateWordLetterScores();
        }
    }
    
    public void calculateRoundScores(int round){
        calculateRoundLetterValues(round);
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).getPlayerRound(round).calculateScore();
        }
    }
    
    public void processRoundScores(final int round){
        ArrayList<Player> tmpPlayerList = playerList;
        Collections.sort(tmpPlayerList, new Comparator<Player>(){
            @Override
            public int compare(Player s1, Player s2) {
                int score1 = s1.getPlayerRound(round).getScore();
                int score2 = s2.getPlayerRound(round).getScore();
                int scoreCompare = Integer.compare(score1, score2);
                if (scoreCompare == 0){
                    int penalty1 = s1.getPlayerRound(round).penaltyPoints.getPenaltyPoints();
                    int penalty2 = s2.getPlayerRound(round).penaltyPoints.getPenaltyPoints();
                    int penaltyCompare = Integer.compare(penalty1, penalty2);
                    return penaltyCompare;
                } else {
                    return scoreCompare;
                }
            }
        });
        // playerList.get(tmpPlayerList.get(0).getListIndex()).kickPlayer();
    }
    
    public void runWordSearch(int round, int playerNumber){
        playerList.get(playerNumber).getPlayerRound(round).getWordSearch();
    }
    
    public void setPlayerRoundWord(int round, int playerNumber, String word) {
        playerList.get(playerNumber).setRoundWord(round, word);  
    }
    
    public String getPlayerRoundWord(int round, int playerNumber) {
        Player currentPlayer =  playerList.get(playerNumber);   
        return currentPlayer.getRoundWord(round).getWord();
    }
 
    public Player getPlayer(int playerNumber) {
       return playerList.get(playerNumber);
    }
    
    public void setPlayer(int playerNumber, Player player) {
       playerList.set(playerNumber, player);
    }
    
    public ArrayList<Player> getPlayerList()
    {
        return playerList;
    }
    
    public Player getPlayerfromName(String name){
        int userIndex = getIndexByPlayerName(name);
        return getPlayerList().get(userIndex);
    }
    
    public PlayerRound getPlayerRoundForRound(String name, int round){
        int userIndex = getIndexByPlayerName(name);
        return getPlayerList().get(userIndex).getPlayerRound(round);
    }    
}
