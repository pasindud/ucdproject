package FLOG_GUI;

import FLOG_LOGIC.Game;
import FLOG_LOGIC.Player;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Dushan Galappaththi
 */
public class DataForUI {

    public static PlayerData[] PdArray;
    public static PlayerData[] sortedPdArrayByScore;
    public static String[] letters;
    public static Game game;
    public static boolean isChatOpen = false;
    public static boolean isConnectedToServer = false;
    public static ArrayList<Player> playerList;

    //Holds information of Player
    public static Player player;

    //LCD Font for timers
    public static Font LCD;

    //Limits of rounds per game
    public static int RoundLimit = 5;

    //Current Round Number
    public static int RoundNum;

    //Set Round ready up time
    public static int RoundReadyUpTime = 5;

    //Set Round time
    public static int RoundTime = 20;

    //Current Username
    public static String currentUsername = "dc";

    //Current channel name
    public static String currentChannel = "";

    //Constants used to switch the cardlayout
    public static final String STR_GAMEPLAY = "GamePlay";
    public static final String STR_SETTINGS = "Settings";
    public static final String STR_ROUNDREADYUP = "RoundReadyUp";
    public static final String STR_MAINMENU = "MainMenu";
    public static final String SelectMultiplayer = "SelectMultiPlayer";
    public static final String STR_WINNER = "WinnerList";
    public static final String STR_LOGIN = "Login";
    public static final String STR_REGISTER = "Register";
    public static final String STR_CREDITS = "Credits";
    public static final String STR_HELP = "Help";

    public static String firstLetter;
    public static String secondLetter;

    public static HashMap<String, Integer> scoresMap = new HashMap<String, Integer>();
    public static int myTotalScore;
    public static int myRank;
    public static boolean flagInitialParse = false;

    public DataForUI() {
        //To be Used to call data only
    }

    public DataForUI(int playerCount) //Calling this constructor is a must, But only once
    {
        letters = new String[12];
        RoundNum = 1;
        try {
            LCD
                    = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("LCD.ttf")))
                    .deriveFont(Font.PLAIN, 100);
        } catch (FontFormatException ex) {
            System.out.println(ex.getMessage().toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage().toString());
        }
    }

    public static void setPlayer(int rank, String name, int score) {
        player.setName(name);
        player.setListIndex(rank);
        player.setTotalScore(score);
    }

    public static PlayerData[] getPdArray() {
        getPlayerList();
        return PdArray;
    }

    public static PlayerData[] getSortedPdArrayByScore() {
        return sortedPdArrayByScore;
    }

    public static String[] getLetters() {
        return letters;
    }

    public static void getPlayerList() {
        playerList = game.getPlayerList();
        if (!flagInitialParse) {
            initialParsePlayerList();
            flagInitialParse = true;
        }
        parsePlayerList();

    }

    public static void parsePlayerList() {
        int count = 0;
        String letterArry[] = new String[7];
        String wordArry[] = new String[7];
        for (Player p : playerList) {
            int total = p.getTotalScore();
            String name = p.getName();
            scoresMap.put(name, total);
            String[] initialLetters = p.getPlayerRound(RoundNum).getIntialLetters();
            PlayerData pd = new PlayerData(p.getListIndex(), name, total, initialLetters[0], initialLetters[1]);

            pd.letterArry = PdArray[getIndexByName(name)].letterArry;
            pd.WordArry = PdArray[getIndexByName(name)].WordArry;

            PdArray[getIndexByName(name)] = pd;

            count++;

        }

    }

    public static void updatePdArray(String name, int totalScore, int round, String letters, String word) {
        PdArray[getIndexByName(name)].setScore(totalScore);
        PdArray[getIndexByName(name)].setLetterArry(letters, round);
        PdArray[getIndexByName(name)].setWordArry(word, round);
    }

    public static int getIndexByName(String name) {
        boolean flag = false;
        int count = 0;
        int index = 0;
        int rank = count;
        while ((!flag) && (count < PdArray.length)) {
            if (PdArray[count].name.equals(name)) {
                flag = true;
                index = count;
            }
            count++;
        }

        return index;
    }

    //
    private static void initialParsePlayerList() {
        int count = 0;
        PdArray = new PlayerData[playerList.size()];
        for (Player p : playerList) {
            int total = p.getTotalScore();
            String name = p.getName();
            String[] initialLetters = p.getPlayerRound(RoundNum).getIntialLetters();
            PlayerData pd = new PlayerData(p.getListIndex(), name, total, initialLetters[0], initialLetters[1]);
            PdArray[count] = pd;
            count++;
        }

    }

    public static void preparePlayerArrayForUI() {
        sortedPdArrayByScore = sortPlayerArrayByScore(PdArray);
        myRank = findPlayerRankByName(currentUsername);
        myTotalScore = findPlayerScoreByName(currentUsername);
    }

    public static PlayerData[] sortPlayerArrayByScore(PlayerData[] playerDataArray) {

        //Bubble Sort in descending order
        int length = playerDataArray.length;
        PlayerData tempPlayerData = null;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < (length - i); j++) {
                if ((j + 1) < length) {
                    if (playerDataArray[j + 1].getScore() > playerDataArray[j].getScore()) {
                        tempPlayerData = playerDataArray[j + 1];
                        playerDataArray[j + 1] = playerDataArray[j];
                        playerDataArray[j] = tempPlayerData;
                    }
                }
            }
        }

        for (int i = 0; i < length; i++) {
            playerDataArray[i].setPosition(i + 1);
        }

        return playerDataArray;
    }

    private static int findPlayerRankByName(String name) {
        boolean flag = false;
        int count = 0;
        int rank = count;
        while ((!flag) && (count < sortedPdArrayByScore.length)) {
            if (sortedPdArrayByScore[count].name.equals(name)) {
                flag = true;
                rank = sortedPdArrayByScore[count].getPosition();
            }
            count++;
        }
        return rank;
    }

    private static int findPlayerScoreByName(String name) {
        boolean flag = false;
        int count = 0;
        int score = 0;
        while ((!flag) && (count < sortedPdArrayByScore.length)) {
            if (sortedPdArrayByScore[count].name.equals(name)) {
                flag = true;
                score = sortedPdArrayByScore[count].getScore();
            }
            count++;
        }
        return score;
    }

}
