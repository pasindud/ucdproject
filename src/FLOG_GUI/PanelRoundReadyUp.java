/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Dushan Galappaththi
 */
public class PanelRoundReadyUp extends javax.swing.JPanel {

  /**
   * Creates new form PanelRoundReadyUp
   */
  private Image bg;
  DataForUI DataForUI;
  GameScreen gameScreen;
  int mouseX =0;
  int mouseY=0;

  public PanelRoundReadyUp(GameScreen gameScreen) {
    initComponents();
    bg = new ImageIcon(getClass().getResource("/images/bg_roundReadyUp.png")).getImage();
    DataForUI = new DataForUI();
    this.gameScreen = gameScreen;
    lblTitlePlayersInfo.setVisible(false);
  }

  public void setTimer(String tmr) {
     //This line causing null pointer exception when running outside netbeans
    this.lblReadyUpTimer.setFont(new Font("Arial Rounded MT Bold", 0, 55));
    this.lblReadyUpTimer.setText(tmr);
  }

  public void setRound(String round) {
    this.lblRound.setText(round);
  }

  private void updatePlayersUI() {
    pnlPlayerInfoContainer.removeAll();
    revalidate();
    repaint();
  }

  /**
   * Dynamically display round score board/ready up player list
   */
  public void drawPlayers() {
    boolean isFirstRound = true;
    DataForUI.preparePlayerArrayForUI();
    pnlPlayerInfoContainer.setLayout(null);
    updatePlayersUI();
    JScrollPane jsp;
    JPanel pnlA = new JPanel();
    pnlA.setOpaque(false);
    pnlA.setLayout(new BoxLayout(pnlA, BoxLayout.PAGE_AXIS));
    pnlA.setBounds(0, 0, 700, 290);
    if (DataForUI.RoundNum > 1) {
      isFirstRound = false;
    }
    for (int i = 0; i < DataForUI.PdArray.length; i++) {
      pnlA.add(new ReadyPlayersUI(isFirstRound, i));
    }

    jsp = new JScrollPane(pnlA);
    jsp.setOpaque(false);
    jsp.setBorder(null);
    jsp.getViewport().setOpaque(false);
    pnlPlayerInfoContainer.add(jsp).setBounds(-82, 0, 800, 290);
  }

  public void setTitlePlayers(boolean isPlayers) {
    if (isPlayers) {
      lblTitlePlayers.setVisible(true);
      lblTitlePlayersInfo.setVisible(false);
    } else {
      lblTitlePlayers.setVisible(false);
      lblTitlePlayersInfo.setVisible(true);
    }
  }
  private void chatClicked()
  {
      gameScreen.toggleChat();
  }
  @Override
  protected void paintComponent(Graphics g) {
    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblReadyUpTimer = new javax.swing.JLabel();
        lblRound = new javax.swing.JLabel();
        pnlPlayerInfoContainer = new javax.swing.JPanel();
        lblTitlePlayers = new javax.swing.JLabel();
        lblTitlePlayersInfo = new javax.swing.JLabel();
        btnChat = new javax.swing.JLabel();
        pnlTopBorder = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(900, 619));
        setMinimumSize(new java.awt.Dimension(900, 619));
        setPreferredSize(new java.awt.Dimension(900, 619));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblReadyUpTimer.setFont(new java.awt.Font("Times New Roman", 0, 40)); // NOI18N
        lblReadyUpTimer.setForeground(new java.awt.Color(255, 255, 255));
        lblReadyUpTimer.setText("  ");
        add(lblReadyUpTimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(577, 140, -1, -1));

        lblRound.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        lblRound.setForeground(new java.awt.Color(255, 255, 255));
        lblRound.setText("1");
        add(lblRound, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 142, -1, -1));

        pnlPlayerInfoContainer.setBackground(new java.awt.Color(204, 255, 255));
        pnlPlayerInfoContainer.setOpaque(false);
        pnlPlayerInfoContainer.setLayout(null);
        add(pnlPlayerInfoContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 240, 650, 290));

        lblTitlePlayers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lbl_players.png"))); // NOI18N
        lblTitlePlayers.setToolTipText("");
        add(lblTitlePlayers, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 198, -1, -1));

        lblTitlePlayersInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lbl_players_full.png"))); // NOI18N
        add(lblTitlePlayersInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 198, -1, -1));

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
        add(pnlTopBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 38));
    }// </editor-fold>//GEN-END:initComponents

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

    private void pnlTopBorderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopBorderMousePressed
         mouseX = evt.getX();
         mouseY = evt.getY();
    }//GEN-LAST:event_pnlTopBorderMousePressed

    private void pnlTopBorderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopBorderMouseDragged
       gameScreen.moveScreen(evt.getXOnScreen(), evt.getYOnScreen(), mouseX, mouseY);
    }//GEN-LAST:event_pnlTopBorderMouseDragged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnChat;
    private javax.swing.JLabel lblReadyUpTimer;
    private javax.swing.JLabel lblRound;
    private javax.swing.JLabel lblTitlePlayers;
    private javax.swing.JLabel lblTitlePlayersInfo;
    private javax.swing.JPanel pnlPlayerInfoContainer;
    private javax.swing.JPanel pnlTopBorder;
    // End of variables declaration//GEN-END:variables
}
