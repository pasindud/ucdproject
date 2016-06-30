
package FLOG_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Transparency;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author Dushan Galappaththi
 */
public class OpponentsUI extends JPanel
{
    OpponentsUI me;
    Border border;
    private Image bg;
   
 // accept an array list containing oponenet info
        public OpponentsUI(int pos, String OppName, int score, String letterOne, String letterTwo) 
        {
            super();
            me = this;
            this.setOpaque(false);
            this.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
            
            bg = new ImageIcon(getClass().getResource("/images/bg_opponent_n.png")).getImage();
      
            PanelOppPos panelOppPos = new PanelOppPos(pos);
            
            PanelOppInfo panelOppInfo = new PanelOppInfo(OppName, score, letterOne, letterTwo);
            add(panelOppPos);
            add(panelOppInfo);
        }
         @Override
        protected void paintComponent(Graphics g) 
        {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        }
        
        public class PanelOppPos extends JPanel
        {
            public PanelOppPos(int Pos) 
            {
                this.setLayout(null);
                Font font_Trebuchet;
                if(Pos>9 && Pos<100)
                {
                       font_Trebuchet = new Font("Arial Rounded MT Bold", 0,45);
                }
                else if(Pos>99)
                {
                    font_Trebuchet = new Font("Arial Rounded MT Bold", 0,30);
                }
                else
                {
                    font_Trebuchet = new Font("Arial Rounded MT Bold", 0,55);
                }
                //this.setOpaque(true);
                this.setMinimumSize(new Dimension(86, 96));
                this.setMaximumSize(new Dimension(86, 96));
                this.setPreferredSize(new Dimension(86, 96));
                this.setOpaque(false);
                JLabel lblPos = new JLabel();
                //lblPos.setSize(54, 73);
                lblPos.setBounds(12, 12, 58, 73);
                lblPos.setText(String.valueOf(Pos));
                lblPos.setFont(font_Trebuchet);
                lblPos.setHorizontalAlignment(SwingConstants.CENTER);
                
                add(lblPos);
            }
            
        
        }
        
        public class PanelOppInfo extends JPanel
        {

            public PanelOppInfo(String OppName, int score, String letterOne, String letterTwo) 
            {
                this.setLayout(null);
                Font font_Trebuchet = new Font("Trebuchet MS", 0, 25);
                this.setOpaque(false);
                this.setMinimumSize(new Dimension(130, 96));
                this.setMaximumSize(new Dimension(130, 96));
                this.setPreferredSize(new Dimension(130, 96));
                
                JLabel lblOpponentName = new JLabel();
                lblOpponentName.setBounds(0, 12, 118, 20);
                lblOpponentName.setText(OppName);
                lblOpponentName.setFont(font_Trebuchet);
                lblOpponentName.setForeground(Color.WHITE);
                lblOpponentName.setMinimumSize(new Dimension(118, 16));
                lblOpponentName.setMaximumSize(new Dimension(118, 16));
                lblOpponentName.setPreferredSize(new Dimension(118, 16));
               
                
                
                JLabel lblOpScore = new JLabel();
                lblOpScore.setText("score : "+String.valueOf(score));
                lblOpScore.setFont(new Font("Trebuchet MS", 0, 20));
                lblOpScore.setForeground(Color.WHITE);
                lblOpScore.setMinimumSize(new Dimension(118, 16));
                lblOpScore.setMaximumSize(new Dimension(118, 16));
                lblOpScore.setPreferredSize(new Dimension(118, 16));
                lblOpScore.setBounds(0, 33, 119, 18);
                
            
                JLabel lblLetterOne = new JLabel();
                lblLetterOne.setText(letterOne);
                lblLetterOne.setFont(new Font("Arial Rounded MT Bold", 0, 25));
                lblLetterOne.setForeground(Color.BLACK);
                lblLetterOne.setMinimumSize(new Dimension(21, 21));
                lblLetterOne.setMaximumSize(new Dimension(21, 21));
                lblLetterOne.setPreferredSize(new Dimension(21, 21));
                lblLetterOne.setBounds(-1, 55, 30, 30);
                lblLetterOne.setHorizontalAlignment(SwingConstants.CENTER);
                
            
                JLabel lblLetterTwo = new JLabel();
                lblLetterTwo.setText(letterTwo);
                lblLetterTwo.setHorizontalAlignment(SwingConstants.CENTER);
                lblLetterTwo.setFont(new Font("Arial Rounded MT Bold", 0, 25));
                lblLetterTwo.setForeground(Color.BLACK);
                lblLetterTwo.setMinimumSize(new Dimension(21, 21));
                lblLetterTwo.setMaximumSize(new Dimension(21, 21));
                lblLetterTwo.setPreferredSize(new Dimension(21, 21));
                lblLetterTwo.setBounds(56, 55, 30, 30);
                
                
                add(lblOpponentName);
                add(lblOpScore);
                add(lblLetterOne);
                add(lblLetterTwo);
            }
            
        }
}
