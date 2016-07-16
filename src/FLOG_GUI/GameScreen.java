package FLOG_GUI;

import FLOG_LOGIC.Game;
import static FLOG_GUI.DataForUI.SelectMultiplayer;
import FLOG_LOGIC.Game;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import FLOG_LOGIC.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameScreen myFrame = new GameScreen();
            }
        });
    }

    //Constructor 
    public GameScreen() {
        createAndShowGUI();
        // Tempory.
        /*SelectMultiPlayer selectMultiPlayer2 = new SelectMultiPlayer();
        JFrame ui2=new JFrame("ui2");
        ui2.setSize(new Dimension(900, 619)); 
        ui2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui2.add(selectMultiPlayer2);
        ui2.setVisible(true);*/
        
        /*SelectMultiPlayer selectMultiPlayer1 = new SelectMultiPlayer(this);
        JFrame ui1 =new JFrame("ui1");
        ui1.setSize(new Dimension(900, 619)); 
        ui1.setLocation(300, 300);
        ui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui1.add(selectMultiPlayer1);
        ui1.setVisible(true);*/
    }

    //JFrame setting up
    private void createAndShowGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        preparePanels();
        showMainMenu();
        this.setSize(new Dimension(900, 619)); 
//        this.setUndecorated(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.validate();
        this.setVisible(true);
    }

    private void preparePanels() {
        panelMainMenu = new PanelMainMenu();
        panelPlaying = new PanelGamePlay();
        panelSettings = new PanelSettings();
        panelRoundReadyUp = new PanelRoundReadyUp();
        panelWinners = new PanelWinners();
        panelSelectMultiPlayer = new SelectMultiPlayer(this);
        panelLogin = new PanelLogin();
        panelRegister = new PanelRegister();
        
        
        //Adding Panels to Card Layout
        container = new JPanel();
        
        container.setLayout(mainPanelCards);
        container.add(panelMainMenu, dataForUI.STR_MAINMENU);
        container.add(panelPlaying,dataForUI.STR_GAMEPLAY);
        container.add(panelSettings,dataForUI.STR_SETTINGS);
        container.add(panelRoundReadyUp,dataForUI.STR_ROUNDREADYUP);
        container.add(panelWinners,dataForUI.STR_WINNER);
        container.add(panelSelectMultiPlayer,dataForUI.SelectMultiplayer);
        container.add(panelLogin,dataForUI.STR_LOGIN);
        container.add(panelRegister,dataForUI.STR_REGISTER);
        this.getContentPane().add(container,BorderLayout.CENTER);
        
        //TestGUI_Inputs testing = new TestGUI_Inputs();
//        initateGame();
        controllerGamePlay = new ControllerGamePlay(panelPlaying,this);
        controllerRoundReadyUp = new ControllerRoundReadyUp(panelRoundReadyUp,this, controllerGamePlay);
        controllerMainMenu = new ControllerMainMenu(panelMainMenu, this, controllerRoundReadyUp);
        controllerSettings = new ControllerSettings(panelSettings, this);
        controllerWinners = new ControllerWinners(panelWinners, this, controllerGamePlay);
        controllerLogin = new ControllerLogin(panelLogin, this);
        controllerRegister = new ControllerRegister(panelRegister, this);
    }

    private void showMainMenu() {
        //System.out.println("showmainmenu");
        //changeScreen(dataForUI.STR_MAINMENU, null);
        changeScreen(dataForUI.STR_LOGIN, dataForUI.STR_LOGIN);
    }
