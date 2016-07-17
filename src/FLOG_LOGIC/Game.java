/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import java.util.*;

/**
 *
 * @author Pasindu Handles the whole game flow.
 */
public class Game {
    /** List of {@code Player} objects of the users online. */
    private ArrayList<Player> playerList = new ArrayList<Player>();
    /** Current round in progress. */
    private int currentRound = -1;
    /***/
    private HashMap<String, Integer> playerIndexMap = 
                                                new HashMap<String, Integer>();
    
    /** 
     * Add players to the game. 
     * 
     * @param playerName name of the player that should be added.
     */
    public void addPlayer(String playerName) {
        Player player = new Player();
        player.setName(playerName);
        playerIndexMap.put(playerName, playerList.size());
        player.setListIndex(playerList.size());
        playerList.add(player);
    }

    public int getIndexByPlayerName(String name) {
        if (playerIndexMap.get(name) != null) {
            return playerIndexMap.get(name);
        } else {
            return -1;
        }
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void calculateRoundScores(int round) {
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).getPlayerRound(round).calculateScore();
        }
    }

    public void processRoundScores(final int round) {
        ArrayList<Player> tmpPlayerList = playerList;
        Collections.sort(tmpPlayerList, new Comparator<Player>() {
            @Override
            public int compare(Player s1, Player s2) {
                int score1 = s1.getPlayerRound(round).getScore();
                int score2 = s2.getPlayerRound(round).getScore();
                int scoreCompare = Integer.compare(score1, score2);
                if (scoreCompare == 0) {
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

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public Player getPlayerfromName(String name) {
        int userIndex = getIndexByPlayerName(name);
        return getPlayerList().get(userIndex);
    }

    public PlayerRound getPlayerRoundForRound(String name, int round) {
        int userIndex = getIndexByPlayerName(name);
        return getPlayerList().get(userIndex).getPlayerRound(round);
    }
}
