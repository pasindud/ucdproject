
package FLOG_GUI;

/**
 *
 * @author Dushan Galappaththi
 */
public class ControllerRoundReadyUp {
    Thread timerThread;
    PanelRoundReadyUp panelRoundReadyUp;
    GameScreen gameScreen;
    ControllerGamePlay controllerGamePlay;

    public ControllerRoundReadyUp(PanelRoundReadyUp panelRoundReadyUp, GameScreen gameScreen, ControllerGamePlay controllerGamePlay) {
        this.panelRoundReadyUp = panelRoundReadyUp;
        this.gameScreen = gameScreen;
        this.controllerGamePlay = controllerGamePlay;
    }
    
    
    /**
      * Start Round ReadyUP Timer
      * If you want to Change Round time, then change the value of
      * the RoundReadyUpTime variable at DataForUI class 
      */
    public void runTimer()
     {
         timerThread = new Thread(new ThreadTimer(panelRoundReadyUp,this, DataForUI.RoundReadyUpTime));
         timerThread.start();
     }
    
    //Switching to gameplay screen and begin the round
    public void startRound()
    {
        gameScreen.changeScreen(DataForUI.STR_GAMEPLAY, null);
        controllerGamePlay.beginRound();
    }
    
}
