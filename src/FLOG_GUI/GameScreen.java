
package FLOG_GUI;

import FLOG_LOGIC.Game;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dushan Galappaththi
 */
public class GameScreen extends JFrame 
{
 /**
  * Panel initialization, setting up each panel to cardlayout and
  * initializing controllers for each panel are done in this class.
  * 
  * Also manages changing screens and movement of the gamescreen (frame) 
  * relative to the player's computer screen is handled through this class
  * 
  */
    
    
    static int countMe = 0;
    public static PanelGamePlay panelPlaying;
    private PanelMainMenu panelMainMenu;
    private PanelSettings panelSettings;
    private PanelRoundReadyUp panelRoundReadyUp;
    
    //Holds the CardLayout
    private JPanel container; 
    
    //Controllers for Panels 
    ControllerGamePlay controllerGamePlay;
    ControllerMainMenu controllerMainMenu;
    ControllerSettings controllerSettings;
    ControllerRoundReadyUp controllerRoundReadyUp;
    
    //CardLayout which will hold all the panels
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
    
    //Constructor 
    public GameScreen() {
        createAndShowGUI();
        
    }
    
    //JFrame setting up
    private void createAndShowGUI() 
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        preparePanels();
        showMainMenu();
        
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

        //Adding Panels to Card Layout
        container = new JPanel();
        
        container.setLayout(mainPanelCards);
        container.add(panelMainMenu, dataForUI.STR_MAINMENU);
        container.add(panelPlaying,dataForUI.STR_GAMEPLAY);
        container.add(panelSettings,dataForUI.STR_SETTINGS);
        container.add(panelRoundReadyUp,dataForUI.STR_ROUNDREADYUP);
     
        this.getContentPane().add(container,BorderLayout.CENTER);
        
        //TestGUI_Inputs testing = new TestGUI_Inputs();
        initateGame();
      controllerGamePlay = new ControllerGamePlay(panelPlaying,this);
      controllerRoundReadyUp = new ControllerRoundReadyUp(panelRoundReadyUp,this, controllerGamePlay);
       controllerMainMenu = new ControllerMainMenu(panelMainMenu, this, controllerRoundReadyUp);
       controllerSettings = new ControllerSettings(panelSettings, this);
       
   
    }
    
   
     
    
    private void showMainMenu()
    {
        changeScreen(dataForUI.STR_MAINMENU, null);
      
    }
    private void initateGame()
    {
        Game game = new Game();
        game.addPlayer("Pasindu");
        game.addPlayer("Dushan");
        game.addPlayer("Ishanka");
        game.addPlayer("Dilshan");
        game.addPlayer("Piumal");
        game.addPlayer("Dinuka");
        game.addPlayer("snake");
        game.addPlayer("fff");
        game.addPlayer("ghgfh");
        game.addPlayer("ffgff");
        game.addPlayer("ffghghhf");
        game.addPlayer("ffghghf");
        
        
        
        DataForUI.game = game;
        DataForUI.getPlayerList();
    }
    /**
     * The following Method will handle all change screen calls,
     * basic switching for the panels in the CardLayout
     * 
     * @param screenName : The screen to change to
     * @param invokerName : The screen where the change screen is called, 
     * this parameter is used to return back to the previous screen
     *
     */
    public void changeScreen(String screenName, String invokerName)
    {
         CardLayout cl = (CardLayout) (container.getLayout());
        if (invokerName != null) 
        {
            switch (screenName) 
            {
                case DataForUI.STR_MAINMENU:
                    cl.show(container, screenName);
                    break;

                case DataForUI.STR_SETTINGS:
                    cl.show(container, screenName);
                    controllerSettings.setReturnTo(invokerName);
                    break;
                case DataForUI.STR_ROUNDREADYUP:
                    cl.show(container, screenName);
                    controllerRoundReadyUp.runTimer();
                    controllerRoundReadyUp.drawPlayers();
                    if(DataForUI.RoundNum>=2){controllerRoundReadyUp.setTitlePlayers(false);}
                    else{controllerRoundReadyUp.setTitlePlayers(true);}
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
    
    /**
     * The following method is used to move the game screen relative to the computer screen
     * 
     * @param x : mouse X Coordinate relative to JFrame
     * @param y : mouse Y Coordinate relative to JFrame
     * @param mX : mouse X Coordinate relative to the actual screen of the computer
     * @param mY : mouse Y Coordinate relative to the actual screen of the computer
     */
   
    public void moveScreen(int x, int y,int mX,int mY)
    {
        this.setLocation(x -mX, y-mY);
        
    }

    
    
   
}
