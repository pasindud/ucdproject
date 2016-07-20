/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_LOGIC;

import com.sun.xml.internal.fastinfoset.util.CharArray;
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

        System.out.println("Per Letters size - " + letters.size());

        Set<String> sh = new HashSet<String>();
        TreeMap<String, TreeMap> possibles = new TreeMap<String, TreeMap>();
        for (String s : letters) {
            System.out.println("L - " + s);
            String a[] = s.split(",");
            possibles.put(s, new TreeMap<String, String>());
//            possibles.put(a[0], Integer.parseInt(a[1]));
        }

        /*TreeMap<String, Integer> possiblesValues = new TreeMap<String, Integer>();
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 10; j++) {
                
            }
        }*/
//        trav(trie, 0);
//    permutations(sh, new Stack<String>(), sh.size());
//possibles.clear();
//possibles.put("C,1", new TreeMap<String, TreeMap>());
//possibles.put("E,3", new TreeMap<String, TreeMap>());
//possibles.put("I,1", new TreeMap<String, TreeMap>());
//possibles.put("M,1", new TreeMap<String, TreeMap>());
//possibles.put("O,1", new TreeMap<String, TreeMap>());
//possibles.put("R,2", new TreeMap<String, TreeMap>());
//possibles.put("T,1", new TreeMap<String, TreeMap>());
        TreeMap<String, String> done = new TreeMap<String, String>();
        startTime = System.currentTimeMillis();
        TreeMap<String, TreeMap> as = getEntry(trie, 0,
                possibles, testStr, new TreeMap<String, TreeMap>(), done);
        System.out.println("Per size - " + gobalpers.size());
        System.out.println("Time Dura " + (System.currentTimeMillis() - startTime));
        for (Map.Entry<String, String> s : found.entrySet()) {
            System.out.println("Long - " + s);
        }
        System.out.println("Longest is - " + longestWord);
        int startI = 0;
        /*for (int i = 0; i < gobalpers.size(); i++) {
            ArrayList<String> cardsList =
                    new ArrayList<String>(Arrays.asList(gobalpers.get(i)));
            TreeMap<String, TreeMap> as = getEntry(trie, startI, cardsList, testStr, new TreeMap<String, TreeMap>());
            if (as.size() != 0 ) {
                System.out.println("Something was found");
                break;
            }
//            startI++;
        }*/

    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    public static HashMap<String, String> found = new HashMap<String, String>();

    public static void getAllInTheLevel(TreeMap<String, TreeMap> tree) {
        for (Map.Entry<String, TreeMap> entry2 : tree.entrySet()) {
            String key = entry2.getKey();
            TreeMap<String, TreeMap> bb = entry2.getValue();
//            System.out.println(" k - " + entry2.getKey());
            if (!key.matches(".*\\d+.*")) {
               

                boolean correct = true;
                for (int i = 0; i < key.length(); i++) {
                    boolean found = false;
                    for (int j = 0; j < testStr.length(); j++) {
                        if (key.charAt(i) == testStr.charAt(j)) {

                            found = true;
                            break;
                        }
                    }
                    if (found == false) {
                        correct = false;
                        break;
                    }
                }

                if (correct && key.length() >= longestWord.length()) {
                    longestWord = key;
                     if (!found.containsKey(key)) {
                    found.put(key, key);
//                     System.out.println(" - " + key);
                    }
                }
            }
            /*for (Map.Entry<String, TreeMap> entry3 : bb.entrySet()) {
                System.out.println(" k - " + entry3.getKey());
            }*/
        }
    }
    public static List<String[]> gobalpers = new ArrayList<String[]>();

    public static void
            permutations(Set<String> items, Stack<String> permutation, int size) {

        System.out.println("Per size s- " + gobalpers.size());
        /* permutation stack has become equal to size that we require */
//    if(permutation.size() == size) {
        /* print the permutation */
        gobalpers.add(permutation.toArray(new String[0]));

//        System.out.println(Arrays.toString(permutation.toArray(new String[0])));
//    }

        /* items available for permutation */
        String[] availableItems = items.toArray(new String[0]);
        for (String i : availableItems) {
            /* add current item */
            permutation.push(i);

            /* remove item from available item set */
            items.remove(i);

            /* pass it on for next permutation */
            permutations(items, permutation, size);

            /* pop and put the removed item back */
            items.add(permutation.pop());
        }
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
                if (k) {
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

    static int Lookc = 0;
    public static String longestWord = "";

    public static TreeMap getEntry(TreeMap<String, TreeMap> a,
            int l, TreeMap<String, TreeMap> list,
            String word, TreeMap<String, TreeMap> back, TreeMap<String, String> k) {
        for (Map.Entry<String, TreeMap> entry2 : list.entrySet()) {
            String key = entry2.getKey();
            TreeMap value = a.get(key);
//            getAllInTheLevel(a);
            if (value != null && value.size() != 0) {
                getEntry(value, 0, list, word, list, k);
            } else {
                getAllInTheLevel(a);
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
