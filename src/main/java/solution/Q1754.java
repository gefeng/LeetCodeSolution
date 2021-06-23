package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Merge Of Two Strings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/largest-merge-of-two-strings/"
)
public class Q1754 {
    public String largestMerge(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();

        while(i < m || j < n) {
            if(i > m - 1) {
                sb.append(word2.charAt(j++));
            } else if(j > n - 1) {
                sb.append(word1.charAt(i++));
            } else {
                char c1 = word1.charAt(i);
                char c2 = word2.charAt(j);
                if(c1 > c2) {
                    sb.append(c1);
                    i++;
                } else if(c1 < c2) {
                    sb.append(c2);
                    j++;
                } else {
                    if(word1.substring(i + 1, m).compareTo(word2.substring(j + 1, n)) >= 0) {
                        sb.append(c1);
                        i++;
                    } else {
                        sb.append(c2);
                        j++;
                    }
                }
            }
        }

        return sb.toString();
    }

    public String recursive(String word1, String word2) {
        if(word1.length() == 0 || word2.length() == 0) {
            return word1 + word2;
        }

        if(word1.compareTo(word2) > 0) {
            return word1.charAt(0) + largestMerge(word1.substring(1), word2);
        }

        return word2.charAt(0) + largestMerge(word1, word2.substring(1));
    }
}
