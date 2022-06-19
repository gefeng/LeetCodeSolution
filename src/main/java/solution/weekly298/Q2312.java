package solution.weekly298;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Selling Pieces of Wood",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/weekly-contest-298/problems/selling-pieces-of-wood/"
)
public class Q2312 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    public long sellingWood(int m, int n, int[][] prices) {
        long[][] dp = new long[m + 1][n + 1];

        for(int[] p : prices) {
            dp[p[0]][p[1]] = p[2];
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                for(int k = 1; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }

                for(int k = 1; k <= i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
            }
        }

        return dp[m][n];
    }
}
