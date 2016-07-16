/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

import FLOG_LOGIC.CheckQueueThread;
import FLOG_LOGIC.Multiplayer;
import FLOG_LOGIC.ThrowListener;

import FLOG_LOGIC.Thrower;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *
 * @author DILSHAN FERNANDO
 */
public class MultiPlayerTestGUI extends JFrame {

    /**
     * Name of the channel the user is hosting or joined.
     */
    public String channelName = null;
    public String serverQueueName = "";
    /**
     * Name of the player.
     */
    private String playerName = null;

    Catcher clientCatcher = new Catcher();
    Thrower clientThrower = new Thrower();

    List<String> playerNames = new ArrayList<String>();
    Multiplayer multiplayer = new Multiplayer();
    
    Thread backgroundClientQueueCheck;
    /**
     * Creates new form MultiPlayerTestGUI
     */
    public MultiPlayerTestGUI() {
        initComponents();
        clientThrower.addThrowListener(clientCatcher);
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
     * Decode the message received by the client
     */
    public synchronized void decodeClientMessage(String message) {
        // setClientStatus("Message in client - " + message);
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
            case "300":
                //get other players letters
                String[] lettersegments=content.split("^");
                if(lettersegments.length<3){
                    return;
                }
                String player=lettersegments[0];
                String letter1=lettersegments[1];
                String letter2=lettersegments[2];
                this.replaceLettersInTable(player,letter1,letter2);
                break;
        }
    }
    /*
    *   replace letters of players in table
    */
    private void replaceLettersInTable(String player,String letter1,String letter2){
        for(int i=0;i<jTable1.getRowCount();i++){
            if(jTable1.getModel().getValueAt(i, 0).toString().equalsIgnoreCase(player)){
                DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
                model.setValueAt(letter1, i, 1);
                model.setValueAt(letter2, i, 2);
            }
        }
        
    }
    public void startQueueSystem(){
        String clientQueueName = multiplayer.getClientQueue(channelName, playerName);
        backgroundClientQueueCheck = new CheckQueueThread(clientQueueName, clientThrower);
        backgroundClientQueueCheck.start();
    }
    
    ArrayList<String> otherUserNames = new ArrayList<String>();
    public MultiPlayerTestGUI(String channel, String player, List<String> playerlist) {
        this.serverQueueName = multiplayer.getServerQueue(channelName);
        initComponents();
        this.channelName = channel;
        this.playerName = player;
        this.playerNames = playerlist;
        lbll1.setText(FLOG_LOGIC.Utils.getRandomVowel());
        lbll2.setText(FLOG_LOGIC.Utils.getRandomConsonant());
        startQueueSystem();
        txtPlayerName.setText(this.playerName);
        // Delete the current user.
        playerlist.remove(player);
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (String otherPlayerName : playerlist) {
            otherUserNames.add(otherPlayerName);
            model.addRow(new Object[]{ otherPlayerName, "", "", "0"});

        }
        
        multiplayer.ackstartNewGame(playerName, channelName);
        multiplayer.sendLettersToServer(playerName, channelName, otherUserNames, lbll1.getText(),  lbll2.getText());
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

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Player", "Letter#1", "Letter#2", "Total Marks"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 700, 80));

        lbll1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbll1.setText("-");
        add(lbll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        lbll2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbll2.setText("-");
        add(lbll2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        lbll3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll3.setText("-");
        add(lbll3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, -1, -1));

        lbll4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll4.setText("-");
        add(lbll4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        lbll5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll5.setText("-");
        add(lbll5, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 130, 20, -1));

        lbll6.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll6.setText("-");
        add(lbll6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, -1, -1));

        lbll7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll7.setText("-");
        add(lbll7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, -1));

        lbll8.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll8.setText("-");
        add(lbll8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, -1, -1));

        lbll9.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll9.setText("-");
        add(lbll9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, -1));

        lbll10.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll10.setText("-");
        add(lbll10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

        lbll11.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll11.setText("-");
        add(lbll11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, -1, -1));

        lbll12.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbll12.setText("-");
        add(lbll12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, -1, -1));

        jLabel1.setText("player");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        txtPlayerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlayerNameActionPerformed(evt);
            }
        });
        add(txtPlayerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 90, -1));

        jLabel2.setText("Vowels");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));
        add(txtNoV, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 90, -1));

        jLabel3.setText("consonants");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 250, 80, -1));
        add(txtNoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 90, -1));

        cmdGenerate.setText("Generate letters");
        cmdGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGenerateActionPerformed(evt);
            }
        });
        add(cmdGenerate, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, -1, 50));

        jButton1.setText("simiulate letters selection time was up");
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 220, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void cmdGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGenerateActionPerformed
        try {
            int nv=Integer.parseInt(txtNoV.getText());
            int nc=Integer.parseInt(txtNoC.getText());
            String message = "104 letters";
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
                
                for (int i = 0; i < letters.size(); i++) {
                    message += "," + letters.get(i);
                }
                multiplayer.publishToQueue(serverQueueName, message);
            }
            else{
                JOptionPane.showMessageDialog(this, "#Vowels + #Consonants should be equal to 10");
            }
            
            
        } catch (Exception ex) {
                
                }
       
    }//GEN-LAST:event_cmdGenerateActionPerformed

    private void txtPlayerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlayerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlayerNameActionPerformed

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
                break;
            
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
