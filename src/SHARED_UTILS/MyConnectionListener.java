/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SHARED_UTILS;

import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;
import java.util.HashMap;

/**
 * Handles connections.
 * @author Pasindu
 */
public class MyConnectionListener implements ConnectionRequestListener {
  private String username = "";

  public MyConnectionListener(String username) {
    this.username = username;
  }

  @Override
  public void onConnectDone(ConnectEvent event) {
    if (event.getResult() == WarpResponseResultCode.SUCCESS) {
      System.out.println("User connected  " + username + " have connected ");
    }
  }

  @Override
  public void onDisconnectDone(ConnectEvent event) {}

  @Override
  public void onInitUDPDone(byte paramByte) {}
}
