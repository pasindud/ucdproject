
package FLOG_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
public class ReadyPlayersUI extends JPanel {
  int BoundX = 152;
  int BoundY = 240;
  int BoundWidth = 601;
  int BoundHeight = 290;
  boolean isFirstRound = true;
  private Image bg;
  private Font player_Trebuchet = new Font("Trebuchet MS",Font.PLAIN, 10);
  private Font other_arialRounded = new Font("Arial Rounded MT Bold",Font.PLAIN, 10);

  public ReadyPlayersUI(boolean isFirstRound, int playerIndex) {
    this.isFirstRound = isFirstRound;
    this.setLayout(null);
    this.setBounds(0, 0, 616, 30);
    this.setMinimumSize(new Dimension(616, 30));
    this.setMaximumSize(new Dimension(616, 30));
    this.setPreferredSize(new Dimension(616, 30));
    this.setOpaque(false);

    bg = new ImageIcon(getClass().getResource("/images/lbl_player_info.png")).getImage();

    if (isFirstRound) {
      firstRound(playerIndex);
    } else {
      otherRounds(playerIndex);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
  }

  private void firstRound(int i) {

    JLabel lblName = new JLabel();
    lblName.setText(DataForUI.sortedPdArrayByScore[i].getName());
    lblName.setHorizontalAlignment(SwingConstants.CENTER);
    lblName.setHorizontalTextPosition(SwingConstants.CENTER);
    lblName.setBounds(0, 0, 616, 30);
    //lblName.setFont(player_Trebuchet);
    if(DataForUI.sortedPdArrayByScore[i].getName().equals(DataForUI.currentUsername))
    {
        lblName.setForeground(Color.YELLOW);
    }
    else{
        lblName.setForeground(Color.WHITE);
    }
    this.add(lblName);
  }

  private void otherRounds(int i) {
    JLabel lblName = new JLabel();
    lblName.setText(DataForUI.sortedPdArrayByScore[i].getName());
    lblName.setHorizontalAlignment(SwingConstants.CENTER);
    lblName.setHorizontalTextPosition(SwingConstants.CENTER);
    lblName.setBounds(74, 0, 126, 30);
    lblName.setForeground(Color.WHITE);
    //lblName.setFont(player_Trebuchet);
    
    JLabel lblRank = new JLabel();
    lblRank.setText(String.valueOf(DataForUI.sortedPdArrayByScore[i].getPosition()));
    lblRank.setHorizontalAlignment(SwingConstants.CENTER);
    lblRank.setHorizontalTextPosition(SwingConstants.CENTER);
    lblRank.setBounds(0, 0, 74, 30);
    //lblRank.setFont(other_arialRounded);

    JLabel lblLetters = new JLabel();
    lblLetters.setText(DataForUI.sortedPdArrayByScore[i].getLetterArry(DataForUI.RoundNum)); //*******
    lblLetters.setHorizontalAlignment(SwingConstants.CENTER);
    lblLetters.setHorizontalTextPosition(SwingConstants.CENTER);
    lblLetters.setBounds(204, 0, 151, 30);
    //lblLetters.setFont(other_arialRounded);
    
    JLabel lblWord = new JLabel();
    lblWord.setText(DataForUI.sortedPdArrayByScore[i].getWordArry(DataForUI.RoundNum));//*****
    lblWord.setHorizontalAlignment(SwingConstants.CENTER);
    lblWord.setHorizontalTextPosition(SwingConstants.CENTER);
    lblWord.setBounds(358, 0, 135, 30);
    //lblWord.setFont(other_arialRounded);

    JLabel lblScore = new JLabel();
    lblScore.setText(String.valueOf(DataForUI.sortedPdArrayByScore[i].getScore()));
    lblScore.setHorizontalAlignment(SwingConstants.CENTER);
    lblScore.setHorizontalTextPosition(SwingConstants.CENTER);
    lblScore.setBounds(497, 0, 100, 30);
    //lblScore.setFont(other_arialRounded);

    this.add(lblRank);
    this.add(lblName);
    this.add(lblLetters);
    this.add(lblWord);
    this.add(lblScore);
  }
}
