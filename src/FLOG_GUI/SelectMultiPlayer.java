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

import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;

/**
 *
 * @author Pasindu
 */
public class SelectMultiPlayer extends javax.swing.JPanel {
    
    CardLayout mainPanelCards = new CardLayout();
    /** Name of the channel the user is hosting or joined. */
    public String channelName = null;
    /** Name of the player. */
    private String playerName = null;
    int posX=0,posY=0;
    private boolean IS_USER_JOINED = false;
    
    // Consumer producer client
    Catcher clientCatcher = new Catcher();
    Thrower clientThrower = new Thrower();
    
    Multiplayer multiplayer = new Multiplayer();
    Thread backgroundClientQueueCheck;
    
    /**
     * Creates new form SelectMultiPlayer
     */
    public SelectMultiPlayer() {
        initComponents();
        clientThrower.addThrowListener(clientCatcher);
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
//                this.setVisible(false);
                this.playerName = txtPlayerName.getText().trim();
                // TODO add player names.
                if (backgroundClientQueueCheck != null) {
                    backgroundClientQueueCheck.interrupt();
                }
                MultiPlayerTestGUI gui = new MultiPlayerTestGUI(
                        this.channelName, 
                        this.playerName, 
                        Utils.getPlayerNamesFromString(segments[2]));
                gui.setSize(new Dimension(800, 340));
                gui.setVisible(true);
                
//        JFrame ui2=new JFrame("ui2 gui");
//        ui2.setSize(new Dimension(900, 619)); 
//        ui2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        ui2.add(gui);
//        ui2.setVisible(true);
        
               this.setVisible(true);
                removeAll();
                remove(this);

//                this.add(gui);
                
        /*
                JPanel container;
        container = new JPanel();
        container.setLayout(mainPanelCards);
        container.add(gui, DataForUI.SelectMultiplayer);
        this.getContentPane().add(container, BorderLayout.CENTER);
        */        
//        add(gui);
//                this.setLayout(new BoxLayout);
//                this.add(gui, BorderLayout.NORTH);
        
//                backgroundClientQueueCheck.interrupt();
//                this.getContentPane().add(gui);
                //this.add(gui, BorderLayout.CENTER);
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
    private void startServerButtonMouseClicked(MouseEvent evt) {
        startServerButton.setEnabled(false);
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
    private void joinServerButtonMouseClicked(MouseEvent evt) {
        joinServerButton.setEnabled(false);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        txtChannelName = new javax.swing.JTextPane();
        startServerButton = new javax.swing.JButton();
        joinServerButton = new javax.swing.JButton();
        btnStartGame = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtPlayerName = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtClientMessages = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        txtChannelName.setText("ChannelName");
        jScrollPane1.setViewportView(txtChannelName);

        startServerButton.setText("Start Server");
        startServerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startServerButtonMouseClicked(evt);
            }
        });

        joinServerButton.setText("Join Multiplayer");
        joinServerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                joinServerButtonMouseClicked(evt);
            }
        });

        btnStartGame.setText("Start Game");
        btnStartGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStartGameMouseClicked(evt);
            }
        });

        jTextPane2.setText("Server messages - ");
        jTextPane2.setToolTipText("");
        jScrollPane2.setViewportView(jTextPane2);

        jScrollPane3.setViewportView(txtPlayerName);

        txtClientMessages.setColumns(20);
        txtClientMessages.setRows(5);
        txtClientMessages.setText("Client messages -");
        jScrollPane4.setViewportView(txtClientMessages);

        jButton1.setText("jButton1");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)
                        .addComponent(startServerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(joinServerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                        .addComponent(btnStartGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(234, 234, 234))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(startServerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(joinServerButton)
                .addGap(18, 18, 18)
                .addComponent(btnStartGame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(84, 84, 84))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartGameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartGameMouseClicked
        channelName = txtChannelName.getText();
        playerName = txtPlayerName.getText();
        server.startGame();
    }//GEN-LAST:event_btnStartGameMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        /*ControllerGamePlay controllerGamePlay;
        panelPlaying = new PanelGamePlay();
        controllerGamePlay = new ControllerGamePlay(panelPlaying,new GameScreen());*/
        PanelGamePlay panelPlaying = new PanelGamePlay();
        JFrame ui1 =new JFrame("ui1 panelPlaying");
        ui1.setSize(new Dimension(900, 619)); 
        ui1.setLocation(300, 300);
        ui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui1.add(panelPlaying);
        ui1.setVisible(true);
//        decodeClientMessage("201 startgame pasindu,json");
    }//GEN-LAST:event_jButton1MouseClicked

    // Listen
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartGame;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JButton joinServerButton;
    private javax.swing.JButton startServerButton;
    private javax.swing.JTextPane txtChannelName;
    private javax.swing.JTextArea txtClientMessages;
    private javax.swing.JTextPane txtPlayerName;
    // End of variables declaration//GEN-END:variables
}





