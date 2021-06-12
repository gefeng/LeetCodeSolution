package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Falling Path Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-falling-path-sum/"
)
public class Q931 {
    /*
        state:
            dp[i][j] means max falling sum ends at (i, j)
        transition:
            dp[i][j] =
            min(dp[i - 1][j - 1], dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j] if j > 0 && j < n - 1
            min(dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j] if j == 0
            min(dp[i - 1][j - 1], dp[i - 1][j]) + matrix[i][j] if j == n - 1

        dp[0][j] = matrix[0][j]

    */
    public int minFallingPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        for(int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i];
        }

        for(int i = 1; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(j == 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j];
                } else if(j == n - 1) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + matrix[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j + 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + matrix[i][j];
                }
            }
        }

        int minSum = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            minSum = Math.min(minSum, dp[m - 1][i]);
        }
        return minSum;
    }
}
