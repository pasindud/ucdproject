/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;
import static FLOG_GUI.GameScreen.panelPlaying;
import FLOG_LOGIC.*;
import FLOG_LOGIC.Multiplayer;

import FLOG_LOGIC.Thrower;
import FLOG_LOGIC.ThrowListener;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    int mouseX=0;
    int mouseY=0;
    
    
    CardLayout mainPanelCards = new CardLayout();
    /** Name of the channel the user is hosting or joined. */
    public String channelName = null;
    /** Name of the player. */
    private String playerName = null;
    int posX=0,posY=0;
    private boolean IS_USER_JOINED = false;
    
    GameScreen gameScreen;
    // Consumer producer client
    Catcher clientCatcher = new Catcher();
    Thrower clientThrower = new Thrower();
    
    Multiplayer multiplayer = new Multiplayer();
    Thread backgroundClientQueueCheck;
    
    /*public SelectMultiPlayer(){
        this.gameScreen = new GameScreen();
    }*/
    
    /**
     * Creates new form SelectMultiPlayer
     */
    public SelectMultiPlayer(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        initComponents();
        clientThrower.addThrowListener(clientCatcher);
        bg = new ImageIcon(getClass().getResource("/images/bg_multiplayer.png")).getImage();
        jButton1.setVisible(false);//What is this button???? [IMPORTANT]
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
    
    
    
    private Server server = null;
    
    /**
     * Decode the message received by the client
     */
    public synchronized void decodeClientMessage(String message) {
        setClientStatus("Message in client - " + message);
        String[] segments = message.split(" ");
        if (segments.length < 2) {
            return;
        }
        String code = segments[0];
        String content = segments[1];
        switch (code) {
            case "200":
                // User joined the correctly.
                break;
            case "201":
                // Format - 201 startgame <player names>
                if (segments.length != 3) {
                    System.err.println("Start game does not have a name list");
                    return;
                }
                
                // Channing the UI to the game playing deck.
                /*this.playerName = txtPlayerName.getText().trim();
                // TODO add player names.
                if (backgroundClientQueueCheck != null) {
                    backgroundClientQueueCheck.interrupt();
                }
                MultiPlayerTestGUI gui = new MultiPlayerTestGUI(
                        this.channelName, 
                        this.playerName, 
                        Utils.getPlayerNamesFromString(segments[2]));
                gui.setSize(new Dimension(800, 340));
                gui.setVisible(true);*/
                
                gameScreen.channelName = channelName;
                gameScreen.username = playerName;
                gameScreen.otherPlayerNames = new ArrayList<String>();
                gameScreen.otherPlayerNames = Utils.getPlayerNamesFromString(segments[2]);
                gameScreen.setupMultiplayerForGamePlay();
                gameScreen.changeScreen(DataForUI.STR_ROUNDREADYUP, DataForUI.SelectMultiplayer);
                break;
        }
    }
   
    /**
     * Listens to messages thrown by checkQueueThread.
     */
    public class Catcher implements ThrowListener {
        @Override
        public void Catch(String message) {
            System.out.println("Caught " + message);
            decodeClientMessage(message);
        }
    }
    
    /**
     * Start the server queues for the give {@code channelName}
     */
    private void startServerClicked() {
        btnStartServer.setEnabled(false);
        channelName = txtChannelName.getText();
        multiplayer.createServer(channelName);
        setServerStatus("Starting Server");
        // Server listen's to it's queue.
        server = new Server(channelName);
        server.start();
        setServerStatus("Sever started");
    }

    /**
     * Join the given server {@code channelName}
     */
    private void joinServerClicked() {
         btnJoin.setEnabled(false);
        if (IS_USER_JOINED) {
            return;
        }
        IS_USER_JOINED = true;
        setClientStatus("Joining server");
        channelName = txtChannelName.getText();
        playerName = txtPlayerName.getText();
        multiplayer.joinNewPlayer(playerName, channelName);

        // Listen to the client's queue.
        String clientQueueName = multiplayer.getClientQueue(channelName, playerName);
        backgroundClientQueueCheck = new CheckQueueThread(clientQueueName, clientThrower);
        backgroundClientQueueCheck.start();
    }

    /**
     * Set the status from the queue reading.
     */
    public synchronized void setServerStatus(String status) {
        System.out.println("Set status " + status);
        String text = jTextPane2.getText();
        jTextPane2.setText(text += "\n" + status);
    }
    
    
    /** Set of the server status. */
    public synchronized void setClientStatus(String status){
        System.out.println("Set status client - " + status);
        String text = txtClientMessages.getText();
        txtClientMessages.setText(text += "\n" + status);
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
        jButton1 = new javax.swing.JButton();
        pnlTopBorder = new javax.swing.JPanel();
        btnExit = new javax.swing.JLabel();
        btnStartServer = new javax.swing.JLabel();
        btnJoin = new javax.swing.JLabel();
        btnStartGame = new javax.swing.JLabel();
        txtPlayerName = new javax.swing.JTextField();
        txtChannelName = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextPane2.setText("Server messages - ");
        jTextPane2.setToolTipText("");
        jScrollPane2.setViewportView(jTextPane2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 230, 145));

        txtClientMessages.setColumns(20);
        txtClientMessages.setRows(5);
        txtClientMessages.setText("Client messages -");
        jScrollPane4.setViewportView(txtClientMessages);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, -1, 145));

        jButton1.setText("jButton1");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 340, -1, -1));

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

        btnExit.setText("exit");
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });
        pnlTopBorder.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, -1, -1));

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
        add(btnStartServer, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 478, -1, -1));

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
        add(btnJoin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 478, -1, -1));

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
        add(btnStartGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 478, -1, -1));

        txtPlayerName.setText("playerNameDc");
        txtPlayerName.setBorder(null);
        txtPlayerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlayerNameActionPerformed(evt);
            }
        });
        add(txtPlayerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 430, 190, 35));

        txtChannelName.setText("ChannelNameDC");
        txtChannelName.setBorder(null);
        add(txtChannelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 375, 190, 35));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        /*ControllerGamePlay controllerGamePlay;
        panelPlaying = new PanelGamePlay();
        controllerGamePlay = new ControllerGamePlay(panelPlaying,new GameScreen());*/
        
        /**
         * Testing game play panel.
         */
        
        
        
        /*PanelGamePlay panelPlaying = new PanelGamePlay();
        JFrame ui1 =new JFrame("ui1 panelPlaying");
        ui1.setSize(new Dimension(900, 619)); 
        ui1.setLocation(300, 300);
        ui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui1.add(panelPlaying);
        ui1.setVisible(true);*/
        gameScreen.panelPlaying.channelName = channelName;
        Game game = new Game();
        game.addPlayer("Json");
        game.addPlayer("Mark");
        gameScreen.dataForUI.game = game;
        gameScreen.dataForUI.getPlayerList();
        gameScreen.controllerGamePlay.drawOpponenets();
        gameScreen.changeScreen(DataForUI.STR_ROUNDREADYUP, DataForUI.SelectMultiplayer);
        
        //decodeClientMessage("201 startgame pasindu,json");
    }//GEN-LAST:event_jButton1MouseClicked

    private void pnlTopBorderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopBorderMouseDragged
        // TODO add your handling code here:
        gameScreen.moveScreen(evt.getXOnScreen(), evt.getYOnScreen(),mouseX,mouseY);
    }//GEN-LAST:event_pnlTopBorderMouseDragged

    private void pnlTopBorderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopBorderMousePressed
        // TODO add your handling code here:
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_pnlTopBorderMousePressed

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        // TODO add your handling code here:
        gameScreen.changeScreen(DataForUI.STR_MAINMENU, DataForUI.SelectMultiplayer);
    }//GEN-LAST:event_btnExitMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnStartServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartServerMouseClicked
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_hostserver_h.png"));
        btnStartServer.setIcon(imgIcon);
        
        startServerClicked();
    }//GEN-LAST:event_btnStartServerMouseClicked

    private void btnJoinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJoinMouseClicked
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_joinserver_h.png"));
        btnJoin.setIcon(imgIcon);
        
       joinServerClicked();
    }//GEN-LAST:event_btnJoinMouseClicked

    private void btnStartGameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartGameMouseClicked
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_startgamem_h.png"));
       btnStartGame.setIcon(imgIcon);
        
        
        btnStartGame.setEnabled(false);
        channelName = txtChannelName.getText();
        playerName = txtPlayerName.getText();
        server.startGame();
    }//GEN-LAST:event_btnStartGameMouseClicked

    private void txtPlayerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlayerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlayerNameActionPerformed

    private void btnStartServerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartServerMousePressed
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_hostserver_c.png"));
        btnStartServer.setIcon(imgIcon);
    }//GEN-LAST:event_btnStartServerMousePressed

    private void btnStartServerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartServerMouseEntered
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_hostserver_h.png"));
        btnStartServer.setIcon(imgIcon);
    }//GEN-LAST:event_btnStartServerMouseEntered

    private void btnStartServerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartServerMouseExited
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_hostserver_n.png"));
        btnStartServer.setIcon(imgIcon);
    }//GEN-LAST:event_btnStartServerMouseExited

    private void btnStartServerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartServerMouseReleased
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_hostserver_h.png"));
        btnStartServer.setIcon(imgIcon);
    }//GEN-LAST:event_btnStartServerMouseReleased

    private void btnJoinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJoinMouseEntered
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_joinserver_h.png"));
        btnJoin.setIcon(imgIcon);
    }//GEN-LAST:event_btnJoinMouseEntered

    private void btnJoinMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJoinMouseExited
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_joinserver_n.png"));
        btnJoin.setIcon(imgIcon);
    }//GEN-LAST:event_btnJoinMouseExited

    private void btnJoinMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJoinMousePressed
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_joinserver_c.png"));
        btnJoin.setIcon(imgIcon);
    }//GEN-LAST:event_btnJoinMousePressed

    private void btnJoinMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJoinMouseReleased
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_joinserver_h.png"));
        btnJoin.setIcon(imgIcon);
    }//GEN-LAST:event_btnJoinMouseReleased

    private void btnStartGameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartGameMouseEntered
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_startgamem_h.png"));
       btnStartGame.setIcon(imgIcon);
    }//GEN-LAST:event_btnStartGameMouseEntered

    private void btnStartGameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartGameMouseReleased
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_startgamem_h.png"));
       btnStartGame.setIcon(imgIcon);
    }//GEN-LAST:event_btnStartGameMouseReleased

    private void btnStartGameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartGameMouseExited
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_startgamem_n.png"));
       btnStartGame.setIcon(imgIcon);
    }//GEN-LAST:event_btnStartGameMouseExited

    private void btnStartGameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartGameMousePressed
        // TODO add your handling code here:
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_startgamem_c.png"));
       btnStartGame.setIcon(imgIcon);
    }//GEN-LAST:event_btnStartGameMousePressed

    // Listen
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnExit;
    private javax.swing.JLabel btnJoin;
    private javax.swing.JLabel btnStartGame;
    private javax.swing.JLabel btnStartServer;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JPanel pnlTopBorder;
    private javax.swing.JTextField txtChannelName;
    private javax.swing.JTextArea txtClientMessages;
    private javax.swing.JTextField txtPlayerName;
    // End of variables declaration//GEN-END:variables
}





