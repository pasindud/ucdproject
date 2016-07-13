
package FLOG_GUI;

import static FLOG_GUI.DataForUI.SelectMultiplayer;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import FLOG_LOGIC.*;

/**
 *
 * @author Dushan Galappaththi
 */
public class GameScreen extends JFrame {
 
    //static GameScreen myFrame;
    static int countMe = 0;
    public static PanelGamePlay panelPlaying;
    private PanelMainMenu panelMainMenu;
    private PanelSettings panelSettings;
    private PanelRoundReadyUp panelRoundReadyUp;
    
    private JPanel container;
    ControllerGamePlay controllerGamePlay;
    ControllerMainMenu controllerMainMenu;
    ControllerSettings controllerSettings;
    ControllerRoundReadyUp controllerRoundReadyUp;
    
    CardLayout mainPanelCards = new CardLayout();
    DataForUI dataForUI=new DataForUI();
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater( new Runnable() {

            @Override
            public void run() {
                
               GameScreen myFrame = new GameScreen();
             
            }
        });
    }
    

    public GameScreen() {
        // Tempory.
        createAndShowGUI();
        SelectMultiPlayer selectMultiPlayer = new SelectMultiPlayer();
        container = new JPanel();
        container.setLayout(mainPanelCards);
        container.add(selectMultiPlayer, dataForUI.SelectMultiplayer);
    }
    
    private void createAndShowGUI() 
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        preparePanels();
//        showMainMenu();
        
        this.setSize(new Dimension(900, 619)); 
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
        panelSettings = new PanelSettings();
        panelRoundReadyUp = new PanelRoundReadyUp();

        SelectMultiPlayer selectMultiPlayer = new SelectMultiPlayer();
        //Adding Panels to Card Layout
        container = new JPanel();
        
        container.setLayout(mainPanelCards);
        container.add(selectMultiPlayer, dataForUI.SelectMultiplayer);
        container.add(panelMainMenu, dataForUI.STR_MAINMENU);
        container.add(panelPlaying,dataForUI.STR_GAMEPLAY);
        container.add(panelSettings,dataForUI.STR_SETTINGS);
        container.add(panelRoundReadyUp,dataForUI.STR_ROUNDREADYUP);
     
        this.getContentPane().add(container,BorderLayout.CENTER);
        
        TestGUI_Inputs testing = new TestGUI_Inputs();
        
        controllerGamePlay = new ControllerGamePlay(panelPlaying,this);
        controllerRoundReadyUp = new ControllerRoundReadyUp(panelRoundReadyUp,this, controllerGamePlay);
        controllerMainMenu = new ControllerMainMenu(panelMainMenu, this, controllerRoundReadyUp);
        controllerSettings = new ControllerSettings(panelSettings, this);
    }
    
    private void showMainMenu()
    {
        changeScreen(dataForUI.STR_MAINMENU, null);
    }
    
    public void changeScreen(String screenName, String invokerName)
    {
         CardLayout cl = (CardLayout) (container.getLayout());
        if (invokerName != null) 
        {
            switch (screenName) 
            {
                case DataForUI.SelectMultiplayer:
                    cl.show(container, screenName);
                    break;

                case DataForUI.STR_MAINMENU:
                    cl.show(container, screenName);
                    break;

                case DataForUI.STR_SETTINGS:
                    cl.show(container, screenName);
                    controllerSettings.setReturnTo(invokerName);
                    break;
                case DataForUI.STR_ROUNDREADYUP:
                    System.out.println("hit --------------------------------------------");
                    cl.show(container, screenName);
                    controllerRoundReadyUp.runTimer();
                    panelRoundReadyUp.setRound(String.valueOf(dataForUI.RoundNum));
                    break;
                default:
                    cl.show(container, screenName);
                    break;

            }
        } 
        else 
        {
            cl.show(container, screenName);
        }
        this.validate();

    }
    
   
    public void moveScreen(int x, int y,int mX,int mY)
    {
        this.setLocation(x -mX, y-mY);
        
    }   
}
