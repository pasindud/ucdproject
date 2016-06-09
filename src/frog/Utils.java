package frog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Contains common functions.
 */
public class Utils {
    
    /** Array of vowels. */
    public final static String[] VOWELS = {"a", "e", "i", "o", "u"};
    /** Array of consonants. */
    public final static String[] CONSONANTS = 
        {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", 
            "r", "s", "t", "v", "w", "x", "y", "z"};
    /** List of consonants. */
    public final static List<String> CONSONANTS_LIST = 
            new ArrayList<>(Arrays.asList(CONSONANTS));
    /** List of vowels. */
    public final static List<String> VOWELS_LIST = 
            new ArrayList<>(Arrays.asList(VOWELS));
    /** Number of vowels. */
    public final static int NO_OF_VOWELS = VOWELS_LIST.size() - 1;
    /** Number of consonants. */
    public final static int NO_OF_CONSONANTS = CONSONANTS_LIST.size() - 1;
    
    /**
     * Generates list of random letters within given sizes.
     * @param noOfVowels is the number of vowels the list should have.
     * @param noOfConsonants is the number of consonants the list should have.
     * @return list of random letters.
     */
    public static List<String> getRadomLetters(int noOfVowels, 
            int noOfConsonants){
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
}
