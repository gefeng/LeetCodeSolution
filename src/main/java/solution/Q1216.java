package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Valid Palindrome III",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/valid-palindrome-iii/"
)
public class Q1216 {
    /**
     * state:
     *  dp[i][j] denotes minimum removals needed to make s[i, j] a palindrome
     * transition:
     *  dp[i][j] = dp[i + 1][j - 1] if s[i] == s[j]
     *  dp[i][j] = min(dp[i][j - 1], dp[i + 1][j]) + 1
     *
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                if(i == j) {
                    dp[i][j] = 0;
                } else if(j - i == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
                } else {
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                    }
                }
            }
        }

        return dp[0][n - 1] <= k;
    }
}
