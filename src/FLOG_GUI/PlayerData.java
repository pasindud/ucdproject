/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

/**
 *
 * @author Dushan Galappaththi
 */
public class PlayerData 
{
    int position;
    String name;
    int score;
    String letterOne;
    String letterTwo;

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
    
     
}
