package FLOG_LOGIC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Contains common functions.
 */
public class Utils {

    /**
     * Array of vowels.
     */
    public final static String[] VOWELS = {"A", "E", "I", "O", "U"};
    /**
     * Array of consonants.
     */
    public final static String[] CONSONANTS = {
        "B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X",
        "Y", "Z"
    };
    /**
     * List of consonants.
     */
    public final static List<String> CONSONANTS_LIST = new ArrayList<>(Arrays.asList(CONSONANTS));
    /**
     * List of vowels.
     */
    public final static List<String> VOWELS_LIST = new ArrayList<>(Arrays.asList(VOWELS));
    /**
     * Number of vowels.
     */
    public final static int NO_OF_VOWELS = VOWELS_LIST.size() - 1;
    /**
     * Number of consonants.
     */
    public final static int NO_OF_CONSONANTS = CONSONANTS_LIST.size() - 1;
    /**
     * Used to separator channel name and username in the queue name.
     */
    public final static String QUEUE_NAME_SEPERATOR = "OOOOOO";

    /**
     * Codes used in messages to identify the action.
     */
    public static enum COMMAND_CODES {
        CLIENT_GAME_START_CODE("108"),
        CLIENT_JOIN_GAME_CODE("100"),
        BROADCAST_JOIN_GAME_CODE("304"),
        CLIENT_END_ROUND_CODE("106"),
        SERVER_GAME_START("201"),
        SERVER_USER_JOINED_ACK("200"),
        SERVER_ROUND_USER_SCORE("204"),
        SERVER_CHAT("110"),
        CLIENT_SUBMITTED_WORD("210"),
        PLAYER_JOINED_CHAT("111"),
        CLIENT_PENALIZE_WEKEST("115"),
        SERVER_PENALIZE_WEKEST("215");//***[dushan]
        private final String code;

        /**
         * Get code for the enum.
         */
        private COMMAND_CODES(String code) {
            this.code = code;
        }

        public String getText() {
            return code;
        }

        @Override
        public String toString() {
            return code;
        }

        public static COMMAND_CODES getValue(String value) {
            for (COMMAND_CODES e : COMMAND_CODES.values()) {
                if (e.code.equals(value)) {
                    return e;
                }
            }
            return null;
        }
    }

    /**
     * Generates list of random letters within given sizes.
     *
     * @param noOfVowels is the number of vowels the list should have.
     * @param noOfConsonants is the number of consonants the list should have.
     * @return list of random letters.
     */
    public static List<String> getRadomLetters(int noOfVowels, int noOfConsonants) {
        Random rand = new Random();
        List<String> listOfLetters = new ArrayList<>();
        int letterIndex = 0;

        // Generate vowels.
        for (int i = 0; i < noOfVowels; i++) {
            letterIndex = rand.nextInt(NO_OF_VOWELS);
            String letter = VOWELS_LIST.get(letterIndex);
            listOfLetters.add(letter);
        }

        letterIndex = 0;
        // Generate consonants.
        for (int i = 0; i < noOfConsonants; i++) {
            letterIndex = rand.nextInt(NO_OF_CONSONANTS);
            String letter = CONSONANTS_LIST.get(letterIndex);
            listOfLetters.add(letter);
        }
        return listOfLetters;
    }

    public static String getRandomVowel() {
        Random rand = new Random();
        int letterIndex = rand.nextInt(NO_OF_VOWELS);
        return VOWELS_LIST.get(letterIndex);
    }

    public static String getRandomConsonant() {
        Random rand = new Random();
        int letterIndex = rand.nextInt(NO_OF_CONSONANTS);
        return CONSONANTS_LIST.get(letterIndex);
    }

    public static String SwapLetter(String letter) {
        String result = "";
        if (Arrays.asList(VOWELS).contains(letter)) {
            result = getRandomVowel();
            if (result.equalsIgnoreCase(letter)) {
                SwapLetter(letter);
            }
        } else {
            result = getRandomConsonant();
            if (result.equalsIgnoreCase(letter)) {
                SwapLetter(letter);
            }
        }
        return result;
    }

    public static ArrayList<String> getPlayerNamesFromString(String strPlayerNames) {
        return new ArrayList(Arrays.asList(strPlayerNames.split(",")));
    }

    public static String makeRoundEndServerMessage(
            String playerName,
            int roundNum,
            boolean isAutoGenUsed,
            String word,
            String timeRemanString,
            String[] initialLetters,
            String[] otherLetters) {

        StringBuilder clientMessage = new StringBuilder();
        clientMessage.append(Utils.COMMAND_CODES.CLIENT_END_ROUND_CODE + " ")
                .append("endRound").append(" ")
                .append(playerName).append(" ")
                .append(roundNum).append(" ");

        String strInitialLetters = String.join(",", initialLetters);
        String strOtherLetters = String.join(",", otherLetters);

        clientMessage.append(strInitialLetters).append(" ")
                .append(strOtherLetters).append(" ")
                .append(timeRemanString).append(" ")
                .append(isAutoGenUsed).append(" ")
                .append(word);
        return clientMessage.toString();
    }

    /* shephertz.com SECRET_KEY key. */
    public static final String WRAP_SECRET_KEY
            = "6b6e863ce1566e74df345189e31431f8970facb48d7c39df783c5d7597ce6786";
    public static final String WRAP_API_KEY
            = "6ad3d2683a40fb1ee0daaa7945f39fa70c715b7906ad819e206181060408b4f0";
}
