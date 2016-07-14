/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

import FLOG_LOGIC.Multiplayer;
import FLOG_LOGIC.ThrowListener;

import FLOG_LOGIC.Thrower;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DILSHAN FERNANDO
 */
public class MultiPlayerTestGUI extends javax.swing.JFrame {

    /**
     * Name of the channel the user is hosting or joined.
     */
    public String channelName = null;
    /**
     * Name of the player.
     */
    private String playerName = null;

    // Consumer producer client
    Catcher clientCatcher = new Catcher(false);
    Thrower clientThrower = new Thrower();

    // Consumer producer server 
    Catcher serverCatcher = new Catcher(true);
    Thrower serverThrower = new Thrower();

    List<String> playerNames = new ArrayList<String>();
    Multiplayer multiplayer = new Multiplayer();

    /**
     * Creates new form MultiPlayerTestGUI
     */
    public MultiPlayerTestGUI() {
        initComponents();
    }

    public MultiPlayerTestGUI(String channel, String player, List<String> playerlist) {
        initComponents();
        this.channelName = channel;
        this.playerName = player;
        this.playerNames = playerlist;
        lbll1.setText(FLOG_LOGIC.Utils.getRandomVowel());
        lbll2.setText(FLOG_LOGIC.Utils.getRandomConsonant());
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
        jTable1 = new javax.swing.JTable();
        lbll1 = new javax.swing.JLabel();
        lbll2 = new javax.swing.JLabel();
        lbll3 = new javax.swing.JLabel();
        lbll4 = new javax.swing.JLabel();
        lbll5 = new javax.swing.JLabel();
        lbll6 = new javax.swing.JLabel();
        lbll7 = new javax.swing.JLabel();
        lbll8 = new javax.swing.JLabel();
        lbll9 = new javax.swing.JLabel();
        lbll10 = new javax.swing.JLabel();
        lbll11 = new javax.swing.JLabel();
        lbll12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtPlayerName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNoV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNoC = new javax.swing.JTextField();
        cmdGenerate = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Player", "Letter#1", "Letter#2", "Total Marks"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 700, 80));

        lbll1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbll1.setText("-");
        getContentPane().add(lbll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        lbll2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbll2.setText("-");
        getContentPane().add(lbll2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        lbll3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll3.setText("-");
        getContentPane().add(lbll3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, -1, -1));

        lbll4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll4.setText("-");
        getContentPane().add(lbll4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        lbll5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll5.setText("-");
        getContentPane().add(lbll5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        lbll6.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll6.setText("-");
        getContentPane().add(lbll6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, -1, -1));

        lbll7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll7.setText("-");
        getContentPane().add(lbll7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, -1));

        lbll8.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll8.setText("-");
        getContentPane().add(lbll8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, -1, -1));

        lbll9.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll9.setText("-");
        getContentPane().add(lbll9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, -1));

        lbll10.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll10.setText("-");
        getContentPane().add(lbll10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

        lbll11.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll11.setText("-");
        getContentPane().add(lbll11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, -1, -1));

        lbll12.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll12.setText("-");
        getContentPane().add(lbll12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, -1, -1));

        jLabel1.setText("player");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));
        getContentPane().add(txtPlayerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 90, -1));

        jLabel2.setText("Vowels");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));
        getContentPane().add(txtNoV, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 90, -1));

        jLabel3.setText("consonants");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 250, 80, -1));
        getContentPane().add(txtNoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 90, -1));

        cmdGenerate.setText("Generate letters");
        cmdGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGenerateActionPerformed(evt);
            }
        });
        getContentPane().add(cmdGenerate, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, -1, 50));

        jButton1.setText("simiulate letters selection time was up");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 220, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGenerateActionPerformed
        try {
            int nv=Integer.parseInt(txtNoV.getText());
            int nc=Integer.parseInt(txtNoC.getText());
            if(nv+nc==10){
                List<String> letters=FLOG_LOGIC.Utils.getRadomLetters(nv, nc);
                lbll3.setText(letters.get(0));
                lbll4.setText(letters.get(1));
                lbll5.setText(letters.get(2));
                lbll6.setText(letters.get(3));
                lbll7.setText(letters.get(4));
                lbll8.setText(letters.get(5));
                lbll9.setText(letters.get(6));
                lbll10.setText(letters.get(7));
                lbll11.setText(letters.get(8));
                lbll12.setText(letters.get(9));
            }
            else{
                JOptionPane.showMessageDialog(this, "#Vowels + #Consonants should be equal to 10");
            }
            
        } catch (Exception ex) {
                
                }
       
    }//GEN-LAST:event_cmdGenerateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MultiPlayerTestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MultiPlayerTestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MultiPlayerTestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MultiPlayerTestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MultiPlayerTestGUI().setVisible(true);
            }
        });
    }

    public synchronized void decodeClientMessage(String message) {

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
//                String playerName = content;
//                setStatus("User " + content + " joined ");
//                String playerQueue = multiplayer.getClientQueue(channelName, playerName);
//                
//                // Message to acknowledge that the server received the message 
//                String clientMessage = "200 ackJoinServer";
//                multiplayer.publishToQueue(playerQueue, clientMessage);
//                playerNames.add(content.trim());
                break;
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

    public synchronized void setStatus(String status) {
        System.out.println("Set status " + status);
       // String text = jTextPane2.getText();
        // jTextPane2.setText(text += "\n" + status);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdGenerate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbll1;
    private javax.swing.JLabel lbll10;
    private javax.swing.JLabel lbll11;
    private javax.swing.JLabel lbll12;
    private javax.swing.JLabel lbll2;
    private javax.swing.JLabel lbll3;
    private javax.swing.JLabel lbll4;
    private javax.swing.JLabel lbll5;
    private javax.swing.JLabel lbll6;
    private javax.swing.JLabel lbll7;
    private javax.swing.JLabel lbll8;
    private javax.swing.JLabel lbll9;
    private javax.swing.JTextField txtNoC;
    private javax.swing.JTextField txtNoV;
    private javax.swing.JTextField txtPlayerName;
    // End of variables declaration//GEN-END:variables
}