package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Merge Strings Alternately",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/merge-strings-alternately/"
)
public class Q1768 {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int m = word1.length();
        int n = word2.length();
        int i = 0;
        int j = 0;
        while(i < m || j < n) {
            if(i < m) {
                sb.append(word1.charAt(i++));
            }
            if(j < n) {
                sb.append(word2.charAt(j++));
            }
        }

        return sb.toString();
    }
}
