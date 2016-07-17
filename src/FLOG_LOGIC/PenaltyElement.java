package FLOG_LOGIC;

/**
 * UCD ID - 14208891
 * @author Pasindu
 * Used to give penalties to the player.
 */
class PenaltyElement {
    private int numberOfIntialLettersUsed = 0;
    private int numberOfOtherLettersUsed = 0;
    private ConstantElement maxNumberOfIntialLetters = new ConstantElement(2);
    private ConstantElement maxNumberOfOtherLetters = new ConstantElement(10);
    /** Penalty points for not using other letters. */
    private ConstantElement penalyPointsForAIntialLetter 
                                                    = new ConstantElement(10);
    /** Penalty points for not using other letters. */
    private ConstantElement penalyPointsForAOtherLetter 
                                                    = new ConstantElement(50);
    /** Penalty points for the player. */
    private int penaltyPoints = 0;
    
    /** Gets the penalty points for player's round. */
    public int getPenaltyPoints(){
        return penaltyPoints;
    }

    public void setNumberOfIntialLettersUsed(int numberOfIntialLettersUsed) {
        if (numberOfIntialLettersUsed >=0 && 
            numberOfIntialLettersUsed <=  maxNumberOfIntialLetters.getValue()) {
            this.numberOfIntialLettersUsed = numberOfIntialLettersUsed;
        }
    }

    public void setNumberOfOtherLettersUsed(int numberOfOtherLettersUsed) {
        if(numberOfOtherLettersUsed >=0 && 
                numberOfOtherLettersUsed <= maxNumberOfOtherLetters.getValue()){
            this.numberOfOtherLettersUsed = numberOfOtherLettersUsed;
        }
    }
        
    public void calculatePenaltyPoints(){
        // Every unsed intial letter gets 100 penalty points
        penaltyPoints = 0;
        penaltyPoints += (maxNumberOfIntialLetters.getValue() - 
                numberOfIntialLettersUsed) * 
                penalyPointsForAIntialLetter.getValue();
        // Every unsed other letter gets 50 penalty points
        penaltyPoints += (maxNumberOfOtherLetters.getValue() - 
                numberOfOtherLettersUsed) * 
                penalyPointsForAOtherLetter.getValue();
        
    }
    
   public static int getWordAutoSearchPenalty(int score){
       return score / 2;
   }  
    
}