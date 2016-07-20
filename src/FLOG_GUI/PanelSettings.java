
package FLOG_GUI;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Dushan Galappaththi
 */
public class PanelSettings extends javax.swing.JPanel {

  private Image bg;

  public PanelSettings() {
    initComponents();
    bg = new ImageIcon(getClass().getResource("/images/bg_settings.png")).getImage();
  }

  public void setIcon_Back(ImageIcon icon) {
    this.btnBack.setIcon(icon);
  }

  public void setIcon_Sound(ImageIcon icon) {
    this.btnSound.setIcon(icon);
  }

  public void setIcon_VolReduce(ImageIcon icon) {
    this.btnVolReduce.setIcon(icon);
  }

  public void setIcon_VolIncrease(ImageIcon icon) {
    this.btnVolIncrease.setIcon(icon);
  }

  public Component getCompTop(int x) {
    Component[] cmpList = this.getComponents();
    return this.pnlTopBorder.getComponent(x);
  }

  public Component getCompCon(int x) {
    Component[] cmpList = this.getComponents();
    return this.getComponent(x);
  }

  public void setSoundLevel(int x) {
    this.lblLevel.setText("" + x);
  }

  public void setPlayerName(String name) {
    this.txtName.setText(name);
  }

  public String getplayerName() {
    return this.txtName.getText();
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

    pnlTopBorder = new javax.swing.JPanel();
    btnBack = new javax.swing.JLabel();
    btnSound = new javax.swing.JLabel();
    btnVolReduce = new javax.swing.JLabel();
    btnVolIncrease = new javax.swing.JLabel();
    lblLevel = new javax.swing.JLabel();
    txtName = new javax.swing.JTextField();

    setMaximumSize(new java.awt.Dimension(900, 619));
    setMinimumSize(new java.awt.Dimension(900, 619));
    setPreferredSize(new java.awt.Dimension(900, 619));
    setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    pnlTopBorder.setName("top"); // NOI18N
    pnlTopBorder.setOpaque(false);
    add(pnlTopBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 30));

    btnBack.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("/images/btn_back_n.png"))); // NOI18N
    btnBack.setName("back"); // NOI18N
    add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 480, -1, -1));

    btnSound.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("/images/btn_soundon_n.png"))); // NOI18N
    btnSound.setName("onOff"); // NOI18N
    add(btnSound, new org.netbeans.lib.awtextra.AbsoluteConstraints(387, 430, -1, -1));

    btnVolReduce.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
    btnVolReduce.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("/images/btn_minus_n.png"))); // NOI18N
    btnVolReduce.setName("minus"); // NOI18N
    add(btnVolReduce, new org.netbeans.lib.awtextra.AbsoluteConstraints(374, 338, -1, -1));

    btnVolIncrease.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
    btnVolIncrease.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("/images/btn_plus_n.png"))); // NOI18N
    btnVolIncrease.setName("plus"); // NOI18N
    add(btnVolIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(472, 338, -1, -1));

    lblLevel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    lblLevel.setForeground(new java.awt.Color(255, 255, 255));
    lblLevel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblLevel.setText("100");
    add(lblLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 350, 60, 30));

    txtName.setEditable(false);
    txtName.setText("jTextField1");
    txtName.setBorder(null);
    txtName.setOpaque(false);
    txtName.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtNameActionPerformed(evt);
          }
        });
    add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 255, 200, 35));
  } // </editor-fold>//GEN-END:initComponents

  private void txtNameActionPerformed(
      java.awt.event.ActionEvent evt) { //GEN-FIRST:event_txtNameActionPerformed
    // TODO add your handling code here:
  } //GEN-LAST:event_txtNameActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel btnBack;
  private javax.swing.JLabel btnSound;
  private javax.swing.JLabel btnVolIncrease;
  private javax.swing.JLabel btnVolReduce;
  private javax.swing.JLabel lblLevel;
  private javax.swing.JPanel pnlTopBorder;
  private javax.swing.JTextField txtName;
  // End of variables declaration//GEN-END:variables
}
