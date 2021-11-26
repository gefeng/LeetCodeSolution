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
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[] dp = new int[n];

        for(int i = 0; i < n; i++) {
            dp[i] = matrix[0][i];
        }

        for(int i = 1; i < n; i++) {
            int[] ndp = new int[n];
            for(int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for(int k = -1; k <= 1; k++) {
                    if(j + k >= 0 && j + k < n) {
                        min = Math.min(min, dp[j + k] + matrix[i][j]);
                    }
                }
                ndp[j] = min;
            }
            dp = ndp;
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[i]);
        }

        return ans;
    }
}
