/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

import org.junit.Assert;
import org.junit.Test;
import FLOG_GUI.DataForUI.*;
import FLOG_GUI.PlayerData;
/**
 *
 * @author DILSHAN FERNANDO
 */
public class DataForUITests {
    
    @Test
    public void TestPlayerSort() {
        PlayerData player1=new PlayerData(1, "P1", 50, "A", "B");
        PlayerData player2=new PlayerData(2, "P2", 25, "Q", "W");
        PlayerData[] players={player1,player2};
        PlayerData[] Sortedplayers=DataForUI.sortPlayerArrayByScore(players);
        
        PlayerData[] PreSortedplayers={player1,player2};
        
        Assert.assertArrayEquals(Sortedplayers, PreSortedplayers);
    }
    
      
    
    
}
