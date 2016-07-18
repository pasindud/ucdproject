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
                   player1.letterArry[0]="P1_L";
                   player1.WordArry[0]="P1_W";
                   
        PlayerData player2=new PlayerData(2, "P2", 25, "Q", "W");
                   player1.letterArry[0]="P2_L";
                   player1.WordArry[0]="P2_W";
                   
        PlayerData player3=new PlayerData(3, "P3", 70, "Q", "W");
                   player1.letterArry[0]="P3_L";
                   player1.WordArry[0]="P3_W";
                   
        PlayerData[] players={player1,player2,player3};
        PlayerData[] Sortedplayers=DataForUI.sortPlayerArrayByScore(players);
        
        PlayerData[] PreSortedplayers={player3,player1,player2};
        
        for(int i=0;i<players.length;i++)
        {
            System.out.println(players[i].WordArry[1]+" "+players[i].letterArry);
        }
        Assert.assertArrayEquals(Sortedplayers, PreSortedplayers);
    }
    
      
    
    
}
