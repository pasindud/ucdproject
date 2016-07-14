/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

import FLOG_LOGIC.*;
import FLOG_LOGIC.Multiplayer;

import FLOG_LOGIC.Thrower;
import FLOG_LOGIC.ThrowListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.util.ArrayList;
import java.util.List;

import java.*;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Pasindu
 */
public class SelectMultiPlayer extends JPanel {

    /**
     * Name of the channel the user is hosting or joined.
     */
    public String channelName = null;
    /**
     * Name of the player.
     */
    private String playerName = null;
    int posX = 0, posY = 0;

    // Consumer producer client
    Catcher clientCatcher = new Catcher(false);
    Thrower clientThrower = new Thrower();

    // Consumer producer server 
    Catcher serverCatcher = new Catcher(true);
    Thrower serverThrower = new Thrower();

    List<String> playerNames = new ArrayList<String>();
    Multiplayer multiplayer = new Multiplayer();

    /**
     * Creates new form SelectMultiPlayer
     */
    public SelectMultiPlayer() {
        initComponents();
        // Catch message thrown by the queue checker.
        clientThrower.addThrowListener(clientCatcher);
        serverThrower.addThrowListener(serverCatcher);
        // Check run, only used for testing.
        /*
         channelName = txtChannelName.getText();
         txtPlayerName.setText("pasindu");
         playerName = txtPlayerName.getText();
         multiplayer.joinNewPlayer(playerName, channelName);
         new Thread(new CheckQueueThread(multiplayer.getServerQueue(channelName), thrower)).start();
         */
    }

    /**
     * Decode the message received by the client
     */
    public synchronized void decodeClientMessage(String message) {
        System.out.println("Decoding message to client message - " + message);
        String[] segments = message.split(" ");
        if (segments.length < 2) {
            return;
        }
        String code = segments[0];
        String content = segments[1];
        switch (code) {
            case "300":
                this.playerName = txtPlayerName.getText().trim();
                MultiPlayerTestGUI gui = new MultiPlayerTestGUI(this.channelName, this.playerName, playerNames);
                gui.setVisible(true);
                break;
        }
    }

    /**
     * Decode the message received by the server.
     */
    public synchronized void decodeServerMessage(String message) {
        System.out.println("Decoding message to server message - " + message);
        String[] segments = message.split(" ");
        if (segments.length < 2) {
            return;
        }
        String code = segments[0];
        String content = segments[1];
        switch (code) {
            // New user joined format - 100 <player name>
            case "100":
                String playerName = content;
                setStatus("User " + content + " joined ");
                String playerQueue = multiplayer.getClientQueue(channelName, playerName);

                // Message to acknowledge that the server received the message 
                String clientMessage = "200 ackJoinServer";
                multiplayer.publishToQueue(playerQueue, clientMessage);
                playerNames.add(content.trim());
                break;
            // Start new game window
            case "101":
                String clientMessagestart = "300 startgamegui";
                for (String player : playerNames) {
                    String startplayerQueue = multiplayer.getClientQueue(channelName, player);
                    multiplayer.publishToQueue(startplayerQueue, clientMessagestart);
                }
        }

    }

    /**
     * Listens to messages thrown by checkQueueThread.
     */
    public class Catcher implements ThrowListener {

        private boolean isServerCatch = true;

        public Catcher(boolean isServerCatch) {
            this.isServerCatch = isServerCatch;
        }

        @Override
        public void Catch(String message) {
            System.out.println("Caught " + message);
            if (isServerCatch) {
                decodeServerMessage(message);
            } else {
                decodeClientMessage(message);
            }
        }
    }

    /**
     * Start the server queues for the give {@code channelName}
     */
    private void startServerButtonMouseClicked(MouseEvent evt) {
        channelName = txtChannelName.getText();
        multiplayer.createServer(channelName);

        // Server listen's to it's queue.
        String serverQueueName = multiplayer.getServerQueue(channelName);
        Thread backgroundServerQueueCheck = new CheckQueueThread(serverQueueName, serverThrower);
        backgroundServerQueueCheck.start();

        // TODO: Automattically run joinServerButtonActionClick so that the server
        //       user can join the game.
    }

    /**
     * Join the given server {@code channelName}
     */
    private void joinServerButtonActionClicked(MouseEvent evt) {
        channelName = txtChannelName.getText();
        playerName = txtPlayerName.getText();
        multiplayer.joinNewPlayer(playerName, channelName);

        // Listen to the client's queue.
        String clientQueueName = multiplayer.getClientQueue(channelName, playerName);
        Thread backgroundClientQueueCheck = new CheckQueueThread(clientQueueName, clientThrower);
        backgroundClientQueueCheck.start();
    }

    /**
     * starts the game
     */
    private void startGameButtonActionClicked(MouseEvent evt) {
        channelName = txtChannelName.getText();
        playerName = txtPlayerName.getText();
        multiplayer.startNewgame(channelName);

        String serverQueueName = multiplayer.getServerQueue(channelName);
        Thread backgroundServerQueueCheck = new CheckQueueThread(serverQueueName, serverThrower);
        backgroundServerQueueCheck.start();
    }

    /**
     * Set the status from the queue reading.
     */
    public synchronized void setStatus(String status) {
        System.out.println("Set status " + status);
        String text = jTextPane2.getText();
        jTextPane2.setText(text += "\n" + status);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChannelName = new javax.swing.JTextPane();
        startServerButton = new javax.swing.JButton();
        joinServerButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtPlayerName = new javax.swing.JTextPane();

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
                joinServerButtonActionClicked(evt);
            }
        });

        jButton1.setText("Start Game");

        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startGameButtonActionClicked(evt);
            }
        });
        jTextPane2.setText("Progress Info -");
        jTextPane2.setToolTipText("");
        jScrollPane2.setViewportView(jTextPane2);

        jScrollPane3.setViewportView(txtPlayerName);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jScrollPane1)
                                                        .addComponent(startServerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(joinServerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(startServerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(joinServerButton)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addContainerGap(70, Short.MAX_VALUE))
        );
    }

    // Listen

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JButton joinServerButton;
    private javax.swing.JButton startServerButton;
    private javax.swing.JTextPane txtChannelName;
    private javax.swing.JTextPane txtPlayerName;
    // End of variables declaration//GEN-END:variables
}
