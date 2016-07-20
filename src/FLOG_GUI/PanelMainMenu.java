package FLOG_GUI;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.EventHandler;
import javax.swing.ImageIcon;

/**
 *
 * @author Dushan Galappaththi
 */
public class PanelMainMenu extends javax.swing.JPanel {

    public EventHandler startButton;
    private Image bg;

    public PanelMainMenu() {
        initComponents();
        bg = new ImageIcon(getClass().getResource("/images/bg_mainmenu.png")).getImage();

    }

    public Component getCompTop(int x) {
        Component[] cmpList = this.getComponents();

        return this.pnlTopBorder.getComponent(x);
    }

    public Component getCompCon(int x) {
        Component[] cmpList = this.getComponents();

        return this.getComponent(x);
    }

    public void setIcon_Quit(ImageIcon icon) {
        this.btnExit.setIcon(icon);

    }

    public void setIcon_Start(ImageIcon icon) {
        this.btnStart.setIcon(icon);

    }

    public void setIcon_Settings(ImageIcon icon) {
        this.btnSettings.setIcon(icon);

    }

    public void setIcon_Credits(ImageIcon icon) {
        this.btnAbout.setIcon(icon);

    }

    public void setIcon_Help(ImageIcon icon) {
        this.btnHelp.setIcon(icon);

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

        btnStart = new javax.swing.JLabel();
        btnExit = new javax.swing.JLabel();
        btnSettings = new javax.swing.JLabel();
        btnHelp = new javax.swing.JLabel();
        btnAbout = new javax.swing.JLabel();
        pnlTopBorder = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(900, 619));
        setRequestFocusEnabled(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnStart.setBackground(new java.awt.Color(0, 204, 255));
        btnStart.setFont(new java.awt.Font("Trebuchet MS", 0, 30)); // NOI18N
        btnStart.setForeground(new java.awt.Color(255, 255, 255));
        btnStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_startgame_n.png"))); // NOI18N
        btnStart.setPreferredSize(new java.awt.Dimension(282, 36));
        add(btnStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 165, 288, 70));

        btnExit.setBackground(new java.awt.Color(0, 204, 255));
        btnExit.setFont(new java.awt.Font("Trebuchet MS", 0, 30)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_quit_n.png"))); // NOI18N
        btnExit.setPreferredSize(new java.awt.Dimension(282, 36));
        add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 448, 288, 70));

        btnSettings.setBackground(new java.awt.Color(0, 204, 255));
        btnSettings.setFont(new java.awt.Font("Trebuchet MS", 0, 30)); // NOI18N
        btnSettings.setForeground(new java.awt.Color(255, 255, 255));
        btnSettings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_settings_n.png"))); // NOI18N
        btnSettings.setPreferredSize(new java.awt.Dimension(282, 36));
        add(btnSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 235, 288, 70));

        btnHelp.setBackground(new java.awt.Color(0, 204, 255));
        btnHelp.setFont(new java.awt.Font("Trebuchet MS", 0, 30)); // NOI18N
        btnHelp.setForeground(new java.awt.Color(255, 255, 255));
        btnHelp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_help_n.png"))); // NOI18N
        btnHelp.setPreferredSize(new java.awt.Dimension(282, 36));
        add(btnHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 306, 288, 70));

        btnAbout.setBackground(new java.awt.Color(0, 204, 255));
        btnAbout.setFont(new java.awt.Font("Trebuchet MS", 0, 30)); // NOI18N
        btnAbout.setForeground(new java.awt.Color(255, 255, 255));
        btnAbout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_credits_n.png"))); // NOI18N
        btnAbout.setPreferredSize(new java.awt.Dimension(282, 36));
        add(btnAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 377, 288, 70));

        pnlTopBorder.setName("top border"); // NOI18N
        pnlTopBorder.setOpaque(false);
        add(pnlTopBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 38));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAbout;
    private javax.swing.JLabel btnExit;
    private javax.swing.JLabel btnHelp;
    private javax.swing.JLabel btnSettings;
    private javax.swing.JLabel btnStart;
    private javax.swing.JPanel pnlTopBorder;
    // End of variables declaration//GEN-END:variables
}
