/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ChatRequestListener;
import java.util.ArrayList;

/**
 * Gives message status.
 * @author Pasindu
 */
public class MyChatListener implements ChatRequestListener {

    @Override
    public void onSendChatDone(byte b) {
    }

    @Override
    public void onSendPrivateChatDone(byte b) {
    }

    @Override
    public void onGetChatHistoryDone(byte b, ArrayList<ChatEvent> al) {
    }

}
