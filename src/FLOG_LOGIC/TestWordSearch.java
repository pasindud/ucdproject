/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Pasindu
 */
public class TestWordSearch {
    
    public static final String WORD_FILE_NAME
            = "/Users/Pasindu/Desktop/sep/ucdproject/words.txt";
    public static TreeMap<Double, String> words = new TreeMap<Double, String>();
    public static Integer[] arr = new Integer[26];
    public static TreeMap<String, TreeMap> trie = new TreeMap<String, TreeMap>();
    public static String testStr = "MORTICEEETOR";

    static long startTime = System.currentTimeMillis();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new FileReader(WORD_FILE_NAME));
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim().toUpperCase();
            putEntry(trie, 0, getListFromWord(line, false), line);
        }
        List<String> letters = getListFromWord(testStr, true);
        startTime = System.currentTimeMillis();
        TreeMap<String, TreeMap> as = getEntry(trie, 0, letters, testStr, new TreeMap<String, TreeMap>());
    }

    public static List<String> getListFromWord(String line, boolean k) {
        List<String> lettersFreq = new ArrayList<String>();
        char[] chars = line.toCharArray();
        Arrays.sort(chars);
        char l = chars[0];
        int freq = 1;
        for (int j = 1; j < chars.length; j++) {
            if (k) {
//                System.out.println("Char - " + chars[j]);
            }
            if (l == chars[j]) {
                ++freq;
            } else {
                lettersFreq.add(l + "" + freq + ",");
                freq = 1;
                l = chars[j];
                if (j == chars.length - 1) {
                    lettersFreq.add(l + "" + freq + ",");
                }
            }
        }
        return lettersFreq;
    }

    public static void putEntry(TreeMap<String, TreeMap> b, int j, List<String> list, String line) {
        if (j < list.size()) {
            String key = list.get(j);
            if (!b.containsKey(key)) {
                b.put(key, new TreeMap<String, String>());
            }
            ++j;
            putEntry(b.get(key), j, list, line);
        } else {
            b.put(line.trim(), new TreeMap<String, String>());
        }
    }

    static int Lookc = 0;

    public static TreeMap getEntry(TreeMap<String, TreeMap> a, int l, List<String> list, String word, TreeMap<String, TreeMap> back) {
        if (l < list.size()) {
            String key = list.get(l);
            TreeMap<String, TreeMap> ooo = a.get(key);
            ++l;
            ++Lookc;
            if (ooo == null) {
                System.out.println("LL - " + a.entrySet().iterator().next().getKey());
                return getEntry(a, l, list, word, null);
            }
            if (!key.matches(".*\\d+.*")) {
                System.out.println("Found - " + key);
                return new TreeMap<String, String>();
            }
            return getEntry(ooo, l, list, word, a);
        } else {
            if (a == null) {
                System.out.println("Done nothing found");
                return new TreeMap<String, TreeMap>();
            }
            for (Map.Entry<String, TreeMap> entry2 : a.entrySet()) {
                String key = entry2.getKey();
                if (!key.matches(".*\\d+.*")) {
                    System.out.println("Found - " + key);
                    return new TreeMap<String, String>();
                }
                System.out.println("Key " + key);
                return getEntry(a.get(key), l, list, null, null);
            }

        }
        return new TreeMap<String, TreeMap>();
    }

    public static void trav(TreeMap<String, TreeMap> a, int l) {
        String k = "";
        for (Map.Entry<String, TreeMap> entry2 : a.entrySet()) {
            String childKey = entry2.getKey();
            String out = "";
            for (int i = 0; i < l; i++) {
                out += " ";
            }
            l++;
            trav(a.get(childKey), l);
        }
    }

}
