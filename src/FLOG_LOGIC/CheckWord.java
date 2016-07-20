
package FLOG_LOGIC;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author DILSHAN FERNANDO
 */
public class CheckWord {

    /**
     * Filename of the dictionary.
     */
    private static final String WORD_FILE_NAME = "words.txt";

    public static boolean checkWordValidity(String word) {
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.equalsIgnoreCase(word)) {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }
}
