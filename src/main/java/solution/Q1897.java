package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Redistribute Characters to Make All Strings Equal",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/redistribute-characters-to-make-all-strings-equal/"
)
public class Q1897 {
    public boolean makeEqual(String[] words) {
        int n = words.length;
        int[] freq = new int[26];

        // fancy code by using stream
        Arrays.stream(words).forEach(w -> w.chars().forEach(c -> freq[c - 'a']++));

        /*for(String word : words) {
            int len = word.length();
            for(int i = 0; i < len; i++) {
                freq[word.charAt(i) - 'a']++;
            }
        }*/

        for(int i = 0; i < 26; i++) {
            if(freq[i] % n != 0) {
                return false;
            }
        }

        return true;
    }
}
