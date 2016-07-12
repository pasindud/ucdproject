
package FLOG_GUI;

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
public class GameScreen extends JFrame {
 
    //static GameScreen myFrame;
    static int countMe = 0;
    public static PanelGamePlay panelPlaying;
    private PanelMainMenu panelMainMenu;
    private PanelSettings panelSettings;
    
    private JPanel container;
    ControllerGamePlay controllerGamePlay;
    ControllerMainMenu controllerMainMenu;
    ControllerSettings controllerSettings;
    
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

        //Adding Panels to Card Layout
        container = new JPanel();
        
        container.setLayout(mainPanelCards);
        container.add(panelMainMenu, "MainMenu");
        container.add(panelPlaying,"PlayingScreen");
        container.add(panelSettings,"Settings");
     
        this.getContentPane().add(container,BorderLayout.CENTER);
        
        TestGUI_Inputs testing = new TestGUI_Inputs();
        
      controllerGamePlay = new ControllerGamePlay(panelPlaying,this);
        controllerMainMenu = new ControllerMainMenu(panelMainMenu, this);
       controllerSettings = new ControllerSettings(panelSettings, this);
   
    }
    
   
     
    
    private void showMainMenu()
    {
        changeScreen("MainMenu", null);
      
    }
    
    public void changeScreen(String screenName, String invokerName)
    {
         CardLayout cl = (CardLayout) (container.getLayout());
        if (invokerName != null) 
        {
            switch (screenName) 
            {
                case "GamePlay":
                    cl.show(container, screenName);
                    break;

                case "Settings":
                    cl.show(container, screenName);
                    controllerSettings.setReturnTo(invokerName);
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
