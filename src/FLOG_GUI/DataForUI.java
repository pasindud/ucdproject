
package FLOG_GUI;

import FLOG_LOGIC.Game;
import FLOG_LOGIC.Player;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dushan Galappaththi
 */
public class DataForUI {
  public static PlayerData[] PdArray;
  public static PlayerData[] sortedPdArrayByScore;
  public static String[] letters;
  public static Game game;
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
  public static String currentUsername ="dc";
  
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

  public static String firstLetter;
  public static String secondLetter;

  public DataForUI() {
    //To be Used to call data only

  }

  public DataForUI(int playerCount) //Calling this constructor is a must, But only once
       {

    //PdArray = new PlayerData[playerCount];
    //getPlayerList();
    letters = new String[12];
    RoundNum = 1;
    try {
      LCD =
          Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("src/fonts/LCD.ttf")))
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

  public static String[] getLetters() {
    return letters;
  }

  public static void getPlayerList() {
    playerList = game.getPlayerList();
    parsePlayerList();
  }

  public static HashMap<String, Integer> scoresMap = new HashMap<String, Integer>();
  public int myTotalScore;
  public int myRank;
  //
  private static void parsePlayerList() {
    int count = 0;
    PdArray = new PlayerData[playerList.size()];
    for (Player p : playerList) {
      int total = p.getTotalScore();
      String name = p.getName();
      scoresMap.put(name, total);
      String[] initialLetters = p.getPlayerRound(RoundNum).getIntialLetters();
      System.err.println("ParseLettesr - " + initialLetters[0] + " - " + initialLetters[1]);
      PlayerData pd =
          new PlayerData(p.getListIndex(), name, total, initialLetters[0], initialLetters[1]);
      //change
      PdArray[count] = pd;
      count++;
    }
  }
  
  public static void preparePlayerArrayForUI()
    {
        sortedPdArrayByScore = sortPlayerArrayByScore(PdArray);
        
    }
  
    public static PlayerData[] sortPlayerArrayByScore(PlayerData[] playerDataArray)
    {
               
        //Bubble Sort
        int length = playerDataArray.length;
        PlayerData tempPlayerData = null;
        
        for(int i=0; i<length;i++)
        {
            for(int j=0;j<(length-i);j++)
            {
                if ((j + 1) < length) {
                    if (playerDataArray[j + 1].getScore() > playerDataArray[j].getScore()) {
                        /*tempPlayerData = playerDataArray[j-1];
                    playerDataArray[j-1] = playerDataArray[j];
                    playerDataArray[j]=tempPlayerData;
                         */

                        tempPlayerData = playerDataArray[j + 1];
                        playerDataArray[j + 1] = playerDataArray[j];
                        playerDataArray[j] = tempPlayerData;

                    }
                }
            }
        }
        
        for(int i=0;i<length;i++)
        {
            playerDataArray[i].setPosition(i+1);
        }
        
        return playerDataArray;
    }
}
