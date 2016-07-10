/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

/**
 *
 * @author DILSHAN FERNANDO
 */
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.apache.commons.lang3.StringUtils;

public class WordAutoGenerator{

    String c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12;
    private String longestWord = "";
    String z = "";
    String pattern;//= "^[a-zA-Z_]*$";
    private static final long MAX_TIME_FOR_WORD_SEARCH = 5000;

    public WordAutoGenerator(String c1, String c2, String c3, String c4, String c5, String c6, String c7, String c8, String c9, String c10, String c11, String c12) {
        this.c1 = c1.toUpperCase();
        this.c2 = c2.toUpperCase();
        this.c3 = c3.toUpperCase();
        this.c4 = c4.toUpperCase();
        this.c5 = c5.toUpperCase();
        this.c6 = c6.toUpperCase();
        this.c7 = c7.toUpperCase();
        this.c8 = c8.toUpperCase();
        this.c9 = c9.toUpperCase();
        this.c10 = c10.toUpperCase();
        this.c11 = c11.toUpperCase();
        this.c12 = c12.toUpperCase();
        z = this.c1 + this.c2 + this.c3 + this.c4 + this.c5 + this.c6 + this.c7 + this.c8 + this.c9 + this.c10 + this.c11 + this.c12;
        Autogenerator();
    }

    String patternGen() {
        String x = "([" + c1 + c2 + c3 + c4 + c5 + c6 + c7 + c8 + c9 + c10 + c11 + c12 + "]){0,1}";

        x = "^" + x + x + x + x + x + x + x + x + x + x + x + x + "$";
        //System.out.println("x: " + x);
        return x;
    }

    void Autogenerator() {
        pattern = patternGen();
        Pattern r = Pattern.compile(pattern);
        Matcher m = null;
        long startTime = System.currentTimeMillis();
        int c1_count = StringUtils.countMatches(z, this.c1);
        int c2_count = StringUtils.countMatches(z, this.c2);
        int c3_count = StringUtils.countMatches(z, this.c3);
        int c4_count = StringUtils.countMatches(z, this.c4);
        int c5_count = StringUtils.countMatches(z, this.c5);
        int c6_count = StringUtils.countMatches(z, this.c6);
        int c7_count = StringUtils.countMatches(z, this.c7);
        int c8_count = StringUtils.countMatches(z, this.c8);
        int c9_count = StringUtils.countMatches(z, this.c9);
        int c10_count = StringUtils.countMatches(z, this.c10);
        int c11_count = StringUtils.countMatches(z, this.c11);
        int c12_count = StringUtils.countMatches(z, this.c12);

        int i = 1;

        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line = null;

            while ((line = br.readLine().toUpperCase()) != null) {
                m = r.matcher(line);
                if (m.find()) {
//                    System.out.println(i + " - " + line);
                    i = i + 1;
                    if (this.longestWord.equals("")) {
                        if ((StringUtils.countMatches(line, this.c1) <= c1_count)
                                && (StringUtils.countMatches(line, this.c2) <= c2_count)
                                && (StringUtils.countMatches(line, this.c3) <= c3_count)
                                && (StringUtils.countMatches(line, this.c4) <= c4_count)
                                && (StringUtils.countMatches(line, this.c5) <= c5_count)
                                && (StringUtils.countMatches(line, this.c6) <= c6_count)
                                && (StringUtils.countMatches(line, this.c7) <= c7_count)
                                && (StringUtils.countMatches(line, this.c8) <= c8_count)
                                && (StringUtils.countMatches(line, this.c9) <= c9_count)
                                && (StringUtils.countMatches(line, this.c10) <= c10_count)
                                && (StringUtils.countMatches(line, this.c11) <= c11_count)
                                && (StringUtils.countMatches(line, this.c12) <= c12_count)) {

                            this.longestWord = line;
                            if (this.longestWord.length() == 12) {
                                break;
                            }
                            if ((System.currentTimeMillis() - startTime) > 
                        MAX_TIME_FOR_WORD_SEARCH ) {
                    break;
                }
                        }

                    } else if (this.longestWord.length() <= line.length()) {
                        if ((StringUtils.countMatches(line, this.c1) <= c1_count)
                                && (StringUtils.countMatches(line, this.c2) <= c2_count)
                                && (StringUtils.countMatches(line, this.c3) <= c3_count)
                                && (StringUtils.countMatches(line, this.c4) <= c4_count)
                                && (StringUtils.countMatches(line, this.c5) <= c5_count)
                                && (StringUtils.countMatches(line, this.c6) <= c6_count)
                                && (StringUtils.countMatches(line, this.c7) <= c7_count)
                                && (StringUtils.countMatches(line, this.c8) <= c8_count)
                                && (StringUtils.countMatches(line, this.c9) <= c9_count)
                                && (StringUtils.countMatches(line, this.c10) <= c10_count)
                                && (StringUtils.countMatches(line, this.c11) <= c11_count)
                                && (StringUtils.countMatches(line, this.c12) <= c12_count)) {

                            this.longestWord = line;
                            if (this.longestWord.length() == 12) {
                                break;
                            }
                            if ((System.currentTimeMillis() - startTime) > 
                        MAX_TIME_FOR_WORD_SEARCH ) {
                    break;
                }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
//        System.out.println("i : " + i);
//        System.out.println("Word : " + this.longestWord);
//        System.out.println("Length: " + this.longestWord.length());

    }

    public String getLongestWord() {
        if (longestWord.equals(null)) {
            longestWord = "There are no words from these letters";
        }
        return longestWord;
    }

  
   

}
