
package FLOG_GUI;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dushan Galappaththi
 */
public class ThreadTimer implements Runnable
{
    PanelGamePlay panelGamePlay;
    int seconds=0;

    public ThreadTimer(PanelGamePlay panelGamePlay, int seconds) {
        this.panelGamePlay = panelGamePlay;
        this.seconds =seconds;
    }
    

    @Override
    public void run() 
    {
      startCountDown(seconds);
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
    
    }
    
}
