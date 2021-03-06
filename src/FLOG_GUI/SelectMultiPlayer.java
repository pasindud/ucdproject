/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

import static FLOG_GUI.GameScreen.panelPlaying;
import FLOG_LOGIC.*;
import FLOG_LOGIC.Multiplayer;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ChatRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.RoomRequestListener;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

/**
 *
 * @author Pasindu
 */
public class SelectMultiPlayer extends javax.swing.JPanel {

  private Image bg;
  int mouseX = 0;
  int mouseY = 0;

  CardLayout mainPanelCards = new CardLayout();
  /** Name of the channel the user is hosting or joined. */
  public String channelName = null;
  /** Name of the player. */
  private String playerName = null;
  int posX = 0, posY = 0;
  private boolean IS_USER_JOINED = false;
  public boolean IS_THE_SERVER = false;
  GameScreen gameScreen;
  // Consumer producer client
  //    Catcher clientCatcher = new Catcher();
  //    Thrower clientThrower = new Thrower();

  Multiplayer multiplayer = new Multiplayer();
  Thread backgroundClientQueueCheck;
  private WarpClient myGame;
  /**
   * Creates new form SelectMultiPlayer
   */
  public SelectMultiPlayer(GameScreen gameScreen) {
    this.gameScreen = gameScreen;
    initComponents();
    bg = new ImageIcon(getClass().getResource("/images/bg_multiplayer.png")).getImage();
    jScrollPane2.setVisible(false);
    jScrollPane4.setVisible(false);
    //txtChannelName.setVisible(false);
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
  }

  /**
   * Start the server queues for the give {@code channelName}
   */
  private void startServerClicked() {
    btnStartServer.setEnabled(false);
    //channelName = txtChannelName.getText();
    channelName = cmbChannels.getEditor().getItem().toString();
      System.out.println(channelName);
    //        multiplayer.createServer(channelName);
    setServerStatus("Starting Server");

    IS_THE_SERVER = true;
    txtPlayerName.setEnabled(false);
    btnStartGame.setEnabled(false);
    //gameScreen.channelName = txtChannelName.getText();
    gameScreen.channelName = cmbChannels.getEditor().getItem().toString();
    final String channelName = this.channelName;
    setServerStatus("Server will be started soon Server");
    // Server listen's to it's queue.
    Thread thread =
        new Thread(channelName) {
          @Override
          public void run() {
            //                multiplayer.createServer(channelName);
            setServerStatus("Sever started");
            //                FLOG_LOGIC.startServerApp(channelName);
            //
            try {
              File file =
                  new File(
                      FLOG_LOGIC.class
                          .getProtectionDomain()
                          .getCodeSource()
                          .getLocation()
                          .toURI()
                          .getPath());
              File f = new File(System.getProperty("java.class.path"));
              File dir = f.getAbsoluteFile().getParentFile();
              String path2 = dir.toString();

              String path = file.getAbsolutePath();
              //            Runtime.getRuntime().exec("java -cp " + path + " FLOG_LOGIC.FLOG_LOGIC " + channelName);
              //            System.out.println("running commadn path -  " + path2 );
            } catch (Exception e) {

            }
          }
        };
    thread.setDaemon(true);
    thread.start();
  }

  String serverQueueName;
  /**
   * Join the given server {@code channelName}
   */
  private void joinServerClicked() {
    txtPlayerName.setEnabled(false);
    btnJoin.setEnabled(false);
    if (IS_USER_JOINED) {
      return;
    }
    IS_USER_JOINED = true;
    setClientStatus("Joining server");
    gameScreen.channelName = cmbChannels.getEditor().getItem().toString();
    DataForUI.currentChannel=gameScreen.channelName;
    gameScreen.username = txtPlayerName.getText();
    gameScreen.selectMultiplayerJoinServerClick();
    DataForUI.isConnectedToServer=true;
  }

  /**
   * Set the status from the queue reading.
   */
  public synchronized void setServerStatus(String status) {
    System.out.println("Set status [SelectMulti] " + status);
    String text = jTextPane2.getText();
    jTextPane2.setText(text += "\n" + status);
  }

