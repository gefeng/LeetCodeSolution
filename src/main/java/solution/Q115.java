package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Distinct Subsequences",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/distinct-subsequences/"
)
public class Q115 {
    /*
        state:
            dp[i][j] number of subsequences of s[0, i) which equals to t[0, j)
        transition:
            dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] if s[i - 1] == t[j - 1]
                     = dp[i - 1][j]  if s[i - 1] != t[j - 1]
    */
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i < m + 1; i++) {
            dp[i][0] = 1;
        }

        for(int i = 1; i < m + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if(s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }
}
