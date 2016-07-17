
package FLOG_GUI;

import FLOG_LOGIC.Multiplayer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Dushan Galappaththi
 */
public class ControllerLogin {

  int mouseX = 0;
  int mouseY = 0;
  PanelLogin panelLogin;
  GameScreen gameScreen;
  FLOG_LOGIC.Multiplayer multiplayer = new Multiplayer();

  public ControllerLogin(PanelLogin panelLogin, GameScreen gameScreen) {
    this.panelLogin = panelLogin;
    this.gameScreen = gameScreen;
    initializeLoginListeners();
  }

  private void loginClick(String username, String password) {
    //credentials should be validated
    if (multiplayer.login(username, password)) {
      gameScreen.changeScreen(DataForUI.STR_MAINMENU, DataForUI.STR_LOGIN);
    } else {
      JOptionPane.showMessageDialog(
          gameScreen,
          "User name or password entered is incorrect",
          "Login error",
          JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private void registerClick() {
    gameScreen.changeScreen(DataForUI.STR_REGISTER, DataForUI.STR_LOGIN);
  }

  private void exitClick() {
    System.exit(0);
  }

  /**
   * Below Code listens for events happening in the UI,
   * which is the PanelLogin JPanel and Handles Label icon transition
   * to give the feel of a custom button
   */
  private void initializeLoginListeners() {
    final int _login = 0;
    final int _register = 1;
    final int _username = 2;
    final int _pass = 3;
    final int _exit = 0;
    final int _TopBorder = 4;

    panelLogin
        .getCompCon(_TopBorder)
        .addMouseListener(
            new MouseListener() {
              @Override
              public void mouseClicked(MouseEvent e) {}

              @Override
              public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
              }

              @Override
              public void mouseReleased(MouseEvent e) {}

              @Override
              public void mouseEntered(MouseEvent e) {}

              @Override
              public void mouseExited(MouseEvent e) {}
            });

    panelLogin
        .getCompCon(_TopBorder)
        .addMouseMotionListener(
            new MouseMotionListener() {
              @Override
              public void mouseDragged(MouseEvent e) {
                gameScreen.moveScreen(e.getXOnScreen(), e.getYOnScreen(), mouseX, mouseY);
              }

              @Override
              public void mouseMoved(MouseEvent e) {}
            });

    panelLogin
        .getCompCon(_login)
        .addMouseListener(
            new MouseListener() {
              @Override
              public void mouseClicked(MouseEvent e) {
                JTextField uname = (JTextField) panelLogin.getCompCon(_username);
                JPasswordField pass = (JPasswordField) panelLogin.getCompCon(_pass);
                String password = new String(pass.getPassword());
                if (uname.getText().equals("") || password.equals("")) {
                  JOptionPane.showMessageDialog(
                      gameScreen,
                      "User name or password is missing",
                      "Login error",
                      JOptionPane.INFORMATION_MESSAGE);
                } else {
                  loginClick(uname.getText(), password);
                }
              }

              @Override
              public void mousePressed(MouseEvent e) {}

              @Override
              public void mouseReleased(MouseEvent e) {}

              @Override
              public void mouseEntered(MouseEvent e) {}

              @Override
              public void mouseExited(MouseEvent e) {}
            });

    panelLogin
        .getCompCon(_register)
        .addMouseListener(
            new MouseListener() {
              @Override
              public void mouseClicked(MouseEvent e) {
                registerClick();
              }

              @Override
              public void mousePressed(MouseEvent e) {}

              @Override
              public void mouseReleased(MouseEvent e) {}

              @Override
              public void mouseEntered(MouseEvent e) {}

              @Override
              public void mouseExited(MouseEvent e) {}
            });

    panelLogin
        .getCompTop(_exit)
        .addMouseListener(
            new MouseListener() {
              @Override
              public void mouseClicked(MouseEvent e) {
                exitClick();
              }

              @Override
              public void mousePressed(MouseEvent e) {}

              @Override
              public void mouseReleased(MouseEvent e) {}

              @Override
              public void mouseEntered(MouseEvent e) {}

              @Override
              public void mouseExited(MouseEvent e) {}
            });
  }
}
