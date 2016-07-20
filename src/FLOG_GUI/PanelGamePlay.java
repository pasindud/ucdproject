package FLOG_GUI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Dushan Galappaththi
 */
public class PanelGamePlay extends javax.swing.JPanel {

  /**
   * Creates new form PanelGamePlay
   */
  private Image bg;
  JPanel pnlOppList;
  JPanel pnlOppRow;
  JScrollPane jsp;
  DataForUI DataForUI;
  private JLabel[] letter_label_array;
  private int swapLetterLabelIndex;
  private int noOfSwaps;
  public String firstLetter;
  public String secondLetter;
  public String channelName;
  public String username;
  public boolean isAutoWordGenUsed = false;
  //Use to check if the letter was clicked
  private boolean bl1, bl2, bl3, bl4, bl5, bl6, bl7, bl8, bl9, bl10, bl11, bl12;
  private GameScreen gameScreen;

  public String[] getOtherLetters() {
    String[] otherLetters = new String[10];
    for (int i = 0; i < letter_label_array.length; i++) {
      otherLetters[i] = letter_label_array[i].getText();
    }
    return otherLetters;
  }

  public String[] getInitialLetters() {
    return new String[] {firstLetter, secondLetter};
  }

  public PanelGamePlay(GameScreen gameScreen) {
    initComponents();
    bg = new ImageIcon(getClass().getResource("/images/bg_playscreen3.png")).getImage();
    pnlOppList = new JPanel();
    pnlOppRow = new JPanel();
    DataForUI = new DataForUI();
    lblTime.setText("");
    this.gameScreen = gameScreen;
    resetValuesForRound();
  }

  public void resetValuesForRound() {
    // Resets default values in each round
    noOfSwaps = 0;
    swapLetterLabelIndex = -99;
    firstLetter = FLOG_LOGIC.Utils.getRandomConsonant();
    secondLetter = FLOG_LOGIC.Utils.getRandomVowel();
    lblL1.setText(firstLetter);
    lblL2.setText(secondLetter);
    letter_label_array =
        new JLabel[] {lblL3, lblL4, lblL5, lblL6, lblL7, lblL8, lblL9, lblL10, lblL11, lblL12};
    isAutoWordGenUsed = false;
    bl1 = true;
    bl2 = true;
    bl3 = true;
    bl4 = true;
    bl5 = true;
    bl6 = true;
    bl7 = true;
    bl8 = true;
    bl9 = true;
    bl10 = true;
    bl11 = true;
    bl12 = true;
    txtAnswer.setText("Answer");
    for (JLabel x : letter_label_array) {
      x.setText("-");
    }
  }

  private boolean checkAllLetters() {
    boolean check = true;
    for (JLabel x : letter_label_array) {
      if (x.getText().equalsIgnoreCase("-")) {
        check = false;
      }
    }
    return check;
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
  }

  public void updateOpponents() {
    pnlOpponents.removeAll();
    pnlOppList = new JPanel();
    pnlOppRow = new JPanel();
    drawPlayerPosition(DataForUI.myRank);
    drawPlayerScore(DataForUI.myTotalScore);
    revalidate();
    repaint();
  }

  public void drawOpponents(PlayerData[] pdArr) {
    setChannel(DataForUI.currentChannel);
    updateOpponents();
    int count = 0;
    int div3 = (int) pdArr.length / 3;
    int loopTimes = div3 + 1;

    pnlOppRow.setLayout(new BoxLayout(pnlOppRow, BoxLayout.PAGE_AXIS));
    pnlOppRow.setOpaque(false);

    for (int i = 0; i < loopTimes; i++) {
      JPanel pnlA = new JPanel();
      pnlA.setLayout(new FlowLayout(FlowLayout.LEADING));
      pnlA.setOpaque(false);

      for (int j = 0; j < 3; j++) {

        if (count < pdArr.length) {
          pnlA.add(
              new OpponentsUI(
                  pdArr[count].getPosition(),
                  pdArr[count].getName(),
                  pdArr[count].getScore(),
                  pdArr[count].getLetterOne(),
                  pdArr[count].letterTwo));
          count++;
        }
      }
      pnlOppRow.add(pnlA);
    }

    jsp = new JScrollPane(pnlOppRow);
    jsp.setOpaque(false);
    jsp.setBorder(null);
    jsp.getViewport().setOpaque(false);
    pnlOpponents.add(jsp).setBounds(90, 0, 900, 315);
  }

  public void drawPlayerName(String name) {
    lblPlayerName.setText("Player : "+name);
  }

  public void drawPlayerScore(int score) {
    lblScore.setText("Score : " + String.valueOf(score));
  }

  public void drawPlayerPosition(int pos) {
    lblPos.setText("Rank : " + String.valueOf(pos));
  }

  public void drawRoundNumber(int num) {
    lblRound.setText("Round : " + String.valueOf(num));
  }

