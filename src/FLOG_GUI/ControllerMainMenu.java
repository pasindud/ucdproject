/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Dushan Galappaththi
 */
public class ControllerMainMenu 
{
    int mouseX=0;
    int mouseY=0;
    PanelMainMenu panelMainMenu;
    GameScreen gameScreen;

    public ControllerMainMenu(PanelMainMenu panelMainMenu, GameScreen gameScreen) {
        this.panelMainMenu = panelMainMenu;
        this.gameScreen = gameScreen;
        initializeMainMenuListeners();
    }
    
    private void startClick()
    {
      gameScreen.changeScreen("PlayingScreen", null);
        
    }
    
    private void settingsClick()
    {
    
    }
    
    private void helpClick()
    {
    
    }
    
    private void aboutClick()
    {
    
    }
    private void exitClick()
    {
        System.exit(0);
    
    }
    
    private void initializeMainMenuListeners() 
    {
       final int _start = 0;
       final int _setting = 2;
       final int _help = 3;
       final int _about = 4;
       final int _exit = 1;
       final int _TopBorder=5; 
        panelMainMenu.getCompCon(_TopBorder).addMouseListener(new MouseListener() {

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
        panelMainMenu.getCompCon(_TopBorder).addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                gameScreen.moveScreen(e.getXOnScreen(), e.getYOnScreen(),mouseX,mouseY);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
               
            }
        });
        
        panelMainMenu.getComponent(_start).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                startClick();
              
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
        
        panelMainMenu.getComponent(_setting).addMouseListener(new MouseListener() {

           @Override
           public void mouseClicked(MouseEvent e) {
               settingsClick();
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
        
        panelMainMenu.getComponent(_help).addMouseListener(new MouseListener() {

           @Override
           public void mouseClicked(MouseEvent e) {
               helpClick();
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
        
        panelMainMenu.getComponent(_about).addMouseListener(new MouseListener() {

           @Override
           public void mouseClicked(MouseEvent e) {
               aboutClick();
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
        
        panelMainMenu.getComponent(_exit).addMouseListener(new MouseListener() {

           @Override
           public void mouseClicked(MouseEvent e) {
               exitClick();
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
