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
import javafx.concurrent.Task;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author Pasindu
 */
public class CheckQueueThread extends Thread {

    volatile boolean cancel = false;

    private String queueName = null;
    private Multiplayer multiplayer = new Multiplayer();

    private Thrower thrower;
    private String decodeEventName = "messageInQueue";
    private boolean die = false;
    private long POLLING_INTERVEL = 1000; // 3 Seconds

    public CheckQueueThread(String queueName, Thrower thrower) {
        this.queueName = queueName;
        this.thrower = thrower;
    }

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    @Override
    public void run() {
        try {
        Runnable task = () -> {
            if (die) {
                try {
                    executor.shutdownNow();
                    throw new Exception();
                } catch (Exception ex) {
                    Logger.getLogger(CheckQueueThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            List<String> messages = multiplayer.readQueue(queueName);
            for (String message : messages) {
                thrower.Throw(message);
                if (!queueName.split("_")[1].equals("server")) {
                    if (message.split(" ")[0].equals("201")) {
                        die = true;
                    }
                }
                System.err.println("[User:" + queueName + "] M - " + message);
            }
            if (die) {
                    executor.shutdownNow();
                System.err.println("Interuppted DIED");
                return;
            }
        };

        long delay = 0;
        long intervel = 1;
        executor.scheduleWithFixedDelay(task, delay, intervel, TimeUnit.SECONDS);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("FLOG_LOGIC.CheckQueueThread.run()");
        }
        // Checks the queue every 3 seconds
        // This should be killed with the parent thread.
        // TODO break on interup.
        /*while (!this.isInterrupted()) {
//            System.err.println("CheckQueue - " + queueName);
            try {
                sleep(POLLING_INTERVEL);
            } catch (InterruptedException ex) {
                Logger.getLogger(CheckQueueThread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Closing the thread checking the queue " + queueName);
                break;
            }
            if (this.isInterrupted()) {
                System.err.println("Interuppted HERER");
                break;
            }
            List<String> messages = multiplayer.readQueue(queueName);
            for (String message : messages) {
                thrower.Throw(message);
                if (!queueName.split("_")[1].equals("server")) {
                    if (message.split(" ")[0].equals("201")) {
                        die = true;
                    }
                }
                System.err.println("[User:" + queueName + "] M - " + message);
            }
            if (die) {
                System.err.println("Interuppted DIED");
                break;

            }
        }*/
        System.err.println("Interuppted Q - " + queueName);
//        return;
    }

    public void cancel() {
        cancel = true;
    }
}
