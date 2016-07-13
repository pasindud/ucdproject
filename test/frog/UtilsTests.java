package frog;

import FLOG_LOGIC.Utils;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests Utils Class.
 */
public class UtilsTests {
    
    /** Tests testGetRandomLetters. */
    @Test
    public void testGetRandomLetters(){
        int noOfConsonants = 5;
        int noOfVowels = 5;
        List<String> listOfLetters = Utils.getRadomLetters(
                noOfVowels, noOfConsonants);
        Assert.assertEquals(listOfLetters.isEmpty(), false);
        Assert.assertEquals(10, listOfLetters.size());
        
        int consonantsOccurrences = 0;
        int vowelOccurrences = 0;
        for (int i = 0; i < 10; i++) {
            if (Utils.CONSONANTS_LIST.contains(listOfLetters.get(i))){
                consonantsOccurrences +=1;
            } else {
                vowelOccurrences +=1;
            }
        }
        Assert.assertEquals(noOfVowels, vowelOccurrences);
        Assert.assertEquals(noOfConsonants, consonantsOccurrences);        
    } 
}
