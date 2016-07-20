package FLOG_GUI;

import SHARED_UTILS.MyChatListener;
import SHARED_UTILS.MyConnectionListener;
import SHARED_UTILS.MyNotifyListener;
import FLOG_LOGIC.Game;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import FLOG_LOGIC.*;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Dushan Galappaththi
 */
public class GameScreen extends JFrame {

    /**
     * Panel initialization, setting up each panel to cardlayout and
     * initializing controllers for each panel are done in this class.
     *
     * Also manages changing screens and movement of the gamescreen (frame)
     * relative to the player's computer screen is handled through this class
     *
     */
    static int countMe = 0;
    public static PanelGamePlay panelPlaying;
    private PanelMainMenu panelMainMenu;
    private PanelSettings panelSettings;
    private PanelRoundReadyUp panelRoundReadyUp;
    private PanelWinners panelWinners;
    private SelectMultiPlayer panelSelectMultiPlayer;
    private PanelLogin panelLogin;
    private PanelRegister panelRegister;
    private PanelCredits panelCredits;
    private PanelHelp panelHelp;
    public ChatFrame chatFrame;

    //Holds the CardLayout
    private JPanel container;

    //Controllers for Panels
    ControllerGamePlay controllerGamePlay;
    ControllerMainMenu controllerMainMenu;
    ControllerSettings controllerSettings;
    ControllerRoundReadyUp controllerRoundReadyUp;
    ControllerWinners controllerWinners;
    ControllerLogin controllerLogin;
    ControllerRegister controllerRegister;

    //CardLayout which will hold all the panels
    CardLayout mainPanelCards = new CardLayout();

