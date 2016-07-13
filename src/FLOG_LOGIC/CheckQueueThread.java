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

/**
 *
 * @author Pasindu
 */
public class CheckQueueThread extends Thread {

    private String queueName = null;
    private Multiplayer multiplayer = new Multiplayer();

    private Thrower thrower;
    private String decodeEventName = "messageInQueue";

    public CheckQueueThread(String queueName, Thrower thrower) {
        this.queueName = queueName;
        this.thrower = thrower;
    }

    @Override
    public void run() {
        List<String> messages = multiplayer.readQueue(queueName);

        for (String message : messages) {
            thrower.Throw(message);
        }
    }

}
