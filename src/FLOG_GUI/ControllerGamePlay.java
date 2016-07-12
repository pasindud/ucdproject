
package FLOG_GUI;

//import static FLOG_GUI.GameScreen.panelGamePlay;
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
    PanelGamePlay panelGamePlay;
    GameScreen gameScreen;
    int mouseX=0;
    int mouseY=0;
    char[] letterArr = new char[12];
    
    
    
    public ControllerGamePlay(PanelGamePlay panelGamePlay, GameScreen gameScreen) {
        this.panelGamePlay = panelGamePlay;
        this.gameScreen = gameScreen;
        dataForUI data = new dataForUI();
        initializeGamePlayListeners();
        setPlayerInfo("Snake Eyes",999,5435,4);
        panelGamePlay.drawOpponents(data.getPdArray());
    }
    
    public void setPlayerInfo(String playerName, int Rank, int score, int round)
    {
        panelGamePlay.drawPlayerName(playerName);
        panelGamePlay.drawPlayerPosition(Rank);
        panelGamePlay.drawPlayerScore(score);
        panelGamePlay.drawRoundNumber(round);
     
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
        final JTextField txt = (JTextField)panelGamePlay.getCompBottom(_answer);
              
        
        panelGamePlay.getCompTop(0).addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                
              gameScreen.moveScreen(e.getXOnScreen(), e.getYOnScreen(),mouseX,mouseY);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                
            }
        });
        
        panelGamePlay.getCompTop(0).addMouseListener(new MouseListener() {
           

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
        
        panelGamePlay.getCompBottom(_submit).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) 
            {
                //System.out.println("[I] Submit was clicked | value on textbox : " + txt.getText().toString());
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_submit_h.png"));
                panelGamePlay.setIcon_Submit(imgIcon);
                submitClick(txt.getText().toString());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_submit_c.png"));
                panelGamePlay.setIcon_Submit(imgIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_submit_h.png"));
                panelGamePlay.setIcon_Submit(imgIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_submit_h.png"));
                panelGamePlay.setIcon_Submit(imgIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_submit_n.png"));
                panelGamePlay.setIcon_Submit(imgIcon);
            }

        } );
        
        panelGamePlay.getCompBottom(_generate).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_autosearch_h.png"));
                panelGamePlay.setIcon_Gen(imgIcon);
                generateClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {
               ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_autosearch_c.png"));
                panelGamePlay.setIcon_Gen(imgIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_autosearch_h.png"));
                panelGamePlay.setIcon_Gen(imgIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_autosearch_h.png"));
                panelGamePlay.setIcon_Gen(imgIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
               ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_autosearch_n.png"));
                panelGamePlay.setIcon_Gen(imgIcon);
            }
        });
        
        panelGamePlay.getCompBottom(_vowels).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_vowels_h.png"));
                panelGamePlay.setIcon_Vowels(imgIcon);
                vowelsClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_vowels_c.png"));
                panelGamePlay.setIcon_Vowels(imgIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_vowels_h.png"));
                panelGamePlay.setIcon_Vowels(imgIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_vowels_h.png"));
                panelGamePlay.setIcon_Vowels(imgIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
               ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_vowels_n.png"));
                panelGamePlay.setIcon_Vowels(imgIcon);
            }
        });
        
        panelGamePlay.getCompBottom(_consonent).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_consonent_h.png"));
                panelGamePlay.setIcon_Consonents(imgIcon);
                consonentsClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {
               ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_consonent_c.png"));
                panelGamePlay.setIcon_Consonents(imgIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_consonent_h.png"));
                panelGamePlay.setIcon_Consonents(imgIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_consonent_h.png"));
                panelGamePlay.setIcon_Consonents(imgIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
               ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_consonent_n.png"));
                panelGamePlay.setIcon_Consonents(imgIcon);
            }
        });
        
        panelGamePlay.getCompTopBorder(1).addMouseListener(new MouseListener() {

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