/*
    private void initateGame() {
        Game game = new Game();
        game.addPlayer("Pasindu");
        game.addPlayer("Dushan");

        DataForUI.game = game;
        DataForUI.getPlayerList();
    }
*/
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
                    cl.show(container, screenName);
                    break;
                case DataForUI.STR_MAINMENU:
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
                    controllerWinners.setWinner(2);
                    //controllerWinners.setWinner(dataForUI.player.getListIndex());
                    break;
                    
                case DataForUI.STR_GAMEPLAY:
                    DataForUI.getPlayerList();
                    controllerGamePlay.drawOpponenets();
                     cl.show(container, screenName);
                     break;
                     
                case DataForUI.STR_LOGIN:
                    cl.show(container, screenName);
                    
                case DataForUI.STR_REGISTER:
                    cl.show(container, screenName);
                    
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
    public void startRoundUpTimerSystem(){
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

    }
    
    // Multiplayer details.
    public Multiplayer multiplayer = new Multiplayer();
    public Catcher clientCatcher = new Catcher();
    public Thrower clientThrower = new Thrower();
    public String channelName = "TestingChannel";
    public String username = "TestingUsername";
    public String serverQueueName;
    public String clientQueueName;
    public Thread backgroundClientQueueCheck;
    public ArrayList<String> otherPlayerNames = new ArrayList<String>();
    
    Game game = new Game();
    public void setupMultiplayerForGamePlay(){
        serverQueueName = multiplayer.getServerQueue(channelName);
        clientQueueName = multiplayer.getClientQueue(channelName, username);
        
        for (String name : otherPlayerNames) {
            game.addPlayer(name);
        }
        otherPlayerNames.remove(username);
//        game.addPlayer("Json");
//        game.addPlayer("Mark");
        dataForUI.game = game;
        dataForUI.getPlayerList();
        controllerGamePlay.drawOpponenets();
        clientThrower.addThrowListener(clientCatcher);
        startQueueSystem();
    }
    
    /**
     * Listens to messages thrown by checkQueueThread.
     */
    public class Catcher implements ThrowListener {
        @Override
        public void Catch(String message) {
            System.err.println("Caught:GameServer" + message);
            decodeClientMessage(message);
        }
    }
    
    /**
     * Decode the message received by the client
     */
    public synchronized void decodeClientMessage(String message) {
        System.err.println("decodeClientMessage:GameServer m " + message);
        // setClientStatus("Message in client - " + message);
        String[] segments = message.split(" ");
        if (segments.length < 2) {
            return;
        }
        String code = segments[0];
        String content = segments[1];
        switch (code) {
            case "200":
                // User joined the correctly.
                break;
            case "304":
                
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
                int userIndex = dataForUI.game.getIndexByPlayerName(name);
                String[] initLetters = {firstLetter, secondLetter};
                if (dataForUI.RoundNum != round) {
                    System.err.println("Round number does not match. ");
                }

                System.err.println("Intial Letters " + initLetters + " from " + name);
                dataForUI.game.getPlayerRoundForRound(name, round).setIntialLetters(initLetters);
                int k =0;
                break;
            case "204":
                handleRoundScore(segments);
                break;
        }
    }
    int noOfRoundScores = 0;
    // Example - 107 roundScore <player name> <round number> <score>
    //           107 roundScore pasindu 1 score
    public void handleRoundScore(String[] segments){
        String name = segments[2];
        Integer round = Integer.parseInt(segments[3]);
        Integer score = Integer.parseInt(segments[4]);
        Integer totalScore = Integer.parseInt(segments[5]);
        
        dataForUI.game.getPlayerRoundForRound(name, round).setScore(score);
        dataForUI.game.getPlayerfromName(name).setTotalScore(totalScore);
        controllerRoundReadyUp.drawPlayers();
        // Controller up is waiting untils all the users scores are recived.
        ++noOfRoundScores;
        //  + 1 so that it counts that person it self.
        if (noOfRoundScores == (otherPlayerNames.size() + 1)) {
            startRoundUpTimerSystem();
            noOfRoundScores = 0;
        }
    }
    
    public void pushtoServerQueue(String message){
        multiplayer.publishToQueue(serverQueueName, message);
    }
    
    public void startQueueSystem(){
        System.err.println("StartQ:GameServer");
        String clientQueueName = multiplayer.getClientQueue(channelName, username);
        backgroundClientQueueCheck = new CheckQueueThread(clientQueueName, clientThrower);
        backgroundClientQueueCheck.start();
    }
    
    
}
