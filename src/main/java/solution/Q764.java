package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Largest Plus Sign",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/largest-plus-sign/"
)
public class Q764 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] g = new int[n][n];
        int[][] dp = new int[n][n];
        int res = 0;

        for(int i = 0; i < n; i++) {
            Arrays.fill(g[i], 1);
            Arrays.fill(dp[i], n);
        }

        for(int[] m : mines) {
            g[m[0]][m[1]] = 0;
        }

        for(int i = 0; i < n; i++) {
            int[] row = new int[n];
            for(int j = 0; j < n; j++) {
                if(g[i][j] == 1) {
                    row[j] = j == 0 ? 1 : row[j - 1] + 1;
                    dp[i][j] = Math.min(dp[i][j], row[j]);
                }
            }
        }

        for(int i = n - 1; i >= 0; i--) {
            int[] row = new int[n];
            for(int j = n - 1; j >= 0; j--) {
                if(g[i][j] == 1) {
                    row[j] = j == n - 1 ? 1 : row[j + 1] + 1;
                    dp[i][j] = Math.min(dp[i][j], row[j]);
                }
            }
        }

        for(int i = 0; i < n; i++) {
            int[] col = new int[n];
            for(int j = 0; j < n; j++) {
                if(g[j][i] == 1) {
                    col[j] = j == 0 ? 1 : col[j - 1] + 1;
                    dp[j][i] = Math.min(dp[j][i], col[j]);
                }
            }
        }

        for(int i = n - 1; i >= 0; i--) {
            int[] col = new int[n];
            for(int j = n - 1; j >= 0; j--) {
                if(g[j][i] == 1) {
                    col[j] = j == n - 1 ? 1 : col[j + 1] + 1;
                    dp[j][i] = Math.min(dp[j][i], col[j]);
                    res = Math.max(res, dp[j][i]);
                }
            }
        }

        return res;
    }
}
