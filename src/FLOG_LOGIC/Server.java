/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import SHARED_UTILS.MyChatListener;
import SHARED_UTILS.MyConnectionListener;
import SHARED_UTILS.MyNotifyListener;
import FLOG_GUI.SelectMultiPlayer;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ChatRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.RoomRequestListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pasindu
 */
public class Server {
  /** Name of the channel. */
  private static String channelName = "";
  /** Object used to send commands to other users. */
  private Multiplayer multiplayer;
  private String serverQueueName = "";
  private ArrayList<String> playerNames = new ArrayList<String>();
  private boolean isServerStart = false;
  private Game game = null;
  WarpClient myGame;

  public Server(String channelName) {
    WarpClient.initialize(Utils.WRAP_API_KEY, Utils.WRAP_SECRET_KEY);
    this.channelName = channelName;
    multiplayer = new Multiplayer();
    this.serverQueueName = multiplayer.getServerQueue(channelName);
  }

  public void setupWrap() {
    try {
      HashMap<String, Object> data = new HashMap<String, Object>();
      WarpClient.initialize(Utils.WRAP_API_KEY, Utils.WRAP_SECRET_KEY, "50.112.253.86");
      WarpClient.enableTrace(true);
      WarpClient.setRecoveryAllowance(10);
      myGame = (WarpClient) WarpClient.getInstance();
      String serverQueueName = multiplayer.getServerQueue(channelName);
      myGame.addConnectionRequestListener(
          new MyConnectionListener(serverQueueName) {
            @Override
            public void onConnectDone(ConnectEvent event) {
              if (event.getResult() == WarpResponseResultCode.SUCCESS) {
                System.out.println("[Server] have connected");
              } else {
                System.err.println("Server error connecting " + event.getResult());
              }
            }
          });
      myGame.addNotificationListener(
          new MyNotifyListener() {
            public void MyNotifyListener(String clientQueueName) {}

            @Override
            public void onPrivateChatReceived(String from, String message) {
              System.out.println("Msg from - " + from + " - M - " + message);
              decodeServerMessage(message);
            };
          });
      myGame.addChatRequestListener(new MyChatListener());
      myGame.connectWithUserName(serverQueueName);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void start() {
    if (isServerStart) {
      System.err.println("Server is already started");
      return;
    }
    game = new Game();
    setupWrap();
    multiplayer.setMyGame(myGame);
    isServerStart = true;
  }

  public void startGame() {
    // Format - 201 startgame <player names>
    // TODO player names should not have spaces.
    String strPlagerNames = String.join(",", playerNames);
    strPlagerNames = strPlagerNames.replaceFirst("^,", "");
    String message = "201 startgame " + strPlagerNames;
    multiplayer.broadcast(channelName, playerNames, message);
  }

  /**
   * Decode the message received by the server.
   */
  private synchronized void decodeServerMessage(String message) {
    System.out.println("Decoding message to server message - [" + message + "]");
    String[] segments = message.split(" ");
    if (segments.length < 2) {
      return;
    }
    String code = segments[0];
    String content = segments[1];
    String name = "";
    Integer round;
    switch (code) {
        // New user joined format - 100 <player name>
      case "100":
        String playerName = content;
        // setServerStatus("User " + content + " joined ");
        String playerQueue = multiplayer.getClientQueue(channelName, playerName);

        // Message to acknowledge that the server received the message
        String clientMessage = "200 ackJoinServer";
        System.out.println("private message to - " + playerQueue + " from ");
        myGame.sendPrivateChat(playerQueue, clientMessage);

        // multiplayer.publishToQueue(playerQueue, clientMessage);
        name = content.trim();
        playerNames.add(name);
        game.addPlayer(name);
        break;
        // User start the gui- ack game start .
      case "103":
        System.out.println(content);
        break;
      case "104":
        System.out.println(content);
        break;
      case "108":
        // Start game
        startGame();
        break;
      case "105":
        // Format - 104 letters <player name> a,s,d,g,e,q,q,r,t
        String letters = segments[2];
        letters = letters.replaceFirst("^,", "");
        String[] lettersArray = letters.split(",");
        break;
      case "304": // Initial Letters for next round.
        name = segments[2];
        round = Integer.parseInt(segments[3]);
        String firstLetter = segments[4];
        String secondLetter = segments[5];
        int userIndex = game.getIndexByPlayerName(name);
        String[] initialLetters = {firstLetter, secondLetter};
        game.getPlayerRoundForRound(name, round).setIntialLetters(initialLetters);
        break;
      case "106": // Use has finished the message.
        if (segments.length < 8) {
          System.err.println("Game round end message format invalid Message " + message);
          return;
        } else {
          System.out.println("Game round end message correct");
        }
        handleEndRound(segments);
        break;
      default:
        System.err.println("Found nothing");
        break;
    }
  }

  /** Number of users already finished the round.*/
  private int numberOfUsersFinishedRound = 0;

  private void handleEndRound(String[] segments) {
    System.out.println("121212121212");
    String name = segments[2];
    int round = Integer.parseInt(segments[3]);
    String[] initialLetters = segments[4].split(",");
    String[] otherLetters = segments[5].split(",");
    String completedTime = segments[6];
    boolean isAutoGenUsed = Boolean.valueOf(segments[7]);
    String word = "";
    if (segments.length == 9) {
      word = segments[8];
    }
    System.out.println("10101010101");
    PlayerRound currentRound = game.getPlayerRoundForRound(name, round);
    if (currentRound == null) {
      System.err.println("Current round is null - i " + round + " name " + name);
    }
    currentRound.setWord(new WordElement(word));
    currentRound.setIsWordSearchUsed(isAutoGenUsed);
    currentRound.setOtherLetters(otherLetters);
    currentRound.setIntialLetters(initialLetters);
    currentRound.calculateScore();

    System.out.println("111111111111");
    ++numberOfUsersFinishedRound;
    int totalScore = game.getPlayerfromName(name).getNowTotalScore();
    int score = currentRound.getScore();

    // Example - 107 roundScore <player name> <round number>  <score> <totalScore>
    //           107 roundScore pasindu 1 10 100
    String message = "204 roundScore" + " " + name + " " + round + " " + score + " " + totalScore;
    System.out.println(message);
    multiplayer.broadcast(channelName, playerNames, message);

    if (playerNames.size() == numberOfUsersFinishedRound) {
      numberOfUsersFinishedRound = 0;
      // All users sent the round details.
    } else {

    }
  }
}
