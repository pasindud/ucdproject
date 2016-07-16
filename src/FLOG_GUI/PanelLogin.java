/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Dushan Galappaththi
 */


public class PanelLogin extends javax.swing.JPanel {

    private Image bg;
    public PanelLogin() {
        initComponents();
        bg = new ImageIcon(getClass().getResource("/images/bg_login.png")).getImage();
    }

    
    public Component getCompTop(int x)
    {
        Component[] cmpList = this.getComponents();
        
       /* for(int i =0;i<cmpList.length;i++)
        {
              System.out.println(i + " "+cmpList[i].getY()+" "+ cmpList[i].getName() );
        }*/
        return this.pnlTopBorder.getComponent(x);
    }
    
    public Component getCompCon(int x)
    {
        Component[] cmpList = this.getComponents();
        
        /*for(int i =0;i<cmpList.length;i++)
        {
              System.out.println(i + " "+cmpList[i].getY()+" "+ cmpList[i].getName() );
        }*/
        return this.getComponent(x);
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

        btnLogin = new javax.swing.JLabel();
        btnRegister = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        pnlTopBorder = new javax.swing.JPanel();
        btnExit = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(900, 619));
        setMinimumSize(new java.awt.Dimension(900, 619));
        setPreferredSize(new java.awt.Dimension(900, 619));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogin.setText("login");
        btnLogin.setName("login"); // NOI18N
        add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, -1, -1));

        btnRegister.setText("Register");
        btnRegister.setName("reg"); // NOI18N
        add(btnRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 430, -1, -1));

        txtUsername.setBorder(null);
        txtUsername.setName("username"); // NOI18N
        add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(437, 265, 145, 24));

        txtPassword.setBorder(null);
        txtPassword.setName("pass"); // NOI18N
        add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(437, 312, 145, 24));

        pnlTopBorder.setName("topBorder"); // NOI18N
        pnlTopBorder.setOpaque(false);
        pnlTopBorder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExit.setText("exit");
        btnExit.setName("exit"); // NOI18N
        pnlTopBorder.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 13, -1, -1));

        add(pnlTopBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 38));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnExit;
    private javax.swing.JLabel btnLogin;
    private javax.swing.JLabel btnRegister;
    private javax.swing.JPanel pnlTopBorder;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
