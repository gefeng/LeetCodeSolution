package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Substring with Concatenation of All Words",
        difficulty = QDifficulty.HARD,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/substring-with-concatenation-of-all-words/"
)
public class Q30 {
    /**
     * brute force
     * N: length of s
     * M: number of words in dictionary
     * K: length of single word
     *
     * Time:  O(N * M * K)
     * Space: O(M * K)
     * */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> dict = new HashMap<>();
        int k = words[0].length();
        int m = words.length;
        int n = s.length();

        for(String word : words) {
            dict.put(word, dict.getOrDefault(word, 0) + 1);
        }

        for(int l = 0; l <= n - m * k; l++) {
            if(search(s, dict, k, l)) {
                ans.add(l);
            }
        }

        return ans;
    }

    private boolean search(String s, Map<String, Integer> dict, int k, int start) {
        if(dict.isEmpty()) {
            return true;
        }

        int n = s.length();
        if(start > n - k) {
            return false;
        }

        String w = s.substring(start, start + k);
        Integer freq = dict.get(w);

        if(freq == null) {
            return false;
        } else {
            if(freq == 1) {
                dict.remove(w);
            } else {
                dict.put(w, freq - 1);
            }

            boolean res = search(s, dict, k, start + k);

            dict.put(w, dict.getOrDefault(w, 0) + 1);

            return res;
        }
    }
}