  /**
   * Set of the server status.
   */
  public synchronized void setClientStatus(String status) {
    System.out.println("Set status client - [SelectMulti] " + status);
    String text = txtClientMessages.getText();
    txtClientMessages.setText(text += "\n" + status);
  }

  public void setPlayerName()
  {
      txtPlayerName.setText(DataForUI.currentUsername);
  }
  
  public void enableButtons()
  {
      btnJoin.setEnabled(true);
      btnStartGame.setEnabled(true);
      btnStartServer.setEnabled(true);
  }
  
  private void chatClicked()
  {
      gameScreen.toggleChat();
     
  }
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtClientMessages = new javax.swing.JTextArea();
        pnlTopBorder = new javax.swing.JPanel();
        btnExit = new javax.swing.JLabel();
        btnStartServer = new javax.swing.JLabel();
        btnJoin = new javax.swing.JLabel();
        btnStartGame = new javax.swing.JLabel();
        txtPlayerName = new javax.swing.JTextField();
        cmbChannels = new javax.swing.JComboBox<>();
        btnChat = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextPane2.setText("Server messages - ");
        jTextPane2.setToolTipText("");
        jScrollPane2.setViewportView(jTextPane2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 230, 145));

        txtClientMessages.setColumns(20);
        txtClientMessages.setRows(5);
        txtClientMessages.setText("Client messages -");
        jScrollPane4.setViewportView(txtClientMessages);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, -1, 145));

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

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_exit_n.png"))); // NOI18N
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnExitMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnExitMouseReleased(evt);
            }
        });
        pnlTopBorder.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(859, 0, -1, 38));

        add(pnlTopBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 38));

        btnStartServer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_hostserver_n.png"))); // NOI18N
        btnStartServer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStartServerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnStartServerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnStartServerMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnStartServerMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnStartServerMouseReleased(evt);
            }
        });
        add(btnStartServer, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 388, -1, -1));

        btnJoin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_joinserver_n.png"))); // NOI18N
        btnJoin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnJoinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnJoinMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnJoinMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnJoinMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnJoinMouseReleased(evt);
            }
        });
        add(btnJoin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 388, -1, -1));

        btnStartGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_startgamem_n.png"))); // NOI18N
        btnStartGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStartGameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnStartGameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnStartGameMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnStartGameMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnStartGameMouseReleased(evt);
            }
        });
        add(btnStartGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 388, -1, -1));

        txtPlayerName.setText("playerNameDc");
        txtPlayerName.setBorder(null);
        txtPlayerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlayerNameActionPerformed(evt);
            }
        });
        add(txtPlayerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 329, 190, 35));

        cmbChannels.setEditable(true);
        cmbChannels.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "dc", "ab" }));
        add(cmbChannels, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 274, 208, 34));

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
    }// </editor-fold>//GEN-END:initComponents

  private void pnlTopBorderMouseDragged(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopBorderMouseDragged
    // TODO add your handling code here:
    gameScreen.moveScreen(evt.getXOnScreen(), evt.getYOnScreen(), mouseX, mouseY);
  }//GEN-LAST:event_pnlTopBorderMouseDragged

  private void pnlTopBorderMousePressed(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopBorderMousePressed
    // TODO add your handling code here:
    mouseX = evt.getX();
    mouseY = evt.getY();
  }//GEN-LAST:event_pnlTopBorderMousePressed

  private void btnExitMouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
    // TODO add your handling code here:
    gameScreen.changeScreen(DataForUI.STR_MAINMENU, DataForUI.SelectMultiplayer);
  }//GEN-LAST:event_btnExitMouseClicked

  private void btnStartServerMouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartServerMouseClicked
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_hostserver_h.png"));
    btnStartServer.setIcon(imgIcon);

    startServerClicked();
  }//GEN-LAST:event_btnStartServerMouseClicked

  private void btnJoinMouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJoinMouseClicked
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_joinserver_h.png"));
    btnJoin.setIcon(imgIcon);

    joinServerClicked();
    
  }//GEN-LAST:event_btnJoinMouseClicked

  private void btnStartGameMouseClicked(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartGameMouseClicked
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_startgamem_h.png"));
    btnStartGame.setIcon(imgIcon);
    /*btnStartGame.setEnabled(false);
    channelName = txtChannelName.getText();
    playerName = txtPlayerName.getText();
    server.startGame();*/
    btnStartGame.setEnabled(false);
    channelName = cmbChannels.getEditor().getItem().toString();
    playerName = txtPlayerName.getText();
    // Only the server user can press this.
    gameScreen.selectMulitplayerStartGameClick();
  }//GEN-LAST:event_btnStartGameMouseClicked

  private void txtPlayerNameActionPerformed(
      java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlayerNameActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_txtPlayerNameActionPerformed

  private void btnStartServerMousePressed(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartServerMousePressed
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_hostserver_c.png"));
    btnStartServer.setIcon(imgIcon);
  }//GEN-LAST:event_btnStartServerMousePressed

  private void btnStartServerMouseEntered(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartServerMouseEntered
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_hostserver_h.png"));
    btnStartServer.setIcon(imgIcon);
  }//GEN-LAST:event_btnStartServerMouseEntered

  private void btnStartServerMouseExited(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartServerMouseExited
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_hostserver_n.png"));
    btnStartServer.setIcon(imgIcon);
  }//GEN-LAST:event_btnStartServerMouseExited

  private void btnStartServerMouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartServerMouseReleased
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_hostserver_h.png"));
    btnStartServer.setIcon(imgIcon);
  }//GEN-LAST:event_btnStartServerMouseReleased

  private void btnJoinMouseEntered(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJoinMouseEntered
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_joinserver_h.png"));
    btnJoin.setIcon(imgIcon);
  }//GEN-LAST:event_btnJoinMouseEntered

  private void btnJoinMouseExited(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJoinMouseExited
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_joinserver_n.png"));
    btnJoin.setIcon(imgIcon);
  }//GEN-LAST:event_btnJoinMouseExited

  private void btnJoinMousePressed(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJoinMousePressed
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_joinserver_c.png"));
    btnJoin.setIcon(imgIcon);
  }//GEN-LAST:event_btnJoinMousePressed

  private void btnJoinMouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJoinMouseReleased
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_joinserver_h.png"));
    btnJoin.setIcon(imgIcon);
  }//GEN-LAST:event_btnJoinMouseReleased

  private void btnStartGameMouseEntered(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartGameMouseEntered
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_startgamem_h.png"));
    btnStartGame.setIcon(imgIcon);
  }//GEN-LAST:event_btnStartGameMouseEntered

  private void btnStartGameMouseReleased(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartGameMouseReleased
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_startgamem_h.png"));
    btnStartGame.setIcon(imgIcon);
  }//GEN-LAST:event_btnStartGameMouseReleased

  private void btnStartGameMouseExited(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartGameMouseExited
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_startgamem_n.png"));
    btnStartGame.setIcon(imgIcon);
  }//GEN-LAST:event_btnStartGameMouseExited

  private void btnStartGameMousePressed(
      java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartGameMousePressed
    // TODO add your handling code here:
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_startgamem_c.png"));
    btnStartGame.setIcon(imgIcon);
  }//GEN-LAST:event_btnStartGameMousePressed

    private void btnChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChatMouseClicked
        // TODO add your handling code here:
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

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_exit_h.png"));
        btnExit.setIcon(imgIcon);
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_exit_n.png"));
        btnExit.setIcon(imgIcon);
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMousePressed
      ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_exit_h.png"));
        btnExit.setIcon(imgIcon);
    }//GEN-LAST:event_btnExitMousePressed

    private void btnExitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseReleased
       ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_exit_h.png"));
        btnExit.setIcon(imgIcon);
    }//GEN-LAST:event_btnExitMouseReleased

  // Listen

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnChat;
    private javax.swing.JLabel btnExit;
    private javax.swing.JLabel btnJoin;
    private javax.swing.JLabel btnStartGame;
    private javax.swing.JLabel btnStartServer;
    private javax.swing.JComboBox<String> cmbChannels;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JPanel pnlTopBorder;
    private javax.swing.JTextArea txtClientMessages;
    private javax.swing.JTextField txtPlayerName;
    // End of variables declaration//GEN-END:variables

}