    DataForUI dataForUI = new DataForUI();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        GameScreen myFrame = new GameScreen();
                    }
                });
    }

    //Constructor
    public GameScreen() {
        createAndShowGUI();
    }

    //JFrame setting up
    private void createAndShowGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        preparePanels();
        showLoginScreen();
        this.setSize(new Dimension(900, 619));
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.validate();
        this.setVisible(true);
        chatFrame = new ChatFrame(this);
    }

    private void preparePanels() {
        panelMainMenu = new PanelMainMenu();
        panelPlaying = new PanelGamePlay(this);
        panelSettings = new PanelSettings();
        panelRoundReadyUp = new PanelRoundReadyUp(this);
        panelWinners = new PanelWinners(this);
        panelSelectMultiPlayer = new SelectMultiPlayer(this);
        panelLogin = new PanelLogin();
        panelRegister = new PanelRegister();
        panelCredits = new PanelCredits(this);
        panelHelp = new PanelHelp(this);

        //Adding Panels to Card Layout
        container = new JPanel();

        container.setLayout(mainPanelCards);
        container.add(panelMainMenu, dataForUI.STR_MAINMENU);
        container.add(panelPlaying, dataForUI.STR_GAMEPLAY);
        container.add(panelSettings, dataForUI.STR_SETTINGS);
        container.add(panelRoundReadyUp, dataForUI.STR_ROUNDREADYUP);
        container.add(panelWinners, dataForUI.STR_WINNER);
        container.add(panelSelectMultiPlayer, dataForUI.SelectMultiplayer);
        container.add(panelLogin, dataForUI.STR_LOGIN);
        container.add(panelRegister, dataForUI.STR_REGISTER);
        container.add(panelCredits, dataForUI.STR_CREDITS);
        container.add(panelHelp, dataForUI.STR_HELP);
        this.getContentPane().add(container, BorderLayout.CENTER);

        controllerGamePlay = new ControllerGamePlay(panelPlaying, this);
        controllerRoundReadyUp
                = new ControllerRoundReadyUp(panelRoundReadyUp, this, controllerGamePlay);
        controllerMainMenu = new ControllerMainMenu(panelMainMenu, this, controllerRoundReadyUp);
        controllerSettings = new ControllerSettings(panelSettings, this);
        controllerWinners = new ControllerWinners(panelWinners, this, controllerGamePlay);
        controllerLogin = new ControllerLogin(panelLogin, this);
        controllerRegister = new ControllerRegister(panelRegister, this);
    }

    private void showLoginScreen() {

        changeScreen(dataForUI.STR_LOGIN, dataForUI.STR_LOGIN);
    }

    /**
     * The following Method will handle all change screen calls, basic switching
     * for the panels in the CardLayout
     *
     * @param screenName : The screen to change to
     * @param invokerName : The screen where the change screen is called, this
     * parameter is used to return back to the previous screen
     *
     */
    public void changeScreen(String screenName, String invokerName) {
        CardLayout cl = (CardLayout) (container.getLayout());
        if (invokerName != null) {
            switch (screenName) {
                case DataForUI.SelectMultiplayer:
                    panelSelectMultiPlayer.enableButtons();
                    cl.show(container, screenName);
                    panelSelectMultiPlayer.setPlayerName();
                    break;
                case DataForUI.STR_MAINMENU:
                    DataForUI.isConnectedToServer = false;
                    chatFrame.setVisible(false);
                    cl.show(container, screenName);
                    break;

                case DataForUI.STR_SETTINGS:
                    cl.show(container, screenName);
                    controllerSettings.setReturnTo(invokerName);
                    break;

                case DataForUI.STR_ROUNDREADYUP:
                    cl.show(container, screenName);
                    if (dataForUI.RoundNum == 1) {
                        startRoundUpTimerSystem();
                    }
                    break;

                case DataForUI.STR_WINNER:
                    cl.show(container, screenName);
                    controllerWinners.drawPlayers();
                    controllerWinners.setWinner(dataForUI.myRank);
                    break;

                case DataForUI.STR_GAMEPLAY:
                    DataForUI.getPlayerList();
                    controllerGamePlay.drawOpponenets();
                    cl.show(container, screenName);
                    break;

                case DataForUI.STR_LOGIN:
                    cl.show(container, screenName);
                    break;

                case DataForUI.STR_REGISTER:
                    cl.show(container, screenName);
                    break;

                case DataForUI.STR_CREDITS:
                    cl.show(container, screenName);
                    break;

                case DataForUI.STR_HELP:
                    cl.show(container, screenName);
                    break;

                default:
                    cl.show(container, screenName);
                    break;
            }
        } else {
            cl.show(container, screenName);
        }
        this.validate();
    }

  // Run this only when all the scores are returned.
    // Except the first time.
    public void startRoundUpTimerSystem() {
        if (dataForUI.RoundNum == 6) {
            controllerGamePlay.endGame();
            return;
        }
        controllerRoundReadyUp.runTimer();
        controllerRoundReadyUp.drawPlayers();
        if (DataForUI.RoundNum >= 2) {
            controllerRoundReadyUp.setTitlePlayers(false);
        } else {
            controllerRoundReadyUp.setTitlePlayers(true);
        }
        panelRoundReadyUp.setRound(String.valueOf(dataForUI.RoundNum));
    }

    /**
     * The following method is used to move the game screen relative to the
     * computer screen
     *
     * @param x : mouse X Coordinate relative to JFrame
     * @param y : mouse Y Coordinate relative to JFrame
     * @param mX : mouse X Coordinate relative to the actual screen of the
     * computer
     * @param mY : mouse Y Coordinate relative to the actual screen of the
     * computer
     */
    public void moveScreen(int x, int y, int mX, int mY) {
        this.setLocation(x - mX, y - mY);
        chatFrame.setWindow();
        if (!DataForUI.isConnectedToServer) {
            chatFrame.setVisible(false);

        } else {
            chatFrame.setVisible(true);

        }
        if (!DataForUI.isChatOpen) {

            chatFrame.setVisible(false);

        } else {
            chatFrame.setVisible(true);

        }
    }

    public void toggleChat() {
        if (DataForUI.isConnectedToServer) {
            if (DataForUI.isChatOpen) {
                chatFrame.setVisible(false);
                DataForUI.isChatOpen = false;
            } else {
                chatFrame.setVisible(true);
                DataForUI.isChatOpen = true;
            }
        }
    }

    // Multiplayer details.
    public Multiplayer multiplayer = new Multiplayer();
    public String channelName = "TestingChannel";
    public String username = "TestingUsername";
    public String serverQueueName;
    public String clientQueueName;
    public Thread backgroundClientQueueCheck;
    public ArrayList<String> otherPlayerNames = new ArrayList<String>();
    public WarpClient myGame;
    Game game = new Game();

    public void setupWrap() {
        new Thread() {
            public void run() {
                WarpClient.initialize(Utils.WRAP_API_KEY, Utils.WRAP_SECRET_KEY);
                try {
                    myGame = WarpClient.getInstance();
                    try {
                        myGame = WarpClient.getInstance();
                        myGame.addConnectionRequestListener(
                                new MyConnectionListener(clientQueueName) {
                                    public void onConnectDone(ConnectEvent event) {
                                        multiplayer.setMyGame(myGame);
                                        multiplayer.joinNewPlayer(username, channelName);
                                    }
                                });
                        myGame.addChatRequestListener(new MyChatListener());
                        myGame.addNotificationListener(
                                new MyNotifyListener() {
                                    @Override
                                    public void onPrivateChatReceived(String from, String message) {
                                        System.out.println(
                                                "onPrivateChatReceived from - " + from + " - M - " + message);
                                        if (from.contains(Utils.QUEUE_NAME_SEPERATOR)) {
                                            decodeClientMessage(message);
                                        }
                                    }
                                });
                        myGame.connectWithUserName(clientQueueName);
                        String message
                                = Utils.COMMAND_CODES.CLIENT_JOIN_GAME_CODE + " " + username;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(SelectMultiPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }

    public void selectMultiplayerStartServerClick() {
        serverQueueName = multiplayer.getServerQueue(channelName);
        clientQueueName = multiplayer.getClientQueue(channelName, username);
    }

    public void selectMultiplayerJoinServerClick() {
        serverQueueName = multiplayer.getServerQueue(channelName);
        clientQueueName = multiplayer.getClientQueue(channelName, username);
        setupWrap();
    }

    public void selectMulitplayerStartGameClick() {
        System.out.println("Sending message to start game.");
        myGame.sendPrivateChat(
                serverQueueName,
                Utils.COMMAND_CODES.CLIENT_GAME_START_CODE + " " + username + " startGame");
    }

    private void setupMultiplayerForGamePlay() {
        for (String name : otherPlayerNames) {
            game.addPlayer(name);
        }
        otherPlayerNames.remove(username);
        dataForUI.game = game;
        dataForUI.getPlayerList();
        controllerGamePlay.drawOpponenets();
    // Handles messages of letters that was received before
        // game start, which happens due to sync problems.
        if (tempIntialLetterHoling.size() != 0) {
            for (String name : otherPlayerNames) {
                String message = tempIntialLetterHoling.get(name);
                decodeClientMessage(message);
                tempIntialLetterHoling.remove(name);
            }
        }
    }

    private boolean gameStart = false;
    HashMap<String, String> tempIntialLetterHoling = new HashMap<String, String>();

    /**
     * Decode the message received by the client
     */
    private synchronized void decodeClientMessage(String message) {
        System.err.println("decodeClientMessage:GameServer m " + message);
        String[] segments = message.split(" ");
        if (segments.length < 2) {
            return;
        }

        String code = segments[0].trim();
        String content = segments[1];
        switch (Utils.COMMAND_CODES.getValue(code)) {
            case SERVER_GAME_START:
                if (segments.length != 3) {
                    System.err.println("Start game does not have a name list");
                    return;
                }
                otherPlayerNames = new ArrayList<String>();
                otherPlayerNames = Utils.getPlayerNamesFromString(segments[2]);
                setupMultiplayerForGamePlay();
                changeScreen(DataForUI.STR_ROUNDREADYUP, DataForUI.SelectMultiplayer);
                gameStart = true;
                break;
            case SERVER_USER_JOINED_ACK:
                panelSelectMultiPlayer.setClientStatus(message);
                break;
            case BROADCAST_JOIN_GAME_CODE:
        // Exameple 304 intialLetters pasindu 0 a e
                //          304 intialLetters <player name> <round number> <letter 1> <letter 2>
                if (segments.length < 6) {
                    System.err.println("The letters received incorrect format");
                    return;
                }
                String name = segments[2];
                Integer round = Integer.parseInt(segments[3]);
                String firstLetter = segments[4];
                String secondLetter = segments[5];
                if (dataForUI.game == null) {
                    System.err.println("game null");
                    tempIntialLetterHoling.put(name, message);
                }
                int userIndex = dataForUI.game.getIndexByPlayerName(name);
                String[] initLetters = {firstLetter, secondLetter};
                if (dataForUI.RoundNum != round) {
                    System.err.println("Round number does not match. ");
                }
                dataForUI.game.getPlayerRoundForRound(name, round).setIntialLetters(initLetters);
                int k = 0;
                break;
            case SERVER_ROUND_USER_SCORE:
                System.err.println("33333");
                handleRoundScore(segments);
                break;

            case SERVER_CHAT:
                //msg = 110 dilshanwn_::_Hi_every_one
                String[] chatSegments = segments[1].split("_::_");
                String chatMessage = chatSegments[1].trim().replaceAll("_", " ");
                System.out.println("chatmesage: " + chatMessage);
                chatFrame.updateMessages(chatSegments[0], chatMessage);

                break;

            case CLIENT_SUBMITTED_WORD: //***[dushan]
                System.out.println(segments[1] + " submitted answer");
                panelRoundReadyUp.updateUI();
                break;

            case SERVER_PENALIZE_WEKEST:
                if (segments.length == 3) {
                    chatFrame.updateMessages("Server", "@" + segments[1] + " reduced 5% from @" + segments[2]);
                    Player player = game.getPlayerfromName(segments[2]);
                    player.setTotalScore((int) (player.getTotalScore() - player.getTotalScore() * 0.05));
                }
                dataForUI.getPlayerList();
                dataForUI.preparePlayerArrayForUI();//***
                controllerRoundReadyUp.drawPlayers();
                startRoundUpTimerSystem();
                break;

            case PLAYER_JOINED_CHAT:
                System.out.println("player joinedchat");
                chatFrame.playerJoinedUpdateMessages(content);
                break;
        }
    }

    int noOfRoundScores = 0;

  // Example - 107 roundScore <player name> <round number>  <score> <totalScore>
    //           107 roundScore pasindu 1 10 100
    public void handleRoundScore(String[] segments) {
        String name = segments[2];
        Integer round = Integer.parseInt(segments[3]);
        Integer score = Integer.parseInt(segments[4]);
        Integer totalScore = Integer.parseInt(segments[5]);

        String letters = segments[6] + "," + segments[7];
        String word = "<D.N.F>"; //Did Not Finish/submit
        if (segments.length == 9) {
            word = segments[8];
        }
        DataForUI.getPlayerList();
        DataForUI.updatePdArray(name, totalScore, round, letters, word);

        for (int i = 0; i < dataForUI.PdArray.length; i++) {

            for (int k = 0; k < dataForUI.PdArray[i].WordArry.length; k++) {
                System.out.println(dataForUI.PdArray[i].getName() + " " + dataForUI.PdArray[i].WordArry[k]);
            }
        }

        System.err.println("1111111");
        dataForUI.game.getPlayerRoundForRound(name, round).setScore(score);
        dataForUI.game.getPlayerfromName(name).setTotalScore(totalScore);
        controllerRoundReadyUp.drawPlayers();
        // Controller up is waiting untils all the users scores are recived.
        ++noOfRoundScores;
        System.err.println("2222222");
        String msg = Utils.COMMAND_CODES.CLIENT_PENALIZE_WEKEST + " " + username;
        //  + 1 so that it counts that person it self.
        if (noOfRoundScores == (otherPlayerNames.size() + 1)) {

            ArrayList<Player> userMarks = game.processRoundScores(round);
            if (userMarks.size() == 0) {
                System.out.println("Error in user marks!!!!");
                return;
            } else if (userMarks.size() == 1) {
                multiplayer.publishToQueue(serverQueueName, msg);
                return;
            }
            String weakestPlayerName = userMarks.get(0).getName();
            if (userMarks.get(0).getName().equals(username)) {
 

                int res
                        = JOptionPane.showConfirmDialog(
                                null, "Do you want to reduce 5% marks from the weakest player?", " Punishing Weakest Player", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    msg += " " + weakestPlayerName;
                }
                multiplayer.publishToQueue(serverQueueName, msg);

            }
            noOfRoundScores = 0;

        } else {


        }
    }

    public void pushtoServerQueue(String message) {
        multiplayer.publishToQueue(serverQueueName, message);
    }
}
