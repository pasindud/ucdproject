
package FLOG_LOGIC;

import SHARED_UTILS.MyChatListener;
import SHARED_UTILS.MyConnectionListener;
import SHARED_UTILS.MyNotifyListener;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Pasindu
 */
public class Server {

  /**
   * Name of the channel.
   */
  private static String channelName = "";
  /**
   * Object used to send commands to other users.
   */
  private Multiplayer multiplayer;
  private String serverQueueName = "";
  private ArrayList<String> playerNames = new ArrayList<String>();
  private boolean isServerStart = false;
  private Game game = null;
  WarpClient myGame;
  private boolean isGameStart = false;

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
    StringBuilder clientMessage = new StringBuilder();
    String code = segments[0];
    String content = segments[1];
    String name = "";
    Integer round;
    //    Utils.COMMAND_CODES codes = Utils.COMMAND_CODES.valueOf(code);
    switch (Utils.COMMAND_CODES.getValue(code)) {
        // New user joined format - 100 <player name>
      case CLIENT_JOIN_GAME_CODE:
        String playerName = content;
        content = content.trim();
        if (game.getPlayerfromName(content) != null || isGameStart) {
          return;
        } else {
          System.out.println("777 Player name " + content);
        }
        String playerQueue = multiplayer.getClientQueue(channelName, content);
        // Message to acknowledge that the server received the message
        clientMessage
            .append(Utils.COMMAND_CODES.SERVER_USER_JOINED_ACK + " ")
            .append("ackJoinServer");
        myGame.sendPrivateChat(playerQueue, clientMessage.toString());
        playerNames.add(content);
        game.addPlayer(content);
        // Sending a message to chat saying joined visible to all connected users
        // format 111 <player name>
        String msg = "111 " + content;
        multiplayer.broadcast(channelName, playerNames, msg);
        break;
      case CLIENT_GAME_START_CODE:
        // Start game
        isGameStart = true;
        startGame();
        break;
      case BROADCAST_JOIN_GAME_CODE: // Initial Letters for next round.
        name = segments[2];
        round = Integer.parseInt(segments[3]);
        String firstLetter = segments[4];
        String secondLetter = segments[5];
        int userIndex = game.getIndexByPlayerName(name);
        String[] initialLetters = {firstLetter, secondLetter};
        game.getPlayerRoundForRound(name, round).setIntialLetters(initialLetters);
        break;
      case CLIENT_END_ROUND_CODE: // Use has finished the message.
        if (segments.length < 8) {
          System.err.println("Game round end message format invalid Message " + message);
          return;
        } else {
          System.out.println("Game round end message correct");
        }
        handleEndRound(segments);
        break;
      case CLIENT_PENALIZE_WEKEST:
        //Format 115 <Winner> <Weakest>
        String weakestMsg = Utils.COMMAND_CODES.SERVER_PENALIZE_WEKEST + " " + segments[1];
        if (segments.length == 3) {
          weakestMsg += " " + segments[2];
        }

        multiplayer.broadcast(channelName, playerNames, weakestMsg);
        break;
      default:
        System.err.println("Found nothing");
        break;
    }
  }

  /**
   * Number of users already finished the round.
   */
  private int numberOfUsersFinishedRound = 0;

  private void handleEndRound(String[] segments) {
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

    PlayerRound currentRound = game.getPlayerRoundForRound(name, round);
    if (currentRound == null) {
      System.err.println("Current round is null - i " + round + " name " + name);
    }
    System.out.println("Word is : " + word);

    currentRound.setWord(new WordElement(word));
    currentRound.setIsWordSearchUsed(isAutoGenUsed);
    currentRound.setOtherLetters(otherLetters);
    currentRound.setIntialLetters(initialLetters);
    currentRound.calculateScore();
    ++numberOfUsersFinishedRound;

    StringBuilder message = new StringBuilder();
    int totalScore = game.getPlayerfromName(name).getNowTotalScore();
    int score = currentRound.getScore();
    String letters = segments[4] + " " + segments[5];

    // Example - 204 roundScore <player name> <round number>
    //           <score> <totalScore> <initial letters> <other letters> <word>
    //           204 roundScore pasindu 1 10 100
    message
        .append(Utils.COMMAND_CODES.SERVER_ROUND_USER_SCORE)
        .append(" ")
        .append("roundScore")
        .append(" ")
        .append(name)
        .append(" ")
        .append(round)
        .append(" ")
        .append(score)
        .append(" ")
        .append(totalScore)
        .append(" ")
        .append(letters)
        .append(" ")
        .append(word);
    multiplayer.broadcast(channelName, playerNames, message.toString());
    if (playerNames.size() == numberOfUsersFinishedRound) {
      numberOfUsersFinishedRound = 0;
      // All users sent the round details.
    } else {

    }
  }
}
