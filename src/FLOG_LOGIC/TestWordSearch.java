package FLOG_LOGIC;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
    public static String testStr = "RYSBNKNPOLAG";

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

        for (int i = 0; i < testStr.length(); i++) {
            String c = String.valueOf(testStr.charAt(i));
            if (lettersMap.containsKey(c)) {
                Integer value = lettersMap.get(c);
                lettersMap.put(c, ++value);
            } else {
                lettersMap.put(c, 1);
            }
        }
        System.out.println("Letter Map " + lettersMap.toString());
        TreeMap<String, TreeMap> possibles = new TreeMap<String, TreeMap>();
        for (String s : letters) {
            possibles.put(s, new TreeMap<String, String>());
        }

        startTime = System.currentTimeMillis();
        search(trie, possibles);

        System.out.println("Time Dura " + (System.currentTimeMillis() - startTime));
        for (Map.Entry<String, String> s : found.entrySet()) {
            System.out.println("Long - " + s);
        }
        System.out.println("Longest is - " + longestWord + "  0 " + c);
    }

    public static int c = 0;

    public static HashMap<String, String> found = new HashMap<String, String>();

    public static HashMap<String, Integer> lettersMap
            = new HashMap<String, Integer>();

    public static void getAllInTheLevel(TreeMap<String, TreeMap> tree) {
        for (Map.Entry<String, TreeMap> entry : tree.entrySet()) {
            HashMap<String, Integer> keyMapFreq
                    = new HashMap<String, Integer>();

            String key = entry.getKey();
            if (!key.matches(".*\\d+.*")) {
                keyMapFreq.clear();
                if (!found.containsKey(key)) {
                    found.put(key, key);
                }
                boolean correct = true;

                for (int i = 0; i < key.length(); i++) {
                    String c = String.valueOf(key.charAt(i));
                    if (lettersMap.containsKey(c)) {
                        if (keyMapFreq.containsKey(c)) {
                            Integer value = keyMapFreq.get(c);
                            keyMapFreq.put(c, value + 1);
                        } else {
                            keyMapFreq.put(c, 1);
                        }
                    } else {
                        correct = false;
                        break;
                    }
                }
                for (Map.Entry<String, Integer> entry2 : keyMapFreq.entrySet()) {
                    if (entry2.getValue() > lettersMap.get(entry2.getKey())) {
                        correct = false;
                        break;
                    }
                }
                if (correct && key.length() >= longestWord.length()) {
                    longestWord = key;
                }
            }
        }
    }

    public static String longestWord = "";

    public static List<String> getListFromWord(String line, boolean multiple) {
        List<String> lettersFreq = new ArrayList<String>();
        char[] chars = line.toCharArray();
        Arrays.sort(chars);
        char l = chars[0];
        int freq = 1;
        for (int j = 1; j < chars.length; j++) {
            if (l == chars[j]) {
                if (multiple) {
                    lettersFreq.add(l + "," + freq);
                }
                ++freq;
            } else {
                lettersFreq.add(l + "," + freq);
                freq = 1;
                l = chars[j];
                if (j == chars.length - 1) {
                    lettersFreq.add(l + "," + freq);
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

    /**
     * Search for the longest word.
     *
     * @param lookupMap the map to look up from .
     * @param
     */
    public static TreeMap search(TreeMap<String, TreeMap> lookupMap,
            TreeMap<String, TreeMap> keysMap) {
        for (Map.Entry<String, TreeMap> entry : keysMap.entrySet()) {
            String key = entry.getKey();
            TreeMap value = lookupMap.get(key);
            if (value != null && value.size() != 0) {
                search(value, keysMap);
            } else {
                getAllInTheLevel(lookupMap);
            }
        }
        return new TreeMap<String, TreeMap>();
    }
}
