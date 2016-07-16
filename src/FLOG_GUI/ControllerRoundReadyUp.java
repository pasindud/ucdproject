
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
        controllerGamePlay.preRoundStart();
    
        // Exameple 304 intialLetters pasindu 0 a e
        //          304 intialLetters <player name> <round number> <letter 1> <letter 2>
        String username = gameScreen.username;
        String firstLetter = gameScreen.panelPlaying.firstLetter;
        String secondLetter = gameScreen.panelPlaying.secondLetter;
        int roundNum = gameScreen.dataForUI.RoundNum;
        String message = "304 initialLetters " + username + " " + roundNum + " " + firstLetter + " " + secondLetter;
        String channelName = gameScreen.channelName;
        // Update his users data also.
        String[] initLetters = {firstLetter, secondLetter};
        gameScreen.dataForUI.game.getPlayerRoundForRound(username, roundNum).setIntialLetters(initLetters);
                
        gameScreen.multiplayer.broadcast(channelName, gameScreen.otherPlayerNames, message);
        gameScreen.multiplayer.publishToQueue(gameScreen.serverQueueName, message);
        
       // DataForUI.getPlayerList();
        //controllerGamePlay.drawOpponenets();
        
        timerThread = new Thread(new ThreadTimer(panelRoundReadyUp,this, DataForUI.RoundReadyUpTime));
        timerThread.start();
        
    }
    
    //Switching to gameplay screen and begin the round
    public void startRound()
    {
        //TestGUI_Inputs testing = new TestGUI_Inputs();
        /*if(DataForUI.RoundNum>1)
        {
            //testing.listOfPlayers2();
            //controllerGamePlay
            controllerGamePlay.drawOpponenets();
        }*/
        
        //DataForUI.getPlayerList();
        //controllerGamePlay.drawOpponenets();
         
        gameScreen.changeScreen(DataForUI.STR_GAMEPLAY, DataForUI.STR_ROUNDREADYUP);
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
