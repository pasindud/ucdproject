
package FLOG_LOGIC;

import com.shephertz.app42.paas.sdk.java.App42API;
import com.shephertz.app42.paas.sdk.java.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * UCD ID - 14208891
 *
 * @author Pasindu
 */
// java -cp  /Users/Pasindu/Desktop/sep/ucdproject/dist/Frog.jar  FLOG_LOGIC.FLOG_LOGIC ChannelName4412
/// java  -cp  /Users/Pasindu/Desktop/sep/ucdproject/dist/Frog.jar FLOG_LOGIC.FLOG_LOGIC asd
public class FLOG_LOGIC {
    public static void main(String[] args){
        if (args.length == 0) {
            return;
        }
        String channelName = args[0];
        startServerApp(channelName);
    }
    
    public static void startServerApp(String channelName){
        Multiplayer multiplayer = new Multiplayer();
        multiplayer.createServer(channelName);
        Server server = new Server(channelName);
        server.start();
    }
    /**
     * This main method was for sep assignment2.
     */
    public static void Main2(String[] args) {
        System.out.println("Running the a flog game round");

        // Setup users.
        Game game = new Game();
        game.addPlayer("Pasindu");
        game.addPlayer("Rashmika");
        game.addPlayer("Navka");
        game.addPlayer("Shaf");
                               
        // Run a new round
        game.startNewRound();
        
        int currentRound = game.getCurrentRound();

        // Simulate searching word for all other users.
        for (int i = 1; i < 4; i++) {
            game.runWordSearch(currentRound, i);
        }

        // Calculate letter values.
        game.calculateRoundScores(currentRound);

        System.out.println("Player 0 round score - " + 
                game.getPlayer(0).getPlayerRound(currentRound).getScore());
        System.out.println("Player 1 round score - " + 
                game.getPlayer(1).getPlayerRound(currentRound).getScore());
        System.out.println("Player 2 round score - " + 
                game.getPlayer(2).getPlayerRound(currentRound).getScore());
        System.out.println("Player 3 round score - " + 
                game.getPlayer(3).getPlayerRound(currentRound).getScore());
        
        // Process the round scores.
        game.processRoundScores(0);
        System.out.println("Is player 1 kicked - " + game.getPlayer(1).getIsKicked());

    }

    /* 
        // Setup the words for testing in round 0.  
        game.setPlayerRoundWord(0, 0, "wind");
        game.setPlayerRoundWord(0, 1, "new");
        game.setPlayerRoundWord(0, 2, "elements");
        game.setPlayerRoundWord(0, 3, "classes");
    
        System.out.println("Player 0 - word - wind letter score " + 
                game.getPlayer(0).getPlayerRound(0).getTotalLetterValues());
        System.out.println("Player 1 - new - letter score " + 
                game.getPlayer(1).getPlayerRound(0).getTotalLetterValues());
        System.out.println("Player 2 - elements -letter score " + 
                game.getPlayer(2).getPlayerRound(0).getTotalLetterValues());
        System.out.println("Player 3 - classes - letter score " + 
                game.getPlayer(3).getPlayerRound(0).getTotalLetterValues());
        
        // Calculate penalties.
        game.getPlayer(0).getPlayerRound(0).penaltyPoints.setNumberOfIntialLettersUsed(1);
        game.getPlayer(0).getPlayerRound(0).penaltyPoints.setNumberOfOtherLettersUsed(3);
        game.getPlayer(0).getPlayerRound(0).penaltyPoints.calculatePenaltyPoints();
        
        game.getPlayer(1).getPlayerRound(0).penaltyPoints.setNumberOfIntialLettersUsed(1);
        game.getPlayer(1).getPlayerRound(0).penaltyPoints.setNumberOfOtherLettersUsed(2);
        game.getPlayer(1).getPlayerRound(0).penaltyPoints.calculatePenaltyPoints();
        
        game.getPlayer(2).getPlayerRound(0).penaltyPoints.setNumberOfIntialLettersUsed(1);
        game.getPlayer(2).getPlayerRound(0).penaltyPoints.setNumberOfOtherLettersUsed(7);
        game.getPlayer(2).getPlayerRound(0).penaltyPoints.calculatePenaltyPoints();
        
        game.getPlayer(3).getPlayerRound(0).penaltyPoints.setNumberOfIntialLettersUsed(1);
        game.getPlayer(3).getPlayerRound(0).penaltyPoints.setNumberOfOtherLettersUsed(6);
        game.getPlayer(3).getPlayerRound(0).penaltyPoints.calculatePenaltyPoints();
     */
}
