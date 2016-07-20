package FLOG_LOGIC;

/**
 * UCD ID - 14208891
 *
 * @author Pasindu
 */
// java -cp  /Users/Pasindu/Desktop/sep/ucdproject/dist/Frog.jar  FLOG_LOGIC.FLOG_LOGIC ChannelName4412
/// java  -cp  /Users/Pasindu/Desktop/sep/ucdproject/dist/Frog.jar FLOG_LOGIC.FLOG_LOGIC asd
public class FLOG_LOGIC {

    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        String channelName = args[0];
        startServerApp(channelName);
    }

    public static void startServerApp(String channelName) {
        Multiplayer multiplayer = new Multiplayer();
        multiplayer.createServer(channelName);
        Server server = new Server(channelName);
        server.start();
    }
    /**
     * This main method was for sep assignment2.
     */
   
}
