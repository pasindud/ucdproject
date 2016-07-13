
package FLOG_GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.EventHandler;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dushan Galappaththi
 */
public class GameScreen extends JFrame {
 
    //static GameScreen myFrame;
    static int countMe = 0;
    static PanelPlaying panelPlaying;
    private PanelMainMenu panelMainMenu;
    private Panel container;
    CardLayout mainPanelCards = new CardLayout();
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater( new Runnable() {

            @Override
            public void run() {
               GameScreen myFrame = new GameScreen();
             
            }
        });
    }

    public GameScreen() {
        createAndShowGUI();
    }
    
    private void createAndShowGUI() 
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        preparePanels();
        showMainMenu();
        this.setSize(new Dimension(830, 640)); //when 800,600 is entered then the actual size becomes 812,590. No idea why
        this.setResizable(false);
        this.setVisible(true);
    }
    
    private void preparePanels()
    {
        
        panelMainMenu = new PanelMainMenu();
        panelPlaying = new PanelPlaying();
        
        initializeMainMenu();
        initializePlayScreen();
        
        //Adding Panels to Card Layout
        container = new Panel();
        container.setLayout(mainPanelCards);
        container.add(panelMainMenu, "MainMenu");
        container.add(panelPlaying,"PlayingScreen");
     
        this.getContentPane().add(container,BorderLayout.CENTER);
     
    }
    
    public void showPlayScreen() {
        TestGUI_Inputs testing = new TestGUI_Inputs();

        dataForUI data = new dataForUI();

        panelPlaying.drawPlayerName("NoobSnakeEyes");
        panelPlaying.drawPlayerPosition(3);
        panelPlaying.drawPlayerScore(8888);
        panelPlaying.drawRoundNumber(4);
        panelPlaying.drawTweleveLetters(data.getLetters());
        panelPlaying.drawOpponents(data.getPdArray());

        CardLayout cl = (CardLayout) (container.getLayout());
        cl.show(container, "PlayingScreen");
        this.validate();
    }
    
    private void showMainMenu() {
        CardLayout cl = (CardLayout) (container.getLayout());
        cl.show(container, "MainMenu");
        this.validate();
    }
    
     /** Click Events for buttons in Panels 
        
        --- Please keep in mind the following Abbreviations ---
        MM = MainMenu
        PS = PlayingScreen
         
     
        (example : 'MM_SettingsClick()' means Main menu's "Settings" button's click event)
     */
     
     //---Main Menu---
     
     private void MM_StartClick()
     {
        CardLayout cl = (CardLayout) (container.getLayout());
        
        SelectMultiPlayer selectMultiPlayer = new SelectMultiPlayer();
        container.add(selectMultiPlayer,"SelectMultiPlayer");
        cl.show(container, "SelectMultiPlayer");
        this.validate();
         
        
//         SelectMultiPlayer selectMultiPlayer = new SelectMultiPlayer();
//         selectMultiPlayer.show();
//         selectMultiPlayer.setVisible(true);
        // showPlayScreen();
         
//        CardLayout cl = (CardLayout) (container.getLayout());
//        cl.show(container, "SelectMultiPlayer");
//        this.validate();
//        this.hide();
     }
     
     private void MM_SettingsClick()
     {
         System.out.println("[I] Settings button was clicked");
     }
     
     private void MM_HelpClick()
     {
         System.out.println("[I] Help button was clicked");
     }
     
     private void MM_AboutClick()
     {
         System.out.println("[I] About button was clicked");
     }
     
     private void MM_ExitClick()
     {
         System.out.println("[I] Exit button was clicked");
     }
     
     // ---Play Screen---
     
     private void PS_SubmitClick(String answer)
     {
         //When Submit button is clicked, you can get what was submitted through 'answer' variable
         System.out.println("Value on answer Textbox : " +answer);
     }
     
     private void PS_GenerateClick()
     {
         System.out.println("[I] Generate was clicked");
     }
     
     private void PS_VowelsClick()
     {
         System.out.println("[I] Vowels was clicked");
     }
     
     private void PS_ConsonentsClick()
     {
         System.out.println("[I] Consonents was clicked");
     }
     
     
     //Initializing of Event Listners of Panels
          
    private void initializeMainMenu() 
    {
       final int _start = 0;
       final int _setting = 2;
       final int _help = 3;
       final int _about = 4;
       final int _exit = 1;
       
        panelMainMenu.getComponent(_start).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                MM_StartClick();
              
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
               MM_SettingsClick();
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
               MM_HelpClick();
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
               MM_AboutClick();
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

    private void initializePlayScreen()
    {
        final int _submit=17;
        final int _generate=16;
        final int _vowels=18;
        final int _consonent=19;
        final int _answer =15;
        final JTextField txt = (JTextField)panelPlaying.getComp(_answer);
        
       
        panelPlaying.getComp(_submit).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) 
            {
                //System.out.println("[I] Submit was clicked | value on textbox : " + txt.getText().toString());
                PS_SubmitClick(txt.getText().toString());
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
    
        panelPlaying.getComp(_generate).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                PS_GenerateClick();
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
        
        panelPlaying.getComp(_vowels).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                PS_VowelsClick();
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
         
        panelPlaying.getComp(_consonent).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                PS_ConsonentsClick();
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
