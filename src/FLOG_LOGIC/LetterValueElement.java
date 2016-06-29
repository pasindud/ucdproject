package FLOG_LOGIC;

import java.util.HashMap;
import java.util.Map;

/**
 * UCD ID - 14208891
 * @author Pasindu
 * Used the calculate the letter points.
 */
class LetterValueElement {
    ConstantElement letterMultiValue = new ConstantElement(100);
    /** Map of letter values. */
    private Map<String, Integer> letterValues = new HashMap<String, Integer>();
    /** Array of vowels. */
    public final static String[][] VOWELS = {
        {"a", "1"}, {"e", "1"}, {"i", "1"}, {"o", "1"}, {"u", "1"}
    };
    /** Array of consonants. */
    public final static String[][] CONSONANTS = {
        {"b", "3"}, {"c", "3"}, {"d", "2"},
        {"f", "4"}, {"g", "2"},  {"h", "4"},
        {"j", "8"}, {"k", "5"}, {"l", "1"},
        {"m", "3"}, {"n", "1"}, {"p", "3"},
        {"q", "10"}, {"r", "1"}, {"s", "1"},
        {"t", "1"}, {"v", "4"}, {"w", "4"},
        {"x", "8"}, {"y", "4"}, {"z", "10"}
    };
    
    public final static int defaultValue = 1;
    
    LetterValueElement() {
        try {
            // Load vowel values.
            for (int i = 0; i < VOWELS.length; i++) {
                letterValues.put(VOWELS[i][0], Integer.parseInt(VOWELS[i][1]));
            }
            // Load Consonants values.
            for (int i = 0; i < CONSONANTS.length; i++) {
                int points = Integer.parseInt(CONSONANTS[i][1]);
                letterValues.put(CONSONANTS[i][0], points);
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error - " + e.getMessage());
        }
    }
    
    public int calculateWordValue(WordElement word){
        int totalPoints = 0;
        // Letter value is multipled by 10.
        for (int i = 0; i < word.getWordLength(); i++) {
            totalPoints += getLettersValue(word.getLetter(i)) 
                    * letterMultiValue.getValue();
        }
        return totalPoints;
    }
    
    public void setLetterValue(String letter, int value){
        letterValues.put(letter, value);
    }
    
    public int getLettersValue(String letter){
        return letterValues.get(letter);
    }
}