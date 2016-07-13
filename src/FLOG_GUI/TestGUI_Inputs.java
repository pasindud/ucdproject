
package FLOG_GUI;

/**
 *
 * @author Dushan Galappaththi
 */
public class TestGUI_Inputs 
{
    DataForUI data;
    public TestGUI_Inputs() 
    {
        data = new DataForUI(13); // Set Number of Players here
        listOfPlayers();
        setLetters();
    }

    private void listOfPlayers()
    {
        //Set Player Data here
        data.PdArray[0] = new PlayerData(1, "Yankee", 9999, "A", "B");
        data.PdArray[1] = new PlayerData(2, "Charlie", 8888, "C", "D");
        data.PdArray[2] = new PlayerData(3, "Oscar", 7777, "E", "F");
        data.PdArray[3] = new PlayerData(4, "Lima", 6666, "G", "H");
        data.PdArray[4] = new PlayerData(5, "Adaala na", 5555, "I", "J");
        data.PdArray[5] = new PlayerData(6, "Zulu", 4444, "K", "L");
        data.PdArray[6] = new PlayerData(7, "Bravo", 3333, "M", "N");
        data.PdArray[7] = new PlayerData(8, "Delta", 2222, "O", "P");
        data.PdArray[8] = new PlayerData(999, "Foxtrot", 1111, "Q", "R");
        data.PdArray[9] = new PlayerData(10, "Echo", 0000, "S", "T");
        data.PdArray[10] = new PlayerData(66, "Echo", 0000, "U", "V");
        data.PdArray[11] = new PlayerData(99, "Echo", 0000, "W", "X");
        data.PdArray[12] = new PlayerData(100, "Echo", 0000, "Y", "Z");
        
    }

    private void setLetters() 
    {
        data.letters[0] = "A";
        data.letters[1] = "B";
        data.letters[2] = "G";
        data.letters[3] = "T";
        data.letters[4] = "K";
        data.letters[5] = "A";
        data.letters[6] = "I";
        data.letters[7] = "F";
        data.letters[8] = "L";
        data.letters[9] = "E";
        data.letters[10] = "U";
        data.letters[11] = "Z";
        
        
           
    }
        
    
}