  public void drawTweleveLetters(String[] letterArr) {
    lblL1.setText(letterArr[0]);
    lblL2.setText(letterArr[1]);
    lblL3.setText(letterArr[2]);
    lblL4.setText(letterArr[3]);
    lblL5.setText(letterArr[4]);
    lblL6.setText(letterArr[5]);
    lblL7.setText(letterArr[6]);
    lblL8.setText(letterArr[7]);
    lblL9.setText(letterArr[8]);
    lblL10.setText(letterArr[9]);
    lblL11.setText(letterArr[10]);
    lblL12.setText(letterArr[11]);
  }

  public Component getCompTop(int x) {
    Component[] cmpList = this.getComponents();

    /* for (int i = 0; i < cmpList.length; i++) {
        System.out.println(i + " " + cmpList[i].getY() + " " + cmpList[i].getName());
    }*/
    return this.pnlTop.getComponent(x);
  }

  public Component getCompBottom(int x) {
    Component[] cmpList = this.pnlBottom.getComponents();

    /*for (int i = 0; i < cmpList.length; i++) {
        System.out.println(i + " " + cmpList[i].getY() + " " + cmpList[i].getName());
    }*/
    return this.pnlBottom.getComponent(x);
  }

  public Component getCompTopBorder(int x) {
    Component[] cmpList = this.pnlTopBorder.getComponents();

    /*for (int i = 0; i < cmpList.length; i++) {
        System.out.println(i + " " + cmpList[i].getY() + " " + cmpList[i].getName());
    }*/
    return this.pnlTopBorder.getComponent(x);
  }

  public void setIcon_Vowels(ImageIcon icon) {
    this.btnVowels.setIcon(icon);
  }

  public void setIcon_Consonents(ImageIcon icon) {
    this.btnConsonents.setIcon(icon);
  }

  public void setIcon_Gen(ImageIcon icon) {
    this.btnGenerate.setIcon(icon);
  }

  public void setIcon_Submit(ImageIcon icon) {
    this.btnSubmit.setIcon(icon);
  }

  public void setTimer(String tmr) {
    this.lblTime.setFont(DataForUI.LCD);
    this.lblTime.setText(tmr);
  }
  
  private void chatClicked()
  {
      gameScreen.toggleChat();
  }

  public void setChannel(String channel)
  {
      channeltxt.setText("Server : "+channel);
  }
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        btnChat = new javax.swing.JLabel();
        pnlTop = new javax.swing.JPanel();
        pnlTopBorder = new javax.swing.JPanel();
        btnDisconnect = new javax.swing.JLabel();
        btnSettings = new javax.swing.JLabel();
        lblPlayerName = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();
        lblPos = new javax.swing.JLabel();
        lblRound = new javax.swing.JLabel();
        channeltxt = new javax.swing.JLabel();
        pnlOpponents = new javax.swing.JPanel();
        pnlBottom = new javax.swing.JPanel();
        lblL1 = new javax.swing.JLabel();
        lblL2 = new javax.swing.JLabel();
        lblL3 = new javax.swing.JLabel();
        lblL4 = new javax.swing.JLabel();
        lblL5 = new javax.swing.JLabel();
        lblL6 = new javax.swing.JLabel();
        lblL7 = new javax.swing.JLabel();
        lblL8 = new javax.swing.JLabel();
        lblL9 = new javax.swing.JLabel();
        lblL10 = new javax.swing.JLabel();
        lblL11 = new javax.swing.JLabel();
        lblL12 = new javax.swing.JLabel();
        btnVowels = new javax.swing.JLabel();
        btnConsonents = new javax.swing.JLabel();
        btnGenerate = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JLabel();
        txtAnswer = new javax.swing.JTextField();
        lblTime = new javax.swing.JLabel();

