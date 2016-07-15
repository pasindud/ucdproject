/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import java.util.Arrays;

/**
 *
 * @author Pasindu
 * Contains all the information about the player and information about the
 * rounds played.
 */
public class Player {
    private String name;
    private PlayerRound[] playerRounds = new PlayerRound[6]; //[dushan] : made this 6 instead of 5
    private int totalScore;
    private boolean isKicked = false;
    private int listIndex;

    public boolean getIsKicked(){
        return isKicked;
    }
    public int getListIndex() {
        return listIndex;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }
    
    public void kickPlayer(){
        this.isKicked = true;
    }
    
    Player(){
        for (int i = 0; i < 5; i++) {
           playerRounds[i] = new PlayerRound();
        }
    }
    
    public void setRoundWord(int round, String word){
        WordElement wordElement = new WordElement(word);
        playerRounds[round].setWord(wordElement);
    }
    
    public void setPlayerRound(int round, PlayerRound playerRound){
        playerRounds[round] = playerRound;
    }
     
    public PlayerRound getPlayerRound(int round){
        return playerRounds[round];
    } 
    
    public WordElement getRoundWord(int round){
        PlayerRound playerRound = playerRounds[round];
        return playerRound.getWord();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
   
}
