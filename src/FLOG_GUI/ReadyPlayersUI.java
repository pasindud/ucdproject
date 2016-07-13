
package FLOG_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 *
 * @author Dushan Galappaththi
 */
public class ReadyPlayersUI extends JPanel
{
    int BoundX = 152;
    int BoundY = 240;
    int BoundWidth =601;
    int BoundHeight =290;
    boolean isFirstRound = true;
    private Image bg;

    public ReadyPlayersUI(boolean isFirstRound, int playerIndex) 
    {
        this.isFirstRound = isFirstRound;
        this.setLayout(null);
        this.setBounds(0, 0, 616, 30);
        this.setMinimumSize(new Dimension(616, 30));
        this.setMaximumSize(new Dimension(616, 30));
        this.setPreferredSize(new Dimension(616, 30));
        this.setOpaque(false);
        
        bg = new ImageIcon(getClass().getResource("/images/lbl_player_info.png")).getImage();
        
        if(isFirstRound)
        {
            firstRound(playerIndex);
        }
        else
        {
            otherRounds();
        }
    }
        @Override
        protected void paintComponent(Graphics g) 
        {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        }
    private void firstRound(int i) 
    {
       
           JLabel lblName = new JLabel();
           lblName.setText(DataForUI.PdArray[i].getName());
           lblName.setHorizontalAlignment(SwingConstants.CENTER);
           lblName.setHorizontalTextPosition(SwingConstants.CENTER);
           lblName.setBounds(0, 0, 616, 30);
           this.add(lblName);
           
       
    }

    private void otherRounds()
    {
        
        
    }
    
    
}