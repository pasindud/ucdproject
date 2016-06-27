
package FLOG_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Transparency;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Dushan Galappaththi
 */
public class OpponentsUI extends JPanel
{
    OpponentsUI me;
    Border border;
   
 // accept an array list containing oponenet info
        public OpponentsUI(int pos, String OppName, int score, String letterOne, String letterTwo) 
        {
            super();
            me = this;
            this.setOpaque(false);
      
            PanelOppPos panelOppPos = new PanelOppPos(pos);
            PanelOppInfo panelOppInfo = new PanelOppInfo(OppName, score, letterOne, letterTwo);
            add(panelOppPos);
            add(panelOppInfo);
        }
        
        public class PanelOppPos extends JPanel
        {
            public PanelOppPos(int Pos) 
            {
                Font font_Trebuchet = new Font("Trebuchet MS", 0,36);
                //this.setOpaque(false);
                this.setMinimumSize(new Dimension(86, 96));
                this.setMaximumSize(new Dimension(86, 96));
                this.setPreferredSize(new Dimension(86, 96));
                
                JLabel lblPos = new JLabel();
                lblPos.setText(String.valueOf(Pos));
                lblPos.setFont(font_Trebuchet);
                
                add(lblPos);
            }
            
        
        }
        
        public class PanelOppInfo extends JPanel
        {

            public PanelOppInfo(String OppName, int score, String letterOne, String letterTwo) 
            {
                Font font_Trebuchet = new Font("Trebuchet MS", 0, 20);
               // this.setOpaque(false);
                this.setMinimumSize(new Dimension(130, 96));
                this.setMaximumSize(new Dimension(130, 96));
                this.setPreferredSize(new Dimension(130, 96));
                
                JLabel lblOpponentName = new JLabel();
                lblOpponentName.setText(OppName);
                lblOpponentName.setFont(font_Trebuchet);
                lblOpponentName.setMinimumSize(new Dimension(118, 16));
                lblOpponentName.setMaximumSize(new Dimension(118, 16));
                lblOpponentName.setPreferredSize(new Dimension(118, 16));
            
                JLabel lblOpScore = new JLabel();
                lblOpScore.setText(String.valueOf(score));
                lblOpScore.setFont(font_Trebuchet);
                lblOpScore.setMinimumSize(new Dimension(118, 16));
                lblOpScore.setMaximumSize(new Dimension(118, 16));
                lblOpScore.setPreferredSize(new Dimension(118, 16));
            
                JLabel lblLetterOne = new JLabel();
                lblLetterOne.setText(letterOne);
                lblLetterOne.setFont(font_Trebuchet);
                lblLetterOne.setMinimumSize(new Dimension(21, 21));
                lblLetterOne.setMaximumSize(new Dimension(21, 21));
                lblLetterOne.setPreferredSize(new Dimension(21, 21));
            
                JLabel lblLetterTwo = new JLabel();
                lblLetterTwo.setText(letterTwo);
                lblLetterTwo.setFont(font_Trebuchet);
                lblLetterTwo.setMinimumSize(new Dimension(21, 21));
                lblLetterTwo.setMaximumSize(new Dimension(21, 21));
                lblLetterTwo.setPreferredSize(new Dimension(21, 21));
                
                add(lblOpponentName);
                add(lblOpScore);
                add(lblLetterOne);
                add(lblLetterTwo);
            }
            
        }
}
