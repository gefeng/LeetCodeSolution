package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Implement strStr()",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/implement-strstr/"
)
public class Q28 {
    public int strStr(String haystack, String needle) {
        if(needle.isEmpty())
            return 0;

        for(int i = 0; i < haystack.length(); ++i) {
            for(int j = 0; j < needle.length(); ++j) {
                if(i+j > haystack.length() - 1) return -1;
                if(haystack.charAt(i+j) != needle.charAt(j)) break;
                if(j == needle.length() - 1) return i;
            }
        }

        return -1;
    }

    /**
     * Time:  O(M + N)
     * Space: O(M + N)
     * */
    private int KMP(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();

        if(n == 0) {
            return 0;
        }

        // preprocess needle
        // abcxxxaby
        /*
         ...abcxxxabc...
            abcxxxaby
            000000120
            i
             j
                     0123
                         4
        */
        int[] dp = new int[n];

        for(int i = 0, j = 1; j < n; j++) {
            if(needle.charAt(i) == needle.charAt(j)) {
                dp[j] = ++i;
            } else {
                if(i > 0) {
                    i = dp[i - 1];
                    j--;
                }
            }
        }

        // KMP
        for(int i = 0, j = 0; i < m; i++) {
            if(haystack.charAt(i) == needle.charAt(j)) {
                j++;
                if(j == n) {
                    return i - j + 1;
                }
            } else {
                if(j > 0) {
                    j = dp[j - 1];
                    i--;
                }
            }
        }

        return -1;
    }
}
