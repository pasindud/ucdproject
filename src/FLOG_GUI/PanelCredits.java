/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Dushan Galappaththi
 */
public class PanelCredits extends javax.swing.JPanel {

  private Image bg;
  GameScreen gameScreen;
  int mouseX = 0;
  int mouseY = 0;

  public PanelCredits(GameScreen gameScreen) {
    initComponents();
    this.gameScreen = gameScreen;
    bg = new ImageIcon(getClass().getResource("/images/bg_credits.png")).getImage();
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

    jLabel1 = new javax.swing.JLabel();
    pnlTopBorder = new javax.swing.JPanel();
    btnExit = new javax.swing.JLabel();

    jLabel1.setText("jLabel1");

    setMaximumSize(new java.awt.Dimension(900, 619));
    setMinimumSize(new java.awt.Dimension(900, 619));
    setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    pnlTopBorder.setOpaque(false);
    pnlTopBorder.addMouseMotionListener(
        new java.awt.event.MouseMotionAdapter() {
          public void mouseDragged(java.awt.event.MouseEvent evt) {
            pnlTopBorderMouseDragged(evt);
          }
        });
    pnlTopBorder.addMouseListener(
        new java.awt.event.MouseAdapter() {
          public void mousePressed(java.awt.event.MouseEvent evt) {
            pnlTopBorderMousePressed(evt);
          }
        });
    pnlTopBorder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    btnExit.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("/images/btn_exit_n.png"))); // NOI18N
    btnExit.addMouseListener(
        new java.awt.event.MouseAdapter() {
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
  } // </editor-fold>//GEN-END:initComponents

  private void btnExitMouseClicked(
      java.awt.event.MouseEvent evt) { //GEN-FIRST:event_btnExitMouseClicked
    // TODO add your handling code here:
    gameScreen.changeScreen(DataForUI.STR_MAINMENU, DataForUI.STR_CREDITS);
  } //GEN-LAST:event_btnExitMouseClicked

  private void btnExitMouseEntered(
      java.awt.event.MouseEvent evt) { //GEN-FIRST:event_btnExitMouseEntered
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_exit_h.png"));
    btnExit.setIcon(imgIcon);
  } //GEN-LAST:event_btnExitMouseEntered

  private void btnExitMouseExited(
      java.awt.event.MouseEvent evt) { //GEN-FIRST:event_btnExitMouseExited
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_exit_n.png"));
    btnExit.setIcon(imgIcon);
  } //GEN-LAST:event_btnExitMouseExited

  private void btnExitMousePressed(
      java.awt.event.MouseEvent evt) { //GEN-FIRST:event_btnExitMousePressed
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_exit_h.png"));
    btnExit.setIcon(imgIcon);
  } //GEN-LAST:event_btnExitMousePressed

  private void btnExitMouseReleased(
      java.awt.event.MouseEvent evt) { //GEN-FIRST:event_btnExitMouseReleased
    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_exit_h.png"));
    btnExit.setIcon(imgIcon);
  } //GEN-LAST:event_btnExitMouseReleased

  private void pnlTopBorderMousePressed(
      java.awt.event.MouseEvent evt) { //GEN-FIRST:event_pnlTopBorderMousePressed
    mouseX = evt.getX();
    mouseY = evt.getY();
  } //GEN-LAST:event_pnlTopBorderMousePressed

  private void pnlTopBorderMouseDragged(
      java.awt.event.MouseEvent evt) { //GEN-FIRST:event_pnlTopBorderMouseDragged
    gameScreen.moveScreen(evt.getXOnScreen(), evt.getYOnScreen(), mouseX, mouseY);
  } //GEN-LAST:event_pnlTopBorderMouseDragged

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel btnExit;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel pnlTopBorder;
  // End of variables declaration//GEN-END:variables

}
