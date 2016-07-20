
package FLOG_GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import org.omg.CORBA.MARSHAL;

/**
 *
 * @author Dushan Galappaththi
 */
public class ControllerSettings {
  String returnTo = DataForUI.STR_MAINMENU;
  PanelSettings panelSettings;
  GameScreen gameScreen;
  int mouseX = 0;
  int mouseY = 0;
  int soundLevel = 0;
  String playerName;
  boolean isMute = false;

  public ControllerSettings(PanelSettings panelSettings, GameScreen gameScreen) {
    this.panelSettings = panelSettings;
    this.gameScreen = gameScreen;
    initializeSettingsListeners();
    getSavedSettings();
    setSavedSettings();
  }

  private void getSavedSettings() {
    soundLevel = 100; //Add a way to get saved sound level from user save file.
    playerName = "New Player"; //Add a way to get saved player name from user save file.
    isMute = false; //Add a way to get saved sound(on/off) from user save file.
  }

  //Change screen back to where ever it was called.
  private void backClick() {
    gameScreen.changeScreen(this.returnTo, null);
  }

  private void soundToggleClick() {
    if (isMute) {
      isMute = false;

    } else {
      isMute = true;
    }
  }

  private void volReduceClick() {
    if (soundLevel > 0) {
      soundLevel = soundLevel - 5;
      panelSettings.setSoundLevel(soundLevel);
    }
  }

  private void volIncreaseClick() {
    if (soundLevel < 100) {
      soundLevel = soundLevel + 5;
      panelSettings.setSoundLevel(soundLevel);
    }
  }

  private void setSavedSettings() {
    panelSettings.setPlayerName(DataForUI.currentUsername);
    panelSettings.setSoundLevel(soundLevel);
  }

  private void saveSettings(String playerName) {
    //Save Settings
  }

  public void setReturnTo(String returnTo) {
    this.returnTo = returnTo;
  }

  /**
   * Below Code listens for events happening in the UI,
   * which is the 'PanelSettings' JPanel and Handles Label icon transition
   * to give the feel of a custom button
   */
  private void initializeSettingsListeners() {
    final int _TopBorder = 0;
    final int _back = 1;
    final int _SoundToggle = 2;
    final int _VolReduce = 3;
    final int _VolIncrease = 4;

    panelSettings
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

    panelSettings
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

    panelSettings
        .getCompCon(_back)
        .addMouseListener(
            new MouseListener() {

              @Override
              public void mouseClicked(MouseEvent e) {
                saveSettings(panelSettings.getplayerName());
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_back_h.png"));
                panelSettings.setIcon_Back(imgIcon);
                backClick();
              }

              @Override
              public void mousePressed(MouseEvent e) {
              ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_back_c.png"));
                panelSettings.setIcon_Back(imgIcon);
              }

              @Override
              public void mouseReleased(MouseEvent e) {
              ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_back_h.png"));
                panelSettings.setIcon_Back(imgIcon);
              }

              @Override
              public void mouseEntered(MouseEvent e) {
              ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_back_h.png"));
                panelSettings.setIcon_Back(imgIcon);
              }

              @Override
              public void mouseExited(MouseEvent e) {
              ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_back_n.png"));
                panelSettings.setIcon_Back(imgIcon);
              }
            });

    panelSettings
        .getCompCon(_SoundToggle)
        .addMouseListener(
            new MouseListener() {

              @Override
              public void mouseClicked(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_soundon_h.png"));
                panelSettings.setIcon_Sound(imgIcon);
                soundToggleClick();
              }

              @Override
              public void mousePressed(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_soundon_c.png"));
                panelSettings.setIcon_Sound(imgIcon);
              }

              @Override
              public void mouseReleased(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_soundon_h.png"));
                panelSettings.setIcon_Sound(imgIcon);
              }

              @Override
              public void mouseEntered(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_soundon_h.png"));
                panelSettings.setIcon_Sound(imgIcon);
              }

              @Override
              public void mouseExited(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_soundon_n.png"));
                panelSettings.setIcon_Sound(imgIcon);
              }
            });

    panelSettings
        .getCompCon(_VolIncrease)
        .addMouseListener(
            new MouseListener() {

              @Override
              public void mouseClicked(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_plus_h.png"));
                panelSettings.setIcon_VolIncrease(imgIcon);
                  
                volIncreaseClick();
              }

              @Override
              public void mousePressed(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_plus_c.png"));
                panelSettings.setIcon_VolIncrease(imgIcon);
              }

              @Override
              public void mouseReleased(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_plus_h.png"));
                panelSettings.setIcon_VolIncrease(imgIcon);
              }

              @Override
              public void mouseEntered(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_plus_h.png"));
                panelSettings.setIcon_VolIncrease(imgIcon);
              }

              @Override
              public void mouseExited(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_plus_n.png"));
                panelSettings.setIcon_VolIncrease(imgIcon);
              }
            });

    panelSettings
        .getCompCon(_VolReduce)
        .addMouseListener(
            new MouseListener() {

              @Override
              public void mouseClicked(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_minus_h.png"));
                panelSettings.setIcon_VolReduce(imgIcon);
                volReduceClick();
              }

              @Override
              public void mousePressed(MouseEvent e) {
              ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_minus_c.png"));
                panelSettings.setIcon_VolReduce(imgIcon);
              }

              @Override
              public void mouseReleased(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_minus_h.png"));
                panelSettings.setIcon_VolReduce(imgIcon);
              }

              @Override
              public void mouseEntered(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_minus_h.png"));
                panelSettings.setIcon_VolReduce(imgIcon);
              }

              @Override
              public void mouseExited(MouseEvent e) {
                  ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_minus_n.png"));
                panelSettings.setIcon_VolReduce(imgIcon);
              }
            });
  }
}
