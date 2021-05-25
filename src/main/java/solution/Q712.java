package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum ASCII Delete Sum for Two Strings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/"
)
public class Q712 {
    /*
        state:
            dp[i][j] means minimum sum of deleted char to make s1[0, i) and s2[0, j) equal
        transition:
            dp[i][j] = dp[i - 1][j - 1] if s1[i - 1] == s2[j - 1]
                     = min(dp[i - 1][j] + s1[i - 1], dp[i][j - 1] + s2[j - 1])
    */
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 1; i < m + 1; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }

        for(int i = 1; i < n + 1; i++) {
            dp[0][i] = dp[0][i - 1] + s2.charAt(i - 1);
        }

        for(int i = 1; i < m + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }

        return dp[m][n];
    }
}
