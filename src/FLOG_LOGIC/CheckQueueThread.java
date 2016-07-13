/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import static java.awt.SystemColor.desktop;
import java.util.ArrayList;
import java.util.List;

import FLOG_GUI.*;
import com.shephertz.app42.paas.sdk.java.message.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pasindu
 */
public class CheckQueueThread extends Thread {

    private String queueName = null;
    private Multiplayer multiplayer = new Multiplayer();

    private Thrower thrower;
    private String decodeEventName = "messageInQueue";

    private long POLLING_INTERVEL = 5000; // 5 Seconds
    public CheckQueueThread(String queueName, Thrower thrower) {
        this.queueName = queueName;
        this.thrower = thrower;
    }

    @Override
    public void run() {
        // Checks the queue every 5 seconds
        // This should be killed with the parent thread.
        while(true){
            try {
                sleep(POLLING_INTERVEL);
            } catch (InterruptedException ex) {
                Logger.getLogger(CheckQueueThread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Closing the thread checking the queue " + queueName);
                break;
            }
            List<String> messages = multiplayer.readQueue(queueName);

            for (String message : messages) {
                thrower.Throw(message);
                System.out.println("CheckQueue - Throw - " + message);
            }
        }
    }

}
