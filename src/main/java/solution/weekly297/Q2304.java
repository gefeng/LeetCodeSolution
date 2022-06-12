package solution.weekly297;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Path Cost in a Grid",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/weekly-contest-297/problems/minimum-path-cost-in-a-grid/"
)
public class Q2304 {
    /**
     * Time:  O(M * N * N)
     * Space: O(N)
     * */
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = Integer.MAX_VALUE;
        int[] dp = new int[n]; // minimum cost moving from (i - 1, j) to (i, j)

        for(int i = 0; i < n; i++) {
            dp[i] = grid[0][i];
        }

        for(int i = 1; i < m; i++) {
            int[] ndp = new int[n];
            for(int j = 0; j < n; j++) {
                ndp[j] = Integer.MAX_VALUE;
                for(int k = 0; k < n; k++) {
                    ndp[j] = Math.min(ndp[j], dp[k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                }
            }
            dp = ndp;
        }

        for(int i = 0; i < n; i++) {
            ans = Math.min(dp[i], ans);
        }

        return ans;
    }
}
