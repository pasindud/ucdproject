
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
public class dataForUI 
{
    public static PlayerData[] PdArray;
    public static String[] letters;
    public static Font LCD;
    public dataForUI() 
    {
        //To be Used to call data only
       
    }
    
    public dataForUI(int playerCount) //Calling this constructor is a must, But only once
    {
        PdArray = new PlayerData[playerCount];
        letters = new String[12];
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
