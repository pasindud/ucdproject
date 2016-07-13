
package FLOG_GUI;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    
    //LCD Font for timers
    public static Font LCD;
    
    //Current Round Number
    public static int RoundNum;
    
    //Set Round ready up time
    public static int RoundReadyUpTime = 10;
    
    //Set Round time
    public static int RoundTime =10;
    
    //Constants used to switch the cardlayout
    public static final String STR_GAMEPLAY = "GamePlay";
    public static final String STR_SETTINGS = "Settings";
    public static final String STR_ROUNDREADYUP = "RoundReadyUp";
    public static final String STR_MAINMENU = "MainMenu";
      
    
    public DataForUI() 
    {
        //To be Used to call data only
       
    }
    
    public DataForUI(int playerCount) //Calling this constructor is a must, But only once
    {
        
        PdArray = new PlayerData[playerCount];
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

    public static PlayerData[] getPdArray() {
        return PdArray;
    }

    public static String[] getLetters() {
        return letters;
    }
        
    
}
