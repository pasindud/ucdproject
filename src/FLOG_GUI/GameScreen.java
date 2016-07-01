
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
     
        this.getContentPane().add(container,BorderLayout.CENTER);
        
        TestGUI_Inputs testing = new TestGUI_Inputs();
        
        ControllerGamePlay cgp = new ControllerGamePlay(panelPlaying,this);
        ControllerMainMenu cmm = new ControllerMainMenu(panelMainMenu, this);
   
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
