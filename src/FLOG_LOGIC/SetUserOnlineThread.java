/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pasindu
 */
public class SetUserOnlineThread extends Thread {
  /**
   * Every 5 mins the users last online time is reset to current time.
   */
  private static final int UPDATE_USER_ONLINE_TIME = 50000;
  String username = "";

  SetUserOnlineThread(String username) {
    this.username = username;
  }

  @Override
  public void run() {
    try {
      while (true) {
        Thread.sleep(UPDATE_USER_ONLINE_TIME);
        if (!username.equals("")) {
          Multiplayer multiplayer = new Multiplayer();
          multiplayer.setOnline(username);
        } else {
          break;
        }
      }
    } catch (InterruptedException ex) {
      return;
    }
  }
}
