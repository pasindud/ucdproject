
package flog_gui_test;

import FLOG_LOGIC.WordAutoGenerator;

/**
 *
 * @author Dushan Galappaththi
 */
public class GameScreen extends javax.swing.JFrame {

  /**
   * Creates new form GameScreen
   */
  public GameScreen() {
    initComponents();
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
    pnlPlayers = new javax.swing.JPanel();
    pnlPlayingContainer = new javax.swing.JPanel();
    btnL1 = new javax.swing.JButton();
    btnL2 = new javax.swing.JButton();
    btnL3 = new javax.swing.JButton();
    btnL6 = new javax.swing.JButton();
    btnL5 = new javax.swing.JButton();
    btnL4 = new javax.swing.JButton();
    btnL9 = new javax.swing.JButton();
    btnL8 = new javax.swing.JButton();
    btnL7 = new javax.swing.JButton();
    btnL12 = new javax.swing.JButton();
    btnL11 = new javax.swing.JButton();
    btnL10 = new javax.swing.JButton();
    txtAns = new javax.swing.JTextField();
    btnSubmit = new javax.swing.JButton();
    btnAutoGen = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    jLabel1 = new javax.swing.JLabel();
    jScrollPane2 = new javax.swing.JScrollPane();
    jLabel2 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    pnlPlayers.setBackground(new java.awt.Color(255, 255, 255));

    btnL1.setText("1st_Letter");

    btnL2.setText("2nd_Letter");

    btnL3.setText("3rd_Letter");

    btnL6.setText("6th_Letter");
    btnL6.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnL6ActionPerformed(evt);
          }
        });

    btnL5.setText("5th_Letter");
    btnL5.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnL5ActionPerformed(evt);
          }
        });

    btnL4.setText("4th_Letter");

    btnL9.setText("9th_Letter");

    btnL8.setText("8th_Letter");

    btnL7.setText("7th_Letter");

    btnL12.setText("12th_Letter");

    btnL11.setText("11th_Letter");

    btnL10.setText("10th_Letter");

    txtAns.setText("<answer here>");

    btnSubmit.setText("Submit");

    btnAutoGen.setBackground(new java.awt.Color(255, 153, 102));
    btnAutoGen.setText("Auto Generate Word");
    btnAutoGen.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAutoGenActionPerformed(evt);
          }
        });

    javax.swing.GroupLayout pnlPlayingContainerLayout =
        new javax.swing.GroupLayout(pnlPlayingContainer);
    pnlPlayingContainer.setLayout(pnlPlayingContainerLayout);
    pnlPlayingContainerLayout.setHorizontalGroup(
        pnlPlayingContainerLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                pnlPlayingContainerLayout
                    .createSequentialGroup()
                    .addGroup(
                        pnlPlayingContainerLayout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                pnlPlayingContainerLayout
                                    .createSequentialGroup()
                                    .addGroup(
                                        pnlPlayingContainerLayout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(
                                                pnlPlayingContainerLayout
                                                    .createSequentialGroup()
                                                    .addGap(18, 18, 18)
                                                    .addComponent(btnL1)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(btnL2)
                                                    .addGap(18, 18, 18))
                                            .addGroup(
                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                pnlPlayingContainerLayout
                                                    .createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(btnAutoGen)
                                                    .addGap(39, 39, 39)))
                                    .addGroup(
                                        pnlPlayingContainerLayout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(
                                                pnlPlayingContainerLayout
                                                    .createSequentialGroup()
                                                    .addComponent(btnL3)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(btnL4)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(
                                                        btnL5,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        91,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(
                                                        btnL6,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        87,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(
                                                        btnL7,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE))
                                            .addGroup(
                                                pnlPlayingContainerLayout
                                                    .createSequentialGroup()
                                                    .addComponent(btnL8)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(btnL9)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(btnL10)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(btnL11)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(btnL12))))
                            .addGroup(
                                pnlPlayingContainerLayout
                                    .createSequentialGroup()
                                    .addGap(188, 188, 188)
                                    .addComponent(
                                        txtAns,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        196,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnSubmit)))
                    .addContainerGap(32, Short.MAX_VALUE)));
    pnlPlayingContainerLayout.setVerticalGroup(
        pnlPlayingContainerLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                pnlPlayingContainerLayout
                    .createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addGroup(
                        pnlPlayingContainerLayout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(
                                txtAns,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSubmit))
                    .addGap(18, 18, Short.MAX_VALUE)
                    .addGroup(
                        pnlPlayingContainerLayout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                pnlPlayingContainerLayout
                                    .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(
                                        btnL6,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        43,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(
                                        btnL7,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        43,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(
                                pnlPlayingContainerLayout
                                    .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(
                                        btnL4,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        43,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(
                                        btnL5,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        43,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(
                                btnL3,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                43,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(
                                pnlPlayingContainerLayout
                                    .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(
                                        btnL1,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        43,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(
                                        btnL2,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        43,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(
                        pnlPlayingContainerLayout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(
                                btnL12,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                            .addGroup(
                                pnlPlayingContainerLayout
                                    .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(
                                        btnL10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        43,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(
                                        btnL11,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        43,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(
                                pnlPlayingContainerLayout
                                    .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(
                                        btnL9,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        43,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(
                                        btnL8,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        43,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(
                                btnAutoGen,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE))
                    .addGap(21, 21, 21)));

    jScrollPane1.setBackground(new java.awt.Color(0, 153, 153));

    jLabel1.setText("Player names and their two cards");
    jScrollPane1.setViewportView(jLabel1);

    jLabel2.setText("Scores of players, updates after each round");
    jScrollPane2.setViewportView(jLabel2);

    javax.swing.GroupLayout pnlPlayersLayout = new javax.swing.GroupLayout(pnlPlayers);
    pnlPlayers.setLayout(pnlPlayersLayout);
    pnlPlayersLayout.setHorizontalGroup(
        pnlPlayersLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                pnlPlayersLayout
                    .createSequentialGroup()
                    .addComponent(
                        pnlPlayingContainer,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(
                pnlPlayersLayout
                    .createSequentialGroup()
                    .addContainerGap()
                    .addComponent(
                        jScrollPane1,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        513,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane2)
                    .addContainerGap()));
    pnlPlayersLayout.setVerticalGroup(
        pnlPlayersLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                pnlPlayersLayout
                    .createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(
                        pnlPlayersLayout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(
                                jScrollPane1,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                337,
                                Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(
                        pnlPlayingContainer,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE)));

    javax.swing.GroupLayout pnlContainerLayout = new javax.swing.GroupLayout(pnlContainer);
    pnlContainer.setLayout(pnlContainerLayout);
    pnlContainerLayout.setHorizontalGroup(
        pnlContainerLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(
                pnlPlayers,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE));
    pnlContainerLayout.setVerticalGroup(
        pnlContainerLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                pnlContainerLayout
                    .createSequentialGroup()
                    .addComponent(
                        pnlPlayers,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(
                pnlContainer,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE));
    layout.setVerticalGroup(
        layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(
                pnlContainer,
                javax.swing.GroupLayout.Alignment.TRAILING,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE));

    pack();
  } // </editor-fold>//GEN-END:initComponents

  private void btnL6ActionPerformed(
      java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnL6ActionPerformed
    // TODO add your handling code here:
  } //GEN-LAST:event_btnL6ActionPerformed

  private void btnL5ActionPerformed(
      java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnL5ActionPerformed
    // TODO add your handling code here:
  } //GEN-LAST:event_btnL5ActionPerformed

  private void btnAutoGenActionPerformed(
      java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnAutoGenActionPerformed
    //Dummy value for testing
    String[] letterArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"};
    WordAutoGenerator ag = new WordAutoGenerator(letterArray);
    txtAns.setText(ag.getLongestWord());
  } //GEN-LAST:event_btnAutoGenActionPerformed

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
      for (javax.swing.UIManager.LookAndFeelInfo info :
          javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(GameScreen.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(GameScreen.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(GameScreen.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(GameScreen.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(
        new Runnable() {
          public void run() {
            new GameScreen().setVisible(true);
          }
        });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnAutoGen;
  private javax.swing.JButton btnL1;
  private javax.swing.JButton btnL10;
  private javax.swing.JButton btnL11;
  private javax.swing.JButton btnL12;
  private javax.swing.JButton btnL2;
  private javax.swing.JButton btnL3;
  private javax.swing.JButton btnL4;
  private javax.swing.JButton btnL5;
  private javax.swing.JButton btnL6;
  private javax.swing.JButton btnL7;
  private javax.swing.JButton btnL8;
  private javax.swing.JButton btnL9;
  private javax.swing.JButton btnSubmit;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JPanel pnlContainer;
  private javax.swing.JPanel pnlPlayers;
  private javax.swing.JPanel pnlPlayingContainer;
  private javax.swing.JTextField txtAns;
  // End of variables declaration//GEN-END:variables
}
