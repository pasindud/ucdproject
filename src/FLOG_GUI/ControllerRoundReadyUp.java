/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    
    
    public void runTimer()
     {
         timerThread = new Thread(new ThreadTimer(panelRoundReadyUp,this, 10));
         timerThread.start();
     }
    
    public void startRound()
    {
        gameScreen.changeScreen(DataForUI.STR_GAMEPLAY, null);
        controllerGamePlay.runTimer();
    }
    
}
