
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
        TestGUI_Inputs testing = new TestGUI_Inputs();
        if(DataForUI.RoundNum==2)
        {
            //testing.listOfPlayers2();
            //controllerGamePlay
            controllerGamePlay.drawOpponenets();
        }
         
        gameScreen.changeScreen(DataForUI.STR_GAMEPLAY, null);
        controllerGamePlay.beginRound();
    }
    public void drawPlayers()
    {
        panelRoundReadyUp.drawPlayers();
    }
    public void setTitlePlayers(boolean isPlayers)
    {
        panelRoundReadyUp.setTitlePlayers(isPlayers);
    }
}
