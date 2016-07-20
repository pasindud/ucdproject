/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Pasindu Contains all the information about of the round for the given
 * player.
 */
public class PlayerRound {

    /**
     * Initial set of letters.
     */
    private String intialLetters[] = new String[2];
    /**
     * Other set of letters.
     */
    private String otherLetters[] = new String[10];
    /**
     * Whether auto word search was used.
     */
    private boolean isWordSearchUsed = false;
    /**
     * Score refers to the total score.
     */
    private int score = 0;
    /**
     * Total values for the letters in the word.
     */
    private int totalLetterValues = 0;
    /**
     * The word the user inputed.
     */
    private WordElement word;
    /**
     * Used to calculate the penalty points.
     */
    // TODO(pasindu) This is temporyly public for testing purposes.
    public PenaltyElement penaltyPoints = new PenaltyElement();
    /**
     * Points given for length.
     */
    private ConstantElement additionLenghtPoints = new ConstantElement(2);
    /**
     * BonusPoints given for 12 length words.
     */
    private ConstantElement AllLettersUsedBonus = new ConstantElement(1000);

    public int getTotalLetterValues() {
        return totalLetterValues;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public WordElement getWord() {
        return word;
    }

    public void setWord(WordElement word) {
        this.word = word;
    }

    public String[] getIntialLetters() {
        return intialLetters;
    }

    public void getWordSearch() {
        String[] allLetters = (String[]) ArrayUtils.addAll(intialLetters, otherLetters);
        WordSearch wordSearch = new WordSearch(allLetters);

        WordElement wordElement = new WordElement(wordSearch.search());
        this.word = wordElement;
        isWordSearchUsed = true;
    }

    public void setIntialLetters(String[] intialLetters) {
        this.intialLetters = intialLetters;
    }

    public String[] getOtherLetters() {
        return otherLetters;
    }

    public void setOtherLetters(String[] otherLetters) {
        this.otherLetters = otherLetters;
    }

    public boolean isIsWordSearchUsed() {
        return isWordSearchUsed;
    }

    public void setIsWordSearchUsed(boolean isWordSearchUsed) {
        this.isWordSearchUsed = isWordSearchUsed;
    }

    public void calculateWordLetterScores() {
        totalLetterValues = 0;
        LetterValueElement letterValues = new LetterValueElement();
        totalLetterValues = letterValues.calculateWordValue(word);
    }

    public void calculateScore() {
        score = 0;
        if (word != null) {
            if (CheckWord.checkWordValidity(word.getWord())) {
                calculateWordLetterScores();
                String[] allLetters = (String[]) ArrayUtils.addAll(intialLetters, otherLetters);
                penaltyPoints = new PenaltyElement();

                int numberOfInitialLetters = 0;
                int numberOfOtherLetters = 0;
                String[] letters = word.getLetters();

                for (int i = 0; i < letters.length; i++) {
                    if (numberOfInitialLetters == 2) {
                        break;
                    }
                    for (int j = 0; j < intialLetters.length; j++) {
                        if (intialLetters[j].equals(letters[i])) {
                            letters[i] = "";
                            numberOfInitialLetters++;
                        }
                    }
                }

                for (int i = 0; i < letters.length; i++) {
                    if (numberOfOtherLetters == 10) {
                        break;
                    }
                    for (int j = 0; j < intialLetters.length; j++) {
                        if (intialLetters[j].equals(letters[i])) {
                            letters[i] = "";
                            numberOfOtherLetters++;
                        }
                    }
                }

                penaltyPoints.setNumberOfIntialLettersUsed(numberOfInitialLetters);
                penaltyPoints.setNumberOfOtherLettersUsed(numberOfOtherLetters);

                
                penaltyPoints.calculatePenaltyPoints();
                score = totalLetterValues - penaltyPoints.getPenaltyPoints();
                score += Math.pow(word.getWordLength(), additionLenghtPoints.getValue());
                if (word.getWordLength() == 12) {
                    score += AllLettersUsedBonus.getValue();
                }
                if (isWordSearchUsed) {
                    score = PenaltyElement.getWordAutoSearchPenalty(score);
                }

                System.out.println("score letter value: " + totalLetterValues);
                System.out.println("score penalty values :" + penaltyPoints.getPenaltyPoints());
            }
        }
    }
}
