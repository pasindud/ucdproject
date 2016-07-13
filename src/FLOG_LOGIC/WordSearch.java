/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Pasindu
 */
public class WordSearch {
    /** Filename of the dictionary. */
    private static final String WORD_FILE_NAME = "word.txt";
    /** Max time to spend searching for a word. */
    private static final long MAX_TIME_FOR_WORD_SEARCH = 5000;
    /** Array of letters. */
    private String[] letters;
    private String pattern = "";
    
    WordSearch(String[] letters){
        this.letters = letters;
    }
     
    WordSearch(String letters){
        this.letters = letters.split("");
    }
    
    protected void setPattern(){
        // Build the pattern only for one letter.
        StringBuilder patternLetter = new StringBuilder();
        patternLetter.append("([");
        for (int i = 0; i < letters.length; i++) {
            patternLetter.append(letters[i]);
        }
        patternLetter.append("])");
        patternLetter.append("{0,1}");
        
        // Build the pattern for whole word.
        StringBuilder fullPattern = new StringBuilder();
        fullPattern.append("^");
        for (int i = 0; i < letters.length; i++) {
            fullPattern.append(patternLetter.toString());
        }
        fullPattern.append("$");
        this.pattern = fullPattern.toString();
    }
    
    protected String search() {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = null;
        String word = "";
        long startTime = System.currentTimeMillis();
        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(WORD_FILE_NAME));
            String line = null;

            while ((line = br.readLine()) != null) {
                matcher = regex.matcher(line);
                if(matcher.find()) {
                    word = line;
                }
                
                if ((System.currentTimeMillis() - startTime) > 
                        MAX_TIME_FOR_WORD_SEARCH ) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(" Error while automatically "
                    + "searching for a word " + e.getMessage());
        }
        return word;
    }
}
