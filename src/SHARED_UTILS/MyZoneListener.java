/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SHARED_UTILS;

import com.shephertz.app42.gaming.multiplayer.client.events.AllRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.AllUsersEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveUserInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.MatchedRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ZoneRequestListener;

/**
 *
 * @author Dushan Galappaththi
 */
public class MyZoneListener implements ZoneRequestListener {

  @Override
  public void onDeleteRoomDone(RoomEvent re) {}

  @Override
  public void onGetAllRoomsDone(AllRoomsEvent are) {}

  @Override
  public void onCreateRoomDone(RoomEvent re) {}

  @Override
  public void onGetOnlineUsersDone(AllUsersEvent aue) {}

  @Override
  public void onGetLiveUserInfoDone(LiveUserInfoEvent luie) {}

  @Override
  public void onSetCustomUserDataDone(LiveUserInfoEvent luie) {}

  @Override
  public void onGetMatchedRoomsDone(MatchedRoomsEvent mre) {}

  @Override
  public void onGetAllRoomsCountDone(AllRoomsEvent are) {}

  @Override
  public void onGetOnlineUsersCountDone(AllUsersEvent aue) {}

  @Override
  public void onGetUserStatusDone(LiveUserInfoEvent luie) {}
}
