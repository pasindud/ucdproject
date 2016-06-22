
package FLOG_GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dushan Galappaththi
 */
public class GameScreen extends JFrame {
 
    static GameScreen myFrame;
    PanelPlaying panelPlaying;
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater( new Runnable() {

            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
 
    private static void createAndShowGUI() 
    {
        myFrame = new GameScreen();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.prepareUI();
        myFrame.pack();
        myFrame.setVisible(true);
    }
 
    private void prepareUI() 
    {
                
        TestGUI_Inputs testing = new TestGUI_Inputs();
        
        dataForUI data = new dataForUI();
        
        panelPlaying = new PanelPlaying();
        panelPlaying.drawPlayerName("NoobSnakeEyes");
        panelPlaying.drawPlayerPosition(3);
        panelPlaying.drawPlayerScore(8888);
        panelPlaying.drawRoundNumber(4);
        panelPlaying.drawTweleveLetters(data.getLetters());
        
        panelPlaying.drawOpponents(data.getPdArray());
        
        getContentPane().add(panelPlaying, BorderLayout.CENTER);

    }
 
}
