/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

import static FLOG_GUI.GameScreen.panelPlaying;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Dushan Galappaththi
 */
public class ControllerGamePlay 
{
    PanelGamePlay panelPlaying;
    GameScreen gameScreen;
    int mouseX=0;
    int mouseY=0;
    char[] letterArr = new char[12];
    
    
    
    public ControllerGamePlay(PanelGamePlay panelPlaying, GameScreen gameScreen) {
        this.panelPlaying = panelPlaying;
        this.gameScreen = gameScreen;
        dataForUI data = new dataForUI();
        initializeGamePlayListeners();
        setPlayerInfo("Snake Eyes",999,5435,4);
        panelPlaying.drawOpponents(data.getPdArray());
    }
    
    public void setPlayerInfo(String playerName, int Rank, int score, int round)
    {
        panelPlaying.drawPlayerName(playerName);
        panelPlaying.drawPlayerPosition(Rank);
        panelPlaying.drawPlayerScore(score);
        panelPlaying.drawRoundNumber(round);
     
    }
    
    private void submitClick(String ans)
    {
             
    }
    
     private void generateClick() 
     {
         
     }
        
    
     private void vowelsClick()
     {
         
     
     }
     
     private void consonentsClick()
     {
         
     }
     
     
    
    
    
    
    
    private void initializeGamePlayListeners()
    {
        final int _submit=15;
        final int _generate=14;
        final int _vowels=12;
        final int _consonent=13;
        final int _answer =16;
        final int _settings =0;
        final int _disconnect=1;
        final JTextField txt = (JTextField)panelPlaying.getCompBottom(_answer);
              
        //ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_home_h.png"));
          //  btnHome.setIcon(imgIcon);
        panelPlaying.getCompTop(0).addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                
              gameScreen.moveScreen(e.getXOnScreen(), e.getYOnScreen(),mouseX,mouseY);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                
            }
        });
        
        panelPlaying.getCompTop(0).addMouseListener(new MouseListener() {
           

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
              
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
            
            }
        });
        
        panelPlaying.getCompBottom(_submit).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) 
            {
                //System.out.println("[I] Submit was clicked | value on textbox : " + txt.getText().toString());
                submitClick(txt.getText().toString());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
              
            }

            @Override
            public void mouseExited(MouseEvent e) {
            
            }

        } );
        
        panelPlaying.getCompBottom(_generate).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                generateClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {
               
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
               
            }
        });
        
        panelPlaying.getCompBottom(_vowels).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                vowelsClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_vowels_c.png"));
                panelPlaying.setIcon_Vowels(imgIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_vowels_n.png"));
                panelPlaying.setIcon_Vowels(imgIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
               ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_vowels_n.png"));
                panelPlaying.setIcon_Vowels(imgIcon);
            }
        });
        
        panelPlaying.getCompBottom(_consonent).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                consonentsClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {
               
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
               
            }
        });
        
        panelPlaying.getCompTopBorder(1).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               gameScreen.changeScreen("MainMenu", null);
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
               
            }
        });
        
    }

   
    
    

    
    

    
    
    
}
