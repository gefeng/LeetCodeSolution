package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Verifying an Alien Dictionary",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/verifying-an-alien-dictionary/"
)
public class Q953 {
    /*
    * string 比较的基本功, 可以用count[26]代替hashmap i.e. count[c - 'a'] = i;
    * */
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> dict = new HashMap<>();
        for(int i = 0; i < order.length(); i++)
            dict.put(order.charAt(i), i);

        for(int i = 0; i < words.length - 1; i++) {
            String curr = words[i];
            String next = words[i + 1];
            if(compare(curr, next, dict) > 0)
                return false;
        }

        return true;
    }

    private int compare(String word1, String word2, HashMap<Character, Integer> dict) {
        for(int i = 0; i < word1.length() && i < word2.length(); i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            int diff = dict.get(c1) - dict.get(c2);
            if(diff != 0)
                return diff;
        }

        return word1.length() - word2.length();
    }
}
