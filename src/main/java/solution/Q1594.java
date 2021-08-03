package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Non Negative Product in a Matrix",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/"
)
public class Q1594 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int maxProductPath(int[][] grid) {
        long[] res = dfs(grid, 0, 0, new Long[grid.length][grid[0].length][2]);
        return res[1] >= 0 ? (int)(res[1] % MOD) : -1;
    }

    // return min and max
    private long[] dfs(int[][] grid, int i, int j, Long[][][] memo) {
        int m = grid.length;
        int n = grid[0].length;

        if(i == m - 1 && j == n - 1) {
            return new long[] { grid[i][j], grid[i][j] };
        }

        if(memo[i][j][0] != null) {
            return new long[] {memo[i][j][0], memo[i][j][1]};
        }

        long min = 5;
        long max = -5;
        int val = grid[i][j];
        // go right
        if(j < n - 1) {
            long[] r = dfs(grid, i, j + 1, memo);
            min = Math.min(val * r[0], val * r[1]);
            max = Math.max(val * r[0], val * r[1]);
        }

        // go down
        if(i < m - 1) {
            long[] d = dfs(grid, i + 1, j, memo);
            min = Math.min(min, Math.min(val * d[0], val * d[1]));
            max = Math.max(max, Math.max(val * d[0], val * d[1]));
        }

        memo[i][j][0] = min;
        memo[i][j][1] = max;
        return new long[] { memo[i][j][0], memo[i][j][1] };
    }
}
