<<<<<<< HEAD
package FLOG_GUI;

/**
 *
 * @author Dushan Galappaththi
 */
public class ThreadTimer implements Runnable {

  PanelGamePlay panelGamePlay;
  PanelRoundReadyUp panelRoundReadyUp;
  ControllerRoundReadyUp controllerRoundReadyUp;
  ControllerGamePlay controllerGamePlay;

  boolean isRoundReadyUp = false;
  boolean isGamePlay = false;

  int seconds = 0;

  public ThreadTimer(
      PanelGamePlay panelGamePlay, ControllerGamePlay controllerGamePlay, int seconds) {
    this.panelGamePlay = panelGamePlay;
    this.controllerGamePlay = controllerGamePlay;
    this.seconds = seconds;
    isGamePlay = true;
    isRoundReadyUp = false;
  }

  public ThreadTimer(
      PanelRoundReadyUp panelRoundReadyUp,
      ControllerRoundReadyUp controllerRoundReadyUp,
      int seconds) {
    this.panelRoundReadyUp = panelRoundReadyUp;
    this.controllerRoundReadyUp = controllerRoundReadyUp;
    this.seconds = seconds;
    isGamePlay = false;
    isRoundReadyUp = true;
  }

  @Override
  public void run() {
    if (isGamePlay) {
      startCountDown(seconds);
    }
    if (isRoundReadyUp) {
      startReadyUpCountDown(seconds);
    }
    return;
  }

  private void startCountDown(int sec) {
    for (int i = sec; i >= 0; i--) {
      if (ControllerGamePlay.stopTimer) {
        ControllerGamePlay.stopTimer = false;
        return;
      }

      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {

      }
      if (i < 10) {
        panelGamePlay.setTimer("0" + i);
      } else {
        panelGamePlay.setTimer("" + i);
      }
    }

    if (DataForUI.RoundNum < DataForUI.RoundLimit) {
      controllerGamePlay.startNextRound();
    } else {
      controllerGamePlay.endGame();
    }
  }

  private void startReadyUpCountDown(int sec) {

    for (int i = sec; i >= 0; i--) {

      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {

      }
      if (i < 10) {
        panelRoundReadyUp.setTimer("0" + i);

      } else {
        panelRoundReadyUp.setTimer("" + i);
      }
    }
    controllerRoundReadyUp.startRound();
  }
}
=======
package FLOG_GUI;

/**
 *
 * @author Dushan Galappaththi
 */
public class ThreadTimer implements Runnable {

    PanelGamePlay panelGamePlay;
    PanelRoundReadyUp panelRoundReadyUp;
    ControllerRoundReadyUp controllerRoundReadyUp;
    ControllerGamePlay controllerGamePlay;

    boolean isRoundReadyUp = false;
    boolean isGamePlay = false;

    int seconds = 0;

    /**
     * This Thread Class is used by both timers, hence the two constructors
     */
        
    /**
     * This Constructor is to be used by the GamePlay screen
     * @param panelGamePlay
     * @param controllerGamePlay
     * @param seconds 
     */
    
    
    public ThreadTimer(
            PanelGamePlay panelGamePlay, ControllerGamePlay controllerGamePlay, int seconds) {
        this.panelGamePlay = panelGamePlay;
        this.controllerGamePlay = controllerGamePlay;
        this.seconds = seconds;
        isGamePlay = true;
        isRoundReadyUp = false;
    }
    
    /**
     * This Constructor to be used by the Round ready Screen
     * @param panelRoundReadyUp
     * @param controllerRoundReadyUp
     * @param seconds 
     */

    public ThreadTimer(
            PanelRoundReadyUp panelRoundReadyUp,
            ControllerRoundReadyUp controllerRoundReadyUp,
            int seconds) {
        this.panelRoundReadyUp = panelRoundReadyUp;
        this.controllerRoundReadyUp = controllerRoundReadyUp;
        this.seconds = seconds;
        isGamePlay = false;
        isRoundReadyUp = true;
    }

    @Override
    public void run() {
        if (isGamePlay) {
            startCountDown(seconds);
        }
        if (isRoundReadyUp) {
            startReadyUpCountDown(seconds);
        }
        return;
    }

    /**
     * Used to set and run the timer of Gameplay round 
     * @param sec 
     */
    private void startCountDown(int sec) {
        for (int i = sec; i >= 0; i--) {
            if (ControllerGamePlay.stopTimer) {
                ControllerGamePlay.stopTimer = false;
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
            if (i < 10) {
                panelGamePlay.setTimer("0" + i);
            } else {
                panelGamePlay.setTimer("" + i);
            }
        }

        if (DataForUI.RoundNum < DataForUI.RoundLimit) {
            controllerGamePlay.startNextRound();
        } else {
            controllerGamePlay.endGame();
        }
    }

    /**
     *
     * @param sec used to set the timer of Round ready UP screen
     */

    private void startReadyUpCountDown(int sec) {

        for (int i = sec; i >= 0; i--) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
            if (i < 10) {
                panelRoundReadyUp.setTimer("0" + i);

            } else {
                panelRoundReadyUp.setTimer("" + i);
            }

        }
        controllerRoundReadyUp.startRound();
    }
}
>>>>>>> 5b0306fb1a2569381631bb90292f679d62ffc1a8
