/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

import FLOG_LOGIC.FLOG_LOGIC;
import FLOG_LOGIC.Multiplayer;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Dushan Galappaththi
 */
public class ChatFrame extends javax.swing.JFrame {

    Multiplayer multiplayer=new Multiplayer();
    int mouseX=0;
    int mouseY=0;
    GameScreen gameScreen;
    private Image bg;
    public ChatFrame(GameScreen gameScreen) 
    {
        initComponents();
        this.gameScreen = gameScreen;
        setWindow();
        
        
        
    }
    
    public void setWindow()
    {
        this.setSize(new Dimension(272, 619));
        this.setResizable(false);
        this.setLocation(gameScreen.getLocationOnScreen().x + gameScreen.getWidth() + 20
                , gameScreen.getLocationOnScreen().y);
        this.validate();
        this.setVisible(false);
    }
    
    public void updateMessages(String username, String msg)
    {
     
        String newMsg = username +" :: "+msg;
        String oldMsgs = jTextPane1.getText();
        jTextPane1.setText(oldMsgs +="\n" +newMsg);
    }
    
    public void playerJoinedUpdateMessages(String username)
    {
        String newMsg;
        String oldMsgs = jTextPane1.getText();
        if(username.equals(DataForUI.currentUsername))
        {
            newMsg = "SERVER :: You joined";
        }
        else
        {
            newMsg = "SERVER :: "+username+" joined";
        }
        
        jTextPane1.setText(oldMsgs +="\n" +newMsg);
    }
    
    public void sendClick()
    {
        
        String msg =txtMessage.getText();
        //Broadcast the message here
        if(!msg.equals(""))
        {
            updateMessages(DataForUI.currentUsername,msg);
            msg = DataForUI.currentUsername+" :: "+msg;
            msg="110 "+msg.trim().replaceAll(" ","_");
            //msg = 110 dilshanwn_::_Hi_every_one
            gameScreen.multiplayer.broadcast(DataForUI.currentChannel, gameScreen.otherPlayerNames, msg);
            System.out.println("message :" +msg +" "+ gameScreen.otherPlayerNames.size());
            txtMessage.setText("");//Clear messagebox after sent
        }
        
        
    }
    
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContainer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        btnSend = new javax.swing.JLabel();
        txtMessage = new javax.swing.JTextField();
        pnlTopBorder = new javax.swing.JPanel();
        btnExit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlContainer.setOpaque(false);
        pnlContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setViewportView(jTextPane1);

        pnlContainer.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 50, 223, 500));

        btnSend.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_send_n.png"))); // NOI18N
        btnSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSendMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSendMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSendMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSendMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSendMouseReleased(evt);
            }
        });
        pnlContainer.add(btnSend, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 564, 42, 50));

        txtMessage.setBorder(null);
        txtMessage.setOpaque(false);
        txtMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMessageActionPerformed(evt);
            }
        });
        pnlContainer.add(txtMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 570, 198, 35));

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
        pnlTopBorder.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 45, 38));

        pnlContainer.add(pnlTopBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg_chat.png"))); // NOI18N
        pnlContainer.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 272, 619));

        getContentPane().add(pnlContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 272, 619));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMessageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMessageActionPerformed

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        ImageIcon imgIcon =new ImageIcon(getClass().getResource("/images/btn_exit_h.png"));
        btnExit.setIcon(imgIcon);
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnSendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSendMouseClicked
        // TODO add your handling code here:
        sendClick();
        ImageIcon imgIcon =new ImageIcon(getClass().getResource("/images/btn_send_h.png"));
        btnSend.setIcon(imgIcon);
        
    }//GEN-LAST:event_btnSendMouseClicked

    private void pnlTopBorderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopBorderMouseDragged
        // TODO add your handling code here:
        setLocation(evt.getXOnScreen()-mouseX,evt.getYOnScreen()-mouseY);
        
    }//GEN-LAST:event_pnlTopBorderMouseDragged

    private void pnlTopBorderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopBorderMousePressed
        // TODO add your handling code here:
        mouseX=evt.getX();
        mouseY=evt.getY();
    }//GEN-LAST:event_pnlTopBorderMousePressed

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        // TODO add your handling code here:
        ImageIcon imgIcon =new ImageIcon(getClass().getResource("/images/btn_exit_h.png"));
        btnExit.setIcon(imgIcon);
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        ImageIcon imgIcon =new ImageIcon(getClass().getResource("/images/btn_exit_n.png"));
        btnExit.setIcon(imgIcon);
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMousePressed
       ImageIcon imgIcon =new ImageIcon(getClass().getResource("/images/btn_exit_h.png"));
        btnExit.setIcon(imgIcon);
    }//GEN-LAST:event_btnExitMousePressed

    private void btnExitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseReleased
        ImageIcon imgIcon =new ImageIcon(getClass().getResource("/images/btn_exit_h.png"));
        btnExit.setIcon(imgIcon);
    }//GEN-LAST:event_btnExitMouseReleased

    private void btnSendMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSendMouseEntered
        ImageIcon imgIcon =new ImageIcon(getClass().getResource("/images/btn_send_h.png"));
        btnSend.setIcon(imgIcon);
    }//GEN-LAST:event_btnSendMouseEntered

    private void btnSendMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSendMouseExited
        ImageIcon imgIcon =new ImageIcon(getClass().getResource("/images/btn_send_n.png"));
        btnSend.setIcon(imgIcon);
    }//GEN-LAST:event_btnSendMouseExited

    private void btnSendMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSendMousePressed
        ImageIcon imgIcon =new ImageIcon(getClass().getResource("/images/btn_send_c.png"));
        btnSend.setIcon(imgIcon);
    }//GEN-LAST:event_btnSendMousePressed

    private void btnSendMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSendMouseReleased
       ImageIcon imgIcon =new ImageIcon(getClass().getResource("/images/btn_send_h.png"));
        btnSend.setIcon(imgIcon);
    }//GEN-LAST:event_btnSendMouseReleased

    /**
     * @param args the command line arguments
     */
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnExit;
    private javax.swing.JLabel btnSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel pnlContainer;
    private javax.swing.JPanel pnlTopBorder;
    private javax.swing.JTextField txtMessage;
    // End of variables declaration//GEN-END:variables
}
