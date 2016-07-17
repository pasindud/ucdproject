/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;
import java.util.HashMap;

/**
 * Handles notifications.
 * @author Pasindu
 */
public class MyNotifyListener implements NotifyListener {
    private String username;
    public MyNotifyListener(){
    };
    public void MyNotifyListener(String username) {
        this.username = username;
    }    
    @Override
    public void onRoomCreated(RoomData rd) {}

    @Override
    public void onRoomDestroyed(RoomData rd) {}

    @Override
    public void onUserLeftRoom(RoomData rd, String string) {}

    @Override
    public void onUserJoinedRoom(RoomData rd, String string) {}

    @Override
    public void onUserLeftLobby(LobbyData ld, String string) {}

    @Override
    public void onUserJoinedLobby(LobbyData ld, String string) {}

    @Override
    public void onChatReceived(ChatEvent ce) {}

    @Override
    public void onPrivateChatReceived(String string, String string1) {}

    @Override
    public void onPrivateUpdateReceived(String string, byte[] bytes, boolean bln) {}

    @Override
    public void onUpdatePeersReceived(UpdateEvent ue) {}

    @Override
    public void onUserChangeRoomProperty(RoomData rd, String string, HashMap<String, Object> hm, HashMap<String, String> hm1) {}

    @Override
    public void onMoveCompleted(MoveEvent me) {}

    @Override
    public void onGameStarted(String string, String string1, String string2) {}

    @Override
    public void onGameStopped(String string, String string1) {}

    @Override
    public void onUserPaused(String string, boolean bln, String string1) {}

    @Override
    public void onUserResumed(String string, boolean bln, String string1) {}

    @Override
    public void onNextTurnRequest(String string) {}
}
