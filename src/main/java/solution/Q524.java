package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.List;

@Problem(
        title = "Longest Word in Dictionary through Deleting",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/"
)
public class Q524 {
    /**
     * Time:  O(M * N)
     * Space: O(1)
     * */
    public String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        for(String word : dictionary) {
            if(isSubsequence(word, s)) {
                int m = word.length();
                int n = res.length();
                if(m > n || (m == n && word.compareTo(res) < 0)) {
                    res = word;
                }
            }
        }

        return res;
    }

    private boolean isSubsequence(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int i = 0, j = 0;

        while(i < m && j < n) {
            if(s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }

        return i == m;
    }
}
