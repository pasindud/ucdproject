package FLOG_GUI;

import FLOG_LOGIC.Utils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Control the game play area.
 * @author Dushan Galappaththi
 */
public class ControllerGamePlay {
  /* Main game panel. */ 
  PanelGamePlay panelGamePlay;
  /* Game frame that the panel is in. */
  GameScreen gameScreen;
  /* Thread used for keeping time of the game. */
  Thread timerThread;
  /* Whether the timer is stoped. */
  static boolean stopTimer = false;
  /* Used to move the window. */
  int mouseX = 0;
  int mouseY = 0;
  /* Letters shown the user. */
  char[] letterArr = new char[12];
  /* Contains data need to the UIs. */
  DataForUI data;
  /* Contains the answere give by the user. */
  public String answere;
  /* Whether auto word generation was used. */
  public boolean isAutoGenerate = false;
  /* total marks of the currect user. */
  int myTotalScore;
  /* Rank of the user*/
  int rank;

  /**
   * Setup the main gaming panel.
   */
  public ControllerGamePlay(PanelGamePlay panelGamePlay, GameScreen gameScreen) {

    this.panelGamePlay = panelGamePlay;
    this.gameScreen = gameScreen;
    //        data = new DataForUI();
    initializeGamePlayListeners();
    setPlayerInfo(gameScreen.username, 999, 0, DataForUI.RoundNum);
    if (data == null) {
      return;
    }
    int total = data.scoresMap.get(gameScreen.username);
    setPlayerInfo(gameScreen.username, 999, total, DataForUI.RoundNum);
    panelGamePlay.drawOpponents(data.getPdArray());
  }

  //Use this method to update the player's info
  public void setPlayerInfo(String playerName, int Rank, int score, int round) {
    panelGamePlay.drawPlayerName(playerName);
    panelGamePlay.drawPlayerPosition(Rank);
    panelGamePlay.drawPlayerScore(score);
    panelGamePlay.drawRoundNumber(round);
  }

  public void drawOpponenets() {
    int total = data.scoresMap.get(gameScreen.username);
    setPlayerInfo(gameScreen.username, 999, total, DataForUI.RoundNum);
    try {
      data.preparePlayerArrayForUI();
      panelGamePlay.drawOpponents(data.getSortedPdArrayByScore());
    } catch (Exception e) {
      panelGamePlay.drawOpponents(data.getPdArray());
    }
  }

  /* Handles submitting of the answer. */
  private void submitClick(String ans) {
    if (ans == "Answer") {
      answere = "";
    } else {
      answere = ans;
    }
    String msg = "210 " + DataForUI.currentUsername;
    gameScreen.multiplayer.broadcast(
        DataForUI.currentChannel, gameScreen.otherPlayerNames, msg); //***[dushan]
    stopTimer = true;
    this.startNextRound();
  }

  private void vowelsClick() {}

  private void consonentsClick() {}

  /* Setup the round before starting. */
  public void preRoundStart() {
    answere = "";
    panelGamePlay.resetValuesForRound();
  }

  /* Start the round. */
  public void beginRound() {
    runTimer();
  }

  /**
   * Start Round Timer If you want to Change Round time, then change the value
   * of the RoundTime variable at DataForUI class
   */
  private void runTimer() {
    timerThread = new Thread(new ThreadTimer(panelGamePlay, this, DataForUI.RoundTime));
    timerThread.start();
  }

  //Reset Timer
  public void resetTimer() {
    panelGamePlay.setTimer("00");
  }

  /**
   * This method is automatically called when the timer stops, this method
   * will simply change the screen to Round ready up screen and prepare it for
   * the next round
   */
  public void startNextRound() {
    //        GameScreen gameScreen1 = gameScreen
    new Thread() {
      public void run() {
        panelGamePlay.restLabels();
        String[] initialLetters = panelGamePlay.getInitialLetters();
        String[] otherLetters = panelGamePlay.getOtherLetters();
        // Send 106 message to the server telling about the what happened.
        String message =
            Utils.makeRoundEndServerMessage(
                gameScreen.username,
                gameScreen.dataForUI.RoundNum,
                panelGamePlay.isAutoWordGenUsed,
                answere,
                "0",
                initialLetters,
                otherLetters);
        gameScreen.pushtoServerQueue(message);
      }
    }.start();
    if (DataForUI.RoundNum < 6) {
      DataForUI.RoundNum = DataForUI.RoundNum + 1;
    }
    gameScreen.changeScreen(DataForUI.STR_ROUNDREADYUP, DataForUI.STR_GAMEPLAY);
  }

  public void endGame() {
    gameScreen.changeScreen(DataForUI.STR_WINNER, DataForUI.STR_GAMEPLAY);
  }

  //Change screen to settings

  private void settingsClick() {
    gameScreen.changeScreen(DataForUI.STR_SETTINGS, DataForUI.STR_GAMEPLAY);
  }

  /**
   * Directly disconnect from the current game, user will be prompted to
   * confirm their action.
   */
  private void disconnectClick() {
    int res =
        JOptionPane.showConfirmDialog(
            null, "Do you wan to disconnect?", "Warning", JOptionPane.YES_NO_OPTION);
    if (res == JOptionPane.YES_OPTION) {
      if (timerThread.isAlive()) {
        stopTimer = true;
      }
      resetTimer();
      gameScreen.changeScreen(DataForUI.STR_MAINMENU, DataForUI.STR_GAMEPLAY);
    }
  }

