
package FLOG_GUI;

/**
 *
 * @author Dushan Galappaththi
 */
public class TestGUI_Inputs 
{
    dataForUI data;
    public TestGUI_Inputs() 
    {
        data = new dataForUI(10); // Set Number of Players here
        listOfPlayers();
        setLetters();
    }

    private void listOfPlayers()
    {
        //Set Player Data here
        data.PdArray[0] = new PlayerData(1, "yankee", 9999, "A", "O");
        data.PdArray[1] = new PlayerData(2, "charlie", 8888, "A", "O");
        data.PdArray[2] = new PlayerData(3, "oscar", 7777, "A", "O");
        data.PdArray[3] = new PlayerData(4, "lima", 6666, "A", "O");
        data.PdArray[4] = new PlayerData(5, "adaala na", 5555, "A", "O");
        data.PdArray[5] = new PlayerData(6, "zulu", 4444, "A", "O");
        data.PdArray[6] = new PlayerData(7, "bravo", 3333, "A", "O");
        data.PdArray[7] = new PlayerData(8, "delta", 2222, "A", "O");
        data.PdArray[8] = new PlayerData(9, "foxtrot", 1111, "A", "O");
        data.PdArray[9] = new PlayerData(10, "echo", 0000, "A", "O");
        
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
