
package FLOG_GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.EventHandler;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dushan Galappaththi
 */
public class GameScreen extends JFrame {
 
    //static GameScreen myFrame;
    static int countMe = 0;
    static PanelGamePlay panelPlaying;
    private PanelMainMenu panelMainMenu;
    private JPanel container;
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
        
        this.setSize(new Dimension(900, 619)); //when 800,600 is entered then the actual size becomes 812,590. No idea why
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.validate();
        this.setVisible(true);
    }
    
    private void preparePanels()
    {
        panelMainMenu = new PanelMainMenu();
        panelPlaying = new PanelGamePlay();

        //Adding Panels to Card Layout
        container = new JPanel();
        
        container.setLayout(mainPanelCards);
        container.add(panelMainMenu, "MainMenu");
        container.add(panelPlaying,"PlayingScreen");
     
        this.getContentPane().add(container,BorderLayout.CENTER);
        
        TestGUI_Inputs testing = new TestGUI_Inputs();
        
        ControllerGamePlay cgp = new ControllerGamePlay(panelPlaying,this);
        ControllerMainMenu cmm = new ControllerMainMenu(panelMainMenu, this);
   
    }
    
    
 
    public void showPlayScreen() 
    {
       /* //TestGUI_Inputs testing = new TestGUI_Inputs();

        dataForUI data = new dataForUI();
        ControllerGamePlay cgp = new ControllerGamePlay(panelPlaying,this);
        panelPlaying.drawPlayerName("NoobSnakeEyes");
        panelPlaying.drawPlayerPosition(3);
        panelPlaying.drawPlayerScore(8888);
        panelPlaying.drawRoundNumber(4);
        panelPlaying.drawTweleveLetters(data.getLetters());
        //panelPlaying.drawOpponents(data.getPdArray());

        CardLayout cl = (CardLayout) (container.getLayout());
        cl.show(container, "PlayingScreen");
        this.validate();*/

    }
    
    private void showMainMenu()
    {
        changeScreen("MainMenu", null);
      
    }
    
    public void changeScreen(String screenName, String invokerName)
    {
        CardLayout cl = (CardLayout) (container.getLayout());
        cl.show(container, screenName);
        this.validate();

    }
    
   
    public void moveScreen(int x, int y,int mX,int mY)
    {
        this.setLocation(x -mX, y-mY);
        
    }
    
   
}
