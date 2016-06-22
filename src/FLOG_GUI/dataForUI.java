
package FLOG_GUI;

import java.util.HashMap;

/**
 *
 * @author Dushan Galappaththi
 */
public class dataForUI 
{
    public static PlayerData[] PdArray;
    public static String[] letters;
    public dataForUI() 
    {
        //To be Used to call data only
    }
    
    public dataForUI(int playerCount) //Calling this constructor is a must, But only once
    {
        PdArray = new PlayerData[playerCount];
        letters = new String[12];
    }

    public static PlayerData[] getPdArray() {
        return PdArray;
    }

    public static String[] getLetters() {
        return letters;
    }
    
    
    
}
