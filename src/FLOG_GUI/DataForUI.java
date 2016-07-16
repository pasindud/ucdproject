
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
public class DataForUI 
{
    public static PlayerData[] PdArray;
    public static String[] letters;
    public static Game game;
    public static ArrayList<Player> playerList;
    
    //Holds information of Player
    public static Player player;
    
    //LCD Font for timers
    public static Font LCD;
    
    //Limits of rounds per game
    public static int RoundLimit =5;
    
    //Current Round Number
    public static int RoundNum;
    
    //Set Round ready up time
    public static int RoundReadyUpTime = 15;
    
    //Set Round time
    public static int RoundTime =10;
    
    //Constants used to switch the cardlayout
    public static final String STR_GAMEPLAY = "GamePlay";
    public static final String STR_SETTINGS = "Settings";
    public static final String STR_ROUNDREADYUP = "RoundReadyUp";
    public static final String STR_MAINMENU = "MainMenu";
    public static final String SelectMultiplayer = "SelectMultiPlayer";
    public static final String STR_WINNER = "WinnerList";
      
    public static String firstLetter;
    public static String secondLetter;
    
    public DataForUI() 
    {
        //To be Used to call data only
       
    }
    
    public DataForUI(int playerCount) //Calling this constructor is a must, But only once
    {
        
        //PdArray = new PlayerData[playerCount];
        //getPlayerList();
        letters = new String[12];
        RoundNum=1;
        try {
            LCD = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("src/fonts/LCD.ttf"))).deriveFont(Font.PLAIN, 100);
        } catch (FontFormatException ex) {
            System.out.println(ex.getMessage().toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage().toString());
        }
    }
    
    public static void setPlayer(int rank, String name,int score)
    {
        player.setName(name);
        player.setListIndex(rank);
        player.setTotalScore(score);
    }

    public static PlayerData[] getPdArray() {
        //getPlayerList();
        return PdArray;
    }

    public static String[] getLetters() {
        return letters;
    }
    
    public static void getPlayerList()
    {
        playerList = game.getPlayerList();
        parsePlayerList();
    }
    
    
    // 
    private static void parsePlayerList()
    {
        int count=0;
        PdArray = new PlayerData[playerList.size()];
        for(Player p : playerList)
        {
            String[] initialLetters = p.getPlayerRound(RoundNum).getIntialLetters();
            System.err.println("ParseLettesr - " + initialLetters[0] + " - " + initialLetters[1]);
            PlayerData pd = new PlayerData(
                    p.getListIndex(),
                    p.getName(),
                    p.getTotalScore(), 
                    initialLetters[0], 
                    initialLetters[1]);
            //change
            PdArray[count] = pd;
            count++;
        }
    }
        
    
}
