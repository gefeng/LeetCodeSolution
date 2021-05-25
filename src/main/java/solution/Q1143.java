package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Common Subsequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/longest-common-subsequence/"
)
public class Q1143 {
    /*
        classic dp problem

        state:
            dp[i][j] lcs of substring(text1, 0, i) and substring(text2, 0, j)
        transition:
            dp[i][j] = dp[i - 1][j - 1] + 1 if s[i - 1] == s[j - 1]
            dp[i][j] = max(dp[i][j - 1], dp[i - 1][j])
    */
    public int longestCommonSubsequence(String text1, String text2) {
        return topDownDp(text1, text2);
    }

    private int topDownDp(String text1, String text2) {
        return helper(text1, text2, 0, 0, new Integer[text1.length()][text2.length()]);
    }

    private int helper(String text1, String text2, int i, int j, Integer[][] memo) {
        if(i == text1.length() || j == text2.length()) {
            return 0;
        }

        if(memo[i][j] != null) {
            return memo[i][j];
        }

        if(text1.charAt(i) == text2.charAt(j)) {
            return memo[i][j] = helper(text1, text2, i + 1, j + 1, memo) + 1;
        } else {
            return memo[i][j] = Math.max(helper(text1, text2, i + 1, j, memo),
                    helper(text1, text2, i, j + 1, memo));
        }
    }

    private int bottomUpDp(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[m][n];
    }
}