  /**
   * Below Code listens for events happening in the UI, which is the
   * PanelGamePlay JPanel and Handles Label icon transition to give the feel
   * of a custom button
   */
  private void initializeGamePlayListeners() {
    //Component ID's
    final int _submit = 15;
    final int _generate = 14;
    final int _vowels = 12;
    final int _consonent = 13;
    final int _answer = 16;
    final int _settings = 1;
    final int _disconnect = 0;

    final JTextField txt = (JTextField) panelGamePlay.getCompBottom(_answer);

    panelGamePlay
        .getCompTop(0)
        .addMouseMotionListener(
            new MouseMotionListener() {

              @Override
              public void mouseDragged(MouseEvent e) {

                gameScreen.moveScreen(e.getXOnScreen(), e.getYOnScreen(), mouseX, mouseY);
              }

              @Override
              public void mouseMoved(MouseEvent e) {}
            });

    panelGamePlay
        .getCompTop(0)
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

    panelGamePlay
        .getCompBottom(_submit)
        .addMouseListener(
            new MouseListener() {

              @Override
              public void mouseClicked(MouseEvent e) {
                //System.out.println("[I] Submit was clicked | value on textbox : " + txt.getText().toString());
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_submit_h.png"));
                panelGamePlay.setIcon_Submit(imgIcon);
                submitClick(txt.getText().toString());
                txt.setText("");
              }

              @Override
              public void mousePressed(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_submit_c.png"));
                panelGamePlay.setIcon_Submit(imgIcon);
              }

              @Override
              public void mouseReleased(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_submit_h.png"));
                panelGamePlay.setIcon_Submit(imgIcon);
              }

              @Override
              public void mouseEntered(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_submit_h.png"));
                panelGamePlay.setIcon_Submit(imgIcon);
              }

              @Override
              public void mouseExited(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_submit_n.png"));
                panelGamePlay.setIcon_Submit(imgIcon);
              }
            });

    panelGamePlay
        .getCompBottom(_generate)
        .addMouseListener(
            new MouseListener() {
              @Override
              public void mouseClicked(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_autosearch_h.png"));
                panelGamePlay.setIcon_Gen(imgIcon);
              }

              @Override
              public void mousePressed(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_autosearch_c.png"));
                panelGamePlay.setIcon_Gen(imgIcon);
              }

              @Override
              public void mouseReleased(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_autosearch_h.png"));
                panelGamePlay.setIcon_Gen(imgIcon);
              }

              @Override
              public void mouseEntered(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_autosearch_h.png"));
                panelGamePlay.setIcon_Gen(imgIcon);
              }

              @Override
              public void mouseExited(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_autosearch_n.png"));
                panelGamePlay.setIcon_Gen(imgIcon);
              }
            });

    panelGamePlay
        .getCompBottom(_vowels)
        .addMouseListener(
            new MouseListener() {

              @Override
              public void mouseClicked(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_vowels_h.png"));
                panelGamePlay.setIcon_Vowels(imgIcon);
                vowelsClick();
              }

              @Override
              public void mousePressed(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_vowels_c.png"));
                panelGamePlay.setIcon_Vowels(imgIcon);
              }

              @Override
              public void mouseReleased(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_vowels_h.png"));
                panelGamePlay.setIcon_Vowels(imgIcon);
              }

              @Override
              public void mouseEntered(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_vowels_h.png"));
                panelGamePlay.setIcon_Vowels(imgIcon);
              }

              @Override
              public void mouseExited(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_vowels_n.png"));
                panelGamePlay.setIcon_Vowels(imgIcon);
              }
            });

    panelGamePlay
        .getCompBottom(_consonent)
        .addMouseListener(
            new MouseListener() {

              @Override
              public void mouseClicked(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_consonent_h.png"));
                panelGamePlay.setIcon_Consonents(imgIcon);
                consonentsClick();
              }

              @Override
              public void mousePressed(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_consonent_c.png"));
                panelGamePlay.setIcon_Consonents(imgIcon);
              }

              @Override
              public void mouseReleased(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_consonent_h.png"));
                panelGamePlay.setIcon_Consonents(imgIcon);
              }

              @Override
              public void mouseEntered(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_consonent_h.png"));
                panelGamePlay.setIcon_Consonents(imgIcon);
              }

              @Override
              public void mouseExited(MouseEvent e) {
                ImageIcon imgIcon =
                    new ImageIcon(getClass().getResource("/images/btn_consonent_n.png"));
                panelGamePlay.setIcon_Consonents(imgIcon);
              }
            });

    panelGamePlay
        .getCompTopBorder(_disconnect)
        .addMouseListener(
            new MouseListener() {

              @Override
              public void mouseClicked(MouseEvent e) {
                disconnectClick();
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

    panelGamePlay
        .getCompTopBorder(_settings)
        .addMouseListener(
            new MouseListener() {

              @Override
              public void mouseClicked(MouseEvent e) {
                settingsClick();
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
