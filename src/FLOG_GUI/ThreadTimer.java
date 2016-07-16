
package FLOG_GUI;

import FLOG_LOGIC.Utils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dushan Galappaththi
 */
public class ThreadTimer implements Runnable
{
    PanelGamePlay panelGamePlay;
    PanelRoundReadyUp panelRoundReadyUp;
    ControllerRoundReadyUp controllerRoundReadyUp;
    ControllerGamePlay controllerGamePlay;
    
    boolean isRoundReadyUp=false;
    boolean isGamePlay=false;
    
    int seconds=0;

    public ThreadTimer(PanelGamePlay panelGamePlay, ControllerGamePlay controllerGamePlay, int seconds) {
        this.panelGamePlay = panelGamePlay;
        this.controllerGamePlay = controllerGamePlay;
        this.seconds =seconds;
        isGamePlay=true;
        isRoundReadyUp=false;
    }

    public ThreadTimer(PanelRoundReadyUp panelRoundReadyUp,ControllerRoundReadyUp controllerRoundReadyUp, int seconds) {
        this.panelRoundReadyUp =panelRoundReadyUp;
        this.controllerRoundReadyUp=controllerRoundReadyUp;
        this.seconds = seconds;
         isGamePlay=false;
        isRoundReadyUp=true;
    }
    

    @Override
    public void run() 
    {
        if(isGamePlay)
        {
            startCountDown(seconds);
        }  
        if(isRoundReadyUp)
        {
            startReadyUpCountDown(seconds);
        }
      return;
    }
    
    private void startCountDown(int sec)
    {
        for(int i=sec;i>=0;i--)
        {
            if(ControllerGamePlay.stopTimer)
            {
                ControllerGamePlay.stopTimer=false;
                return;
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                
            }
            if(i<10)
            {
                panelGamePlay.setTimer("0"+i);
            }
            else
            {
                panelGamePlay.setTimer(""+i);
            }
        }
    
        if(DataForUI.RoundNum<DataForUI.RoundLimit)
        {
            /*String playerName, int roundNum, 
            boolean isAutoGenUsed, String word, String timeRemanString,
            String[] initialLetters, String[] otherLetters*/
            controllerGamePlay.startNextRound();
        }
        else
        {
            controllerGamePlay.endGame();
        }
    }
    
    private void startReadyUpCountDown(int sec)
    {
        
        for(int i=sec;i>=0;i--)
        {
                        
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                
            }
            if(i<10)
            {
                panelRoundReadyUp.setTimer("0"+i);
                
            }
            else
            {
                panelRoundReadyUp.setTimer(""+i);
            }
        }
        controllerRoundReadyUp.startRound();
    }
    
}
