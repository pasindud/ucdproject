package FLOG_LOGIC;

import java.util.ArrayList;
import java.util.List;

/**
 * UCD ID - 14208891
 * @author Pasindu
 * Used to calculate the word points.
 */
public class WordElement extends FlogElement {
    /** Letter of the words. */    
    private String letters[] = new String[0];
    /** The word. */
    private String word;

    public WordElement(String word){
       this.word = word;
        if (!word.isEmpty()) {
            this.letters = word.split("");
        }
    }
    
    public String getWord(){
        return word;
    }
    
    public int getWordLength(){
        return letters.length;
    }
    
    public String[] getLetters(){
        return letters;
    }
    
    public String getLetter(int index){
        return letters[index];
    }
}


