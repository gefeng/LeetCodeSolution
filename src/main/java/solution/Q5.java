package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Palindromic Substring",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/longest-palindromic-substring/"
)
public class Q5 {
    public String longestPalindrome(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int maxLen = 0;
        int left = 0;
        int right = 0;

        for(int i = len - 1; i >= 0; i--) {
            for(int j = i; j < len; j++) {
                if(i == j)
                    dp[i][j] = true;
                else if(i == j - 1)
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                else
                    dp[i][j] = (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j));

                if(dp[i][j]) {
                    int dist = j - i + 1;
                    if(dist > maxLen) {
                        maxLen = dist;
                        left = i;
                        right = j;
                        //ans = s.substring(i, j + 1);
                    }
                }
            }
        }

        return s.substring(left, right + 1);
    }
}
