
package FLOG_GUI;

/**
 *
 * @author Dushan Galappaththi
 */
public class PlayerData {
  int position;
  String name;
  int score;
  String letterOne;
  String letterTwo;
  String letterArry[] = new String[6];
  String WordArry[] = new String[6];

  public PlayerData(int position, String name, int score, String letterOne, String letterTwo) {
    this.position = position;
    this.name = name;
    this.score = score;
    this.letterOne = letterOne;
    this.letterTwo = letterTwo;
  }

  public int getPosition() {
    return position;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public String getLetterOne() {
    return letterOne;
  }

  public String getLetterTwo() {
    return letterTwo;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setLetterOne(String letterOne) {
    this.letterOne = letterOne;
  }

  public void setLetterTwo(String letterTwo) {
    this.letterTwo = letterTwo;
  }

    public String getWordArry(int roundNum) 
    {
        return WordArry[roundNum];
    }

    public void setWordArry(String Word, int roundNum) {
        this.WordArry[roundNum] = Word;
    }

    public String getLetterArry(int roundNum) 
    {
        return letterArry[roundNum];
    }

    public void setLetterArry(String letters, int roundNum) {
        this.letterArry[roundNum] = letters;
    }
    
    
  
    
  
}