        jMenuItem1.setText("Swap Letter");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Clear");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem2);

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnChat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_openchat_n.png"))); // NOI18N
        btnChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnChatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnChatMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnChatMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnChatMouseReleased(evt);
            }
        });
        add(btnChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 250, 40, 108));

        pnlTop.setBackground(new java.awt.Color(102, 255, 255));
        pnlTop.setMaximumSize(new java.awt.Dimension(900, 72));
        pnlTop.setMinimumSize(new java.awt.Dimension(900, 72));
        pnlTop.setOpaque(false);
        pnlTop.setPreferredSize(new java.awt.Dimension(900, 72));
        pnlTop.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTopBorder.setName("dragingLabel"); // NOI18N
        pnlTopBorder.setOpaque(false);
        pnlTopBorder.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlTopBorderMouseDragged(evt);
            }
        });
        pnlTopBorder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTopBorderMousePressed(evt);
            }
        });
        pnlTopBorder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDisconnect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDisconnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_exit_n.png"))); // NOI18N
        btnDisconnect.setName("close"); // NOI18N
        pnlTopBorder.add(btnDisconnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 40, 40));

        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_settingst_n.png"))); // NOI18N
        btnSettings.setName("settings"); // NOI18N
        pnlTopBorder.add(btnSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, -1, 38));

        pnlTop.add(pnlTopBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 40));

        lblPlayerName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblPlayerName.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayerName.setText("Player : dcdushan");
        pnlTop.add(lblPlayerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 42, 180, 30));

        lblScore.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblScore.setForeground(new java.awt.Color(255, 255, 255));
        lblScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScore.setText("Score : 0");
        pnlTop.add(lblScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 42, 120, 30));

        lblPos.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblPos.setForeground(new java.awt.Color(255, 255, 255));
        lblPos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPos.setText("Rank : 1");
        pnlTop.add(lblPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 42, 90, 30));

        lblRound.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblRound.setForeground(new java.awt.Color(255, 255, 255));
        lblRound.setText("Round : 1");
        pnlTop.add(lblRound, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 42, 90, 30));

        channeltxt.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        channeltxt.setForeground(new java.awt.Color(204, 204, 204));
        channeltxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        channeltxt.setText("Server : NoNameYet");
        pnlTop.add(channeltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 42, 190, 30));

        add(pnlTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 72));

        pnlOpponents.setBackground(new java.awt.Color(51, 153, 255));
        pnlOpponents.setMaximumSize(new java.awt.Dimension(900, 315));
        pnlOpponents.setMinimumSize(new java.awt.Dimension(900, 315));
        pnlOpponents.setOpaque(false);

        javax.swing.GroupLayout pnlOpponentsLayout = new javax.swing.GroupLayout(pnlOpponents);
        pnlOpponents.setLayout(pnlOpponentsLayout);
        pnlOpponentsLayout.setHorizontalGroup(
            pnlOpponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        pnlOpponentsLayout.setVerticalGroup(
            pnlOpponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );

        add(pnlOpponents, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 75, 900, 315));

        pnlBottom.setBackground(new java.awt.Color(0, 255, 255));
        pnlBottom.setOpaque(false);
        pnlBottom.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblL1.setBackground(new java.awt.Color(0, 174, 239));
        lblL1.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL1.setForeground(new java.awt.Color(255, 255, 255));
        lblL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_initial.png"))); // NOI18N
        lblL1.setText("-");
        lblL1.setToolTipText("");
        lblL1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL1.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL1.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL1.setName("L1"); // NOI18N
        lblL1.setPreferredSize(new java.awt.Dimension(40, 43));
        lblL1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblL1MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblL1MouseClicked(evt);
            }
        });
        pnlBottom.add(lblL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 7, 69, 84));

        lblL2.setBackground(new java.awt.Color(0, 174, 239));
        lblL2.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL2.setForeground(new java.awt.Color(255, 255, 255));
        lblL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_initial.png"))); // NOI18N
        lblL2.setText("-");
        lblL2.setToolTipText("");
        lblL2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL2.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL2.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL2.setName("L2"); // NOI18N
        lblL2.setPreferredSize(new java.awt.Dimension(40, 43));
        lblL2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblL2MouseClicked(evt);
            }
        });
        pnlBottom.add(lblL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 7, 69, 84));

        lblL3.setBackground(new java.awt.Color(0, 174, 239));
        lblL3.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL3.setForeground(new java.awt.Color(255, 255, 255));
        lblL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL3.setText("-");
        lblL3.setToolTipText("");
        lblL3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL3.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL3.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL3.setName("L3"); // NOI18N
        lblL3.setPreferredSize(new java.awt.Dimension(40, 43));
        lblL3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblL3MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblL3MouseReleased(evt);
            }
        });
        pnlBottom.add(lblL3, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 7, 69, 84));

        lblL4.setBackground(new java.awt.Color(0, 174, 239));
        lblL4.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL4.setForeground(new java.awt.Color(255, 255, 255));
        lblL4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL4.setText("-");
        lblL4.setToolTipText("");
        lblL4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL4.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL4.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL4.setName("L4"); // NOI18N
        lblL4.setPreferredSize(new java.awt.Dimension(40, 43));
        lblL4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblL4MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblL4MouseReleased(evt);
            }
        });
        pnlBottom.add(lblL4, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 7, 69, 84));

        lblL5.setBackground(new java.awt.Color(0, 174, 239));
        lblL5.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL5.setForeground(new java.awt.Color(255, 255, 255));
        lblL5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL5.setText("-");
        lblL5.setToolTipText("");
        lblL5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL5.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL5.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL5.setName("L5"); // NOI18N
        lblL5.setPreferredSize(new java.awt.Dimension(40, 43));
        lblL5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblL5MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblL5MouseReleased(evt);
            }
        });
        pnlBottom.add(lblL5, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 7, 69, 84));

        lblL6.setBackground(new java.awt.Color(0, 174, 239));
        lblL6.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL6.setForeground(new java.awt.Color(255, 255, 255));
        lblL6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL6.setText("-");
        lblL6.setToolTipText("");
        lblL6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL6.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL6.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL6.setName("L6"); // NOI18N
        lblL6.setPreferredSize(new java.awt.Dimension(40, 43));
        lblL6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblL6MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblL6MouseReleased(evt);
            }
        });
        pnlBottom.add(lblL6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 7, 69, 84));

        lblL7.setBackground(new java.awt.Color(0, 174, 239));
        lblL7.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL7.setForeground(new java.awt.Color(255, 255, 255));
        lblL7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL7.setText("-");
        lblL7.setToolTipText("");
        lblL7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL7.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL7.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL7.setName("L7"); // NOI18N
        lblL7.setPreferredSize(new java.awt.Dimension(40, 43));
        lblL7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblL7MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblL7MouseReleased(evt);
            }
        });
        pnlBottom.add(lblL7, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 7, 69, 84));

        lblL8.setBackground(new java.awt.Color(0, 174, 239));
        lblL8.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL8.setForeground(new java.awt.Color(255, 255, 255));
        lblL8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL8.setText("-");
        lblL8.setToolTipText("");
        lblL8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL8.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL8.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL8.setName("L8"); // NOI18N
        lblL8.setPreferredSize(new java.awt.Dimension(40, 43));
        lblL8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblL8MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblL8MouseReleased(evt);
            }
        });
        pnlBottom.add(lblL8, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 7, 69, 84));

        lblL9.setBackground(new java.awt.Color(0, 174, 239));
        lblL9.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL9.setForeground(new java.awt.Color(255, 255, 255));
        lblL9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL9.setText("-");
        lblL9.setToolTipText("");
        lblL9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL9.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL9.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL9.setName("L9"); // NOI18N
        lblL9.setPreferredSize(new java.awt.Dimension(40, 43));
        lblL9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblL9MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblL9MouseReleased(evt);
            }
        });
        pnlBottom.add(lblL9, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 7, 69, 84));

        lblL10.setBackground(new java.awt.Color(0, 174, 239));
        lblL10.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL10.setForeground(new java.awt.Color(255, 255, 255));
        lblL10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL10.setText("-");
        lblL10.setToolTipText("");
        lblL10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL10.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL10.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL10.setName("L10"); // NOI18N
        lblL10.setPreferredSize(new java.awt.Dimension(40, 43));
        lblL10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblL10MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblL10MouseReleased(evt);
            }
        });
        pnlBottom.add(lblL10, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 7, 69, 84));

        lblL11.setBackground(new java.awt.Color(0, 174, 239));
        lblL11.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL11.setForeground(new java.awt.Color(255, 255, 255));
        lblL11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL11.setText("-");
        lblL11.setToolTipText("");
        lblL11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL11.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL11.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL11.setName("L11"); // NOI18N
        lblL11.setPreferredSize(new java.awt.Dimension(40, 43));
        lblL11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblL11MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblL11MouseReleased(evt);
            }
        });
        pnlBottom.add(lblL11, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 7, 69, 84));

        lblL12.setBackground(new java.awt.Color(0, 174, 239));
        lblL12.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL12.setForeground(new java.awt.Color(255, 255, 255));
        lblL12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL12.setText("-");
        lblL12.setToolTipText("");
        lblL12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL12.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL12.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL12.setName("L12"); // NOI18N
        lblL12.setPreferredSize(new java.awt.Dimension(40, 43));
        lblL12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblL12MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblL12MouseReleased(evt);
            }
        });
        pnlBottom.add(lblL12, new org.netbeans.lib.awtextra.AbsoluteConstraints(812, 7, 69, 84));

        btnVowels.setBackground(new java.awt.Color(0, 153, 153));
        btnVowels.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        btnVowels.setForeground(new java.awt.Color(255, 255, 255));
        btnVowels.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVowels.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_vowels_n.png"))); // NOI18N
        btnVowels.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVowels.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVowels.setName("Vowels"); // NOI18N
        btnVowels.setPreferredSize(new java.awt.Dimension(140, 32));
        btnVowels.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVowelsMouseClicked(evt);
            }
        });
        pnlBottom.add(btnVowels, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 204, 39));

        btnConsonents.setBackground(new java.awt.Color(0, 153, 153));
        btnConsonents.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        btnConsonents.setForeground(new java.awt.Color(255, 255, 255));
        btnConsonents.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnConsonents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_consonent_n.png"))); // NOI18N
        btnConsonents.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsonents.setName("Consonents"); // NOI18N
        btnConsonents.setPreferredSize(new java.awt.Dimension(140, 32));
        btnConsonents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConsonentsMouseClicked(evt);
            }
        });
        pnlBottom.add(btnConsonents, new org.netbeans.lib.awtextra.AbsoluteConstraints(478, 111, 204, 39));

        btnGenerate.setBackground(new java.awt.Color(0, 153, 153));
        btnGenerate.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        btnGenerate.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGenerate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_autosearch_n.png"))); // NOI18N
        btnGenerate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerate.setName("Gen"); // NOI18N
        btnGenerate.setPreferredSize(new java.awt.Dimension(140, 32));
        btnGenerate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGenerateMouseClicked(evt);
            }
        });
        pnlBottom.add(btnGenerate, new org.netbeans.lib.awtextra.AbsoluteConstraints(696, 138, 72, 72));

        btnSubmit.setBackground(new java.awt.Color(0, 153, 153));
        btnSubmit.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_submit_n.png"))); // NOI18N
        btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSubmit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSubmit.setName("Submit"); // NOI18N
        btnSubmit.setPreferredSize(new java.awt.Dimension(140, 32));
        pnlBottom.add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 108, 102, 102));

        txtAnswer.setBackground(new java.awt.Color(76, 76, 76));
        txtAnswer.setFont(new java.awt.Font("Trebuchet MS", 0, 36)); // NOI18N
        txtAnswer.setForeground(new java.awt.Color(255, 255, 255));
        txtAnswer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAnswer.setText("Answer");
        txtAnswer.setBorder(null);
        txtAnswer.setName("Answer"); // NOI18N
        txtAnswer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtAnswerMouseReleased(evt);
            }
        });
        pnlBottom.add(txtAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 167, 400, 39));

        lblTime.setFont(new java.awt.Font("Times New Roman", 0, 100)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("00");
        pnlBottom.add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));

        add(pnlBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 387, 900, 232));
    }// </editor-fold>//GEN-END:initComponents

  private void pnlTopBorderMouseDragged(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopBorderMouseDragged

    //System.out.println("dragged");

  }//GEN-LAST:event_pnlTopBorderMouseDragged

  private void pnlTopBorderMousePressed(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopBorderMousePressed
    //System.out.println("pressed");
  }//GEN-LAST:event_pnlTopBorderMousePressed

  private void btnGenerateMouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerateMouseClicked
    if (checkAllLetters()) {
      isAutoWordGenUsed = true;
      String[] WordArray={lblL1.getText(),
              lblL2.getText(),
              lblL3.getText(),
              lblL4.getText(),
              lblL5.getText(),
              lblL6.getText(),
              lblL7.getText(),
              lblL8.getText(),
              lblL9.getText(),
              lblL10.getText(),
              lblL11.getText(),
              lblL12.getText()};
      FLOG_LOGIC.WordAutoGenerator wordgen =
          new FLOG_LOGIC.WordAutoGenerator(WordArray);
      txtAnswer.setText(wordgen.getLongestWord());
    } else {
      JOptionPane.showMessageDialog(this, "Select All 12 letters first!");
    }

    // JOptionPane.showMessageDialog(this, "clicked");
  }//GEN-LAST:event_btnGenerateMouseClicked

  private void btnVowelsMouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVowelsMouseClicked
    for (JLabel x : letter_label_array) {
      if (x.getText().equalsIgnoreCase("-")) {
        x.setText(FLOG_LOGIC.Utils.getRandomVowel());
        break;
      }
    }
  }//GEN-LAST:event_btnVowelsMouseClicked

  private void btnConsonentsMouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsonentsMouseClicked

    for (JLabel x : letter_label_array) {
      if (x.getText().equalsIgnoreCase("-")) {
        x.setText(FLOG_LOGIC.Utils.getRandomConsonant());
        break;
      }
    }
  }//GEN-LAST:event_btnConsonentsMouseClicked

  private void lblL1MouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL1MouseReleased
    // TODO add your handling code here:
  }//GEN-LAST:event_lblL1MouseReleased

  private void lblL3MouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL3MouseReleased
    if (evt.isPopupTrigger() && !lblL3.getText().equalsIgnoreCase("-") && noOfSwaps < 2) {
      jPopupMenu1.show(
          this,
          pnlBottom.getX() + lblL3.getX() + evt.getX(),
          pnlBottom.getY() + lblL3.getY() + evt.getY());

      swapLetterLabelIndex = 0;
    }
  }//GEN-LAST:event_lblL3MouseReleased

  private void lblL4MouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL4MouseReleased
    if (evt.isPopupTrigger() && !lblL4.getText().equalsIgnoreCase("-") && noOfSwaps < 2) {
      // jPopupMenu1.show(this, evt.getXOnScreen() - 240, evt.getYOnScreen() - 50);
      jPopupMenu1.show(
          this,
          pnlBottom.getX() + lblL4.getX() + evt.getX(),
          pnlBottom.getY() + lblL4.getY() + evt.getY());
      swapLetterLabelIndex = 1;
    }
  }//GEN-LAST:event_lblL4MouseReleased

  private void lblL5MouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL5MouseReleased
    if (evt.isPopupTrigger() && !lblL5.getText().equalsIgnoreCase("-") && noOfSwaps < 2) {
      //jPopupMenu1.show(this, evt.getXOnScreen() - 240, evt.getYOnScreen() - 50);
      jPopupMenu1.show(
          this,
          pnlBottom.getX() + lblL5.getX() + evt.getX(),
          pnlBottom.getY() + lblL5.getY() + evt.getY());
      swapLetterLabelIndex = 2;
    }
  }//GEN-LAST:event_lblL5MouseReleased

  private void lblL6MouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL6MouseReleased
    if (evt.isPopupTrigger() && !lblL6.getText().equalsIgnoreCase("-") && noOfSwaps < 2) {
      //jPopupMenu1.show(this, evt.getXOnScreen() - 240, evt.getYOnScreen() - 50);
      jPopupMenu1.show(
          this,
          pnlBottom.getX() + lblL6.getX() + evt.getX(),
          pnlBottom.getY() + lblL6.getY() + evt.getY());
      swapLetterLabelIndex = 3;
    }
  }//GEN-LAST:event_lblL6MouseReleased

  private void lblL7MouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL7MouseReleased
    if (evt.isPopupTrigger() && !lblL7.getText().equalsIgnoreCase("-") && noOfSwaps < 2) {
      //jPopupMenu1.show(this, evt.getXOnScreen() - 240, evt.getYOnScreen() - 50);
      jPopupMenu1.show(
          this,
          pnlBottom.getX() + lblL7.getX() + evt.getX(),
          pnlBottom.getY() + lblL7.getY() + evt.getY());
      swapLetterLabelIndex = 4;
    }
  }//GEN-LAST:event_lblL7MouseReleased

  private void lblL8MouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL8MouseReleased
    if (evt.isPopupTrigger() && !lblL8.getText().equalsIgnoreCase("-") && noOfSwaps < 2) {
      //jPopupMenu1.show(this, evt.getXOnScreen() - 240, evt.getYOnScreen() - 50);
      jPopupMenu1.show(
          this,
          pnlBottom.getX() + lblL8.getX() + evt.getX(),
          pnlBottom.getY() + lblL8.getY() + evt.getY());
      swapLetterLabelIndex = 5;
    }
  }//GEN-LAST:event_lblL8MouseReleased

  private void lblL9MouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL9MouseReleased
    if (evt.isPopupTrigger() && !lblL9.getText().equalsIgnoreCase("-") && noOfSwaps < 2) {
      //jPopupMenu1.show(this, evt.getXOnScreen() - 240, evt.getYOnScreen() - 50);
      jPopupMenu1.show(
          this,
          pnlBottom.getX() + lblL9.getX() + evt.getX(),
          pnlBottom.getY() + lblL9.getY() + evt.getY());
      swapLetterLabelIndex = 6;
    }
  }//GEN-LAST:event_lblL9MouseReleased

  private void lblL10MouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL10MouseReleased
    if (evt.isPopupTrigger() && !lblL10.getText().equalsIgnoreCase("-") && noOfSwaps < 2) {
      //jPopupMenu1.show(this, evt.getXOnScreen() - 240, evt.getYOnScreen() - 50);
      jPopupMenu1.show(
          this,
          pnlBottom.getX() + lblL10.getX() + evt.getX(),
          pnlBottom.getY() + lblL10.getY() + evt.getY());
      swapLetterLabelIndex = 7;
    }
  }//GEN-LAST:event_lblL10MouseReleased

  private void lblL11MouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL11MouseReleased
    if (evt.isPopupTrigger() && !lblL11.getText().equalsIgnoreCase("-") && noOfSwaps < 2) {
      // jPopupMenu1.show(this, evt.getXOnScreen() - 240, evt.getYOnScreen() - 50);
      jPopupMenu1.show(
          this,
          pnlBottom.getX() + lblL11.getX() + evt.getX(),
          pnlBottom.getY() + lblL11.getY() + evt.getY());
      swapLetterLabelIndex = 8;
    }
  }//GEN-LAST:event_lblL11MouseReleased

  private void lblL12MouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL12MouseReleased
    if (evt.isPopupTrigger() && !lblL12.getText().equalsIgnoreCase("-") && noOfSwaps < 2) {
      //jPopupMenu1.show(this, evt.getXOnScreen() - 240, evt.getYOnScreen() - 50);
      jPopupMenu1.show(
          this,
          pnlBottom.getX() + lblL12.getX() + evt.getX(),
          pnlBottom.getY() + lblL12.getY() + evt.getY());
      swapLetterLabelIndex = 9;
    }
  }//GEN-LAST:event_lblL12MouseReleased

  private void lblL12MouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL12MouseClicked
    if (!lblL12.getText().equalsIgnoreCase("-") && bl12) {
      if (txtAnswer.getText().equalsIgnoreCase("Answer")) {
        txtAnswer.setText(lblL12.getText());
      } else {
        txtAnswer.setText(txtAnswer.getText() + lblL12.getText());
      }
      lblL12.setEnabled(false);
      bl12 = false;
    }
  }//GEN-LAST:event_lblL12MouseClicked

  private void jMenuItem1ActionPerformed(
      java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

    if (swapLetterLabelIndex != -99) {
      JLabel clickedLable = letter_label_array[swapLetterLabelIndex];
      clickedLable.setText(FLOG_LOGIC.Utils.SwapLetter(clickedLable.getText()));
      noOfSwaps = noOfSwaps + 1;
    }
  }//GEN-LAST:event_jMenuItem1ActionPerformed

  private void txtAnswerMouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAnswerMouseReleased
    if (evt.isPopupTrigger()) {
      jPopupMenu2.show(this, evt.getXOnScreen() - 240, evt.getYOnScreen() - 50);
    }
  }//GEN-LAST:event_txtAnswerMouseReleased

  private void jMenuItem2ActionPerformed(
      java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    txtAnswer.setText("");
    lblL1.setEnabled(true);
    lblL2.setEnabled(true);
    lblL3.setEnabled(true);
    lblL4.setEnabled(true);
    lblL5.setEnabled(true);
    lblL6.setEnabled(true);
    lblL7.setEnabled(true);
    lblL8.setEnabled(true);
    lblL9.setEnabled(true);
    lblL10.setEnabled(true);
    lblL11.setEnabled(true);
    lblL12.setEnabled(true);
    bl1 = true;
    bl2 = true;
    bl3 = true;
    bl4 = true;
    bl5 = true;
    bl6 = true;
    bl7 = true;
    bl8 = true;
    bl9 = true;
    bl10 = true;
    bl11 = true;
    bl12 = true;
  }//GEN-LAST:event_jMenuItem2ActionPerformed

  private void lblL1MouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL1MouseClicked
    if (!lblL1.getText().equalsIgnoreCase("-") && bl1) {
      if (txtAnswer.getText().equalsIgnoreCase("Answer")) {
        txtAnswer.setText(lblL1.getText());
      } else {
        txtAnswer.setText(txtAnswer.getText() + lblL1.getText());
      }
      lblL1.setEnabled(false);
      bl1 = false;
    }
  }//GEN-LAST:event_lblL1MouseClicked

  private void lblL2MouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL2MouseClicked
    if (!lblL2.getText().equalsIgnoreCase("-") && bl2) {
      if (txtAnswer.getText().equalsIgnoreCase("Answer")) {
        txtAnswer.setText(lblL2.getText());
      } else {
        txtAnswer.setText(txtAnswer.getText() + lblL2.getText());
      }
      lblL2.setEnabled(false);
      bl2 = false;
    }
  }//GEN-LAST:event_lblL2MouseClicked

  private void lblL3MouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL3MouseClicked
    if (!lblL3.getText().equalsIgnoreCase("-") && bl3) {
      if (txtAnswer.getText().equalsIgnoreCase("Answer")) {
        txtAnswer.setText(lblL3.getText());
      } else {
        txtAnswer.setText(txtAnswer.getText() + lblL3.getText());
      }
      lblL3.setEnabled(false);
      bl3 = false;
    }
  }//GEN-LAST:event_lblL3MouseClicked

  private void lblL4MouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL4MouseClicked
    if (!lblL4.getText().equalsIgnoreCase("-") && bl4) {
      if (txtAnswer.getText().equalsIgnoreCase("Answer")) {
        txtAnswer.setText(lblL4.getText());
      } else {
        txtAnswer.setText(txtAnswer.getText() + lblL4.getText());
      }
      lblL4.setEnabled(false);
      bl4 = false;
    }
  }//GEN-LAST:event_lblL4MouseClicked

  private void lblL5MouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL5MouseClicked
    if (!lblL5.getText().equalsIgnoreCase("-") && bl5) {
      if (txtAnswer.getText().equalsIgnoreCase("Answer")) {
        txtAnswer.setText(lblL5.getText());
      } else {
        txtAnswer.setText(txtAnswer.getText() + lblL5.getText());
      }
      lblL5.setEnabled(false);
      bl5 = false;
    }
  }//GEN-LAST:event_lblL5MouseClicked

  private void lblL6MouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL6MouseClicked
    if (!lblL6.getText().equalsIgnoreCase("-") && bl6) {
      if (txtAnswer.getText().equalsIgnoreCase("Answer")) {
        txtAnswer.setText(lblL6.getText());
      } else {
        txtAnswer.setText(txtAnswer.getText() + lblL6.getText());
      }
      lblL6.setEnabled(false);
      bl6 = false;
    }
  }//GEN-LAST:event_lblL6MouseClicked

  private void lblL7MouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL7MouseClicked
    if (!lblL7.getText().equalsIgnoreCase("-") && bl7) {
      if (txtAnswer.getText().equalsIgnoreCase("Answer")) {
        txtAnswer.setText(lblL7.getText());
      } else {
        txtAnswer.setText(txtAnswer.getText() + lblL7.getText());
      }
      lblL7.setEnabled(false);
      bl7 = false;
    }
  }//GEN-LAST:event_lblL7MouseClicked

  private void lblL8MouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL8MouseClicked
    if (!lblL8.getText().equalsIgnoreCase("-") && bl8) {
      if (txtAnswer.getText().equalsIgnoreCase("Answer")) {
        txtAnswer.setText(lblL8.getText());
      } else {
        txtAnswer.setText(txtAnswer.getText() + lblL8.getText());
      }
      lblL8.setEnabled(false);
      bl8 = false;
    }
  }//GEN-LAST:event_lblL8MouseClicked

  private void lblL9MouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL9MouseClicked
    if (!lblL9.getText().equalsIgnoreCase("-") && bl9) {
      if (txtAnswer.getText().equalsIgnoreCase("Answer")) {
        txtAnswer.setText(lblL9.getText());
      } else {
        txtAnswer.setText(txtAnswer.getText() + lblL9.getText());
      }
      lblL9.setEnabled(false);
      bl9 = false;
    }
  }//GEN-LAST:event_lblL9MouseClicked

  private void lblL10MouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL10MouseClicked
    if (!lblL10.getText().equalsIgnoreCase("-") && bl10) {
      if (txtAnswer.getText().equalsIgnoreCase("Answer")) {
        txtAnswer.setText(lblL10.getText());
      } else {
        txtAnswer.setText(txtAnswer.getText() + lblL10.getText());
      }
      lblL10.setEnabled(false);
      bl10 = false;
    }
  }//GEN-LAST:event_lblL10MouseClicked

  private void lblL11MouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblL11MouseClicked
    if (!lblL11.getText().equalsIgnoreCase("-") && bl11) {
      if (txtAnswer.getText().equalsIgnoreCase("Answer")) {
        txtAnswer.setText(lblL11.getText());
      } else {
        txtAnswer.setText(txtAnswer.getText() + lblL11.getText());
      }
      lblL11.setEnabled(false);
      bl11 = false;
    }
  }//GEN-LAST:event_lblL11MouseClicked

    private void btnChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChatMouseClicked
                chatClicked();
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_openchat_h.png"));
                btnChat.setIcon(imgIcon);

    }//GEN-LAST:event_btnChatMouseClicked

    private void btnChatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChatMouseEntered
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_openchat_h.png"));
        btnChat.setIcon(imgIcon);
    }//GEN-LAST:event_btnChatMouseEntered

    private void btnChatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChatMouseExited
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_openchat_n.png"));
        btnChat.setIcon(imgIcon);
    }//GEN-LAST:event_btnChatMouseExited

    private void btnChatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChatMousePressed
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_openchat_c.png"));
        btnChat.setIcon(imgIcon);
    }//GEN-LAST:event_btnChatMousePressed

    private void btnChatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChatMouseReleased
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_openchat_h.png"));
        btnChat.setIcon(imgIcon);
    }//GEN-LAST:event_btnChatMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnChat;
    private javax.swing.JLabel btnConsonents;
    private javax.swing.JLabel btnDisconnect;
    private javax.swing.JLabel btnGenerate;
    private javax.swing.JLabel btnSettings;
    private javax.swing.JLabel btnSubmit;
    private javax.swing.JLabel btnVowels;
    private javax.swing.JLabel channeltxt;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JLabel lblL1;
    private javax.swing.JLabel lblL10;
    private javax.swing.JLabel lblL11;
    private javax.swing.JLabel lblL12;
    private javax.swing.JLabel lblL2;
    private javax.swing.JLabel lblL3;
    private javax.swing.JLabel lblL4;
    private javax.swing.JLabel lblL5;
    private javax.swing.JLabel lblL6;
    private javax.swing.JLabel lblL7;
    private javax.swing.JLabel lblL8;
    private javax.swing.JLabel lblL9;
    private javax.swing.JLabel lblPlayerName;
    private javax.swing.JLabel lblPos;
    private javax.swing.JLabel lblRound;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblTime;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlOpponents;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel pnlTopBorder;
    private javax.swing.JTextField txtAnswer;
    // End of variables declaration//GEN-END:variables
}
