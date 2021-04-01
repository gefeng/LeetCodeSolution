package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Path Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-path-sum/"
)
public class Q64 {
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {0, 1}};
    public int minPathSum(int[][] grid) {
        return dfsMinPathSum(grid, 0, 0, new Integer[grid.length][grid[0].length]);
    }

    /*
    * Time: O(2^(m + n)) -> O(m * n)
    * Space: O(m + n)
    * */
    private int dfsMinPathSum(int[][] grid, int row, int col, Integer[][] memo) {
        if(row == grid.length - 1 && col == grid[0].length - 1)
            return grid[row][col];

        if(memo[row][col] != null)
            return memo[row][col];

        int minSum = -1;
        for(int[] dir : DIRECTIONS) {
            int nRow = row + dir[0];
            int nCol = col + dir[1];
            if(nRow >= 0 && nCol >= 0 && nRow < grid.length && nCol < grid[0].length) {
                int candidate = dfsMinPathSum(grid, nRow, nCol, memo);
                if(minSum == -1)
                    minSum = candidate;
                else
                    minSum = Math.min(minSum, candidate);
            }
        }

        minSum = minSum + grid[row][col];
        memo[row][col] = minSum;
        return minSum;
    }

    private int dpSolution(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(i == m - 1 && j == n - 1)
                    dp[i][j] = grid[i][j];
                else if(i == m - 1)
                    dp[i][j] = dp[i][j + 1] + grid[i][j];
                else if(j == n - 1)
                    dp[i][j] = dp[i + 1][j] + grid[i][j];
                else
                    dp[i][j] = Math.min(dp[i][j + 1], dp[i + 1][j]) + grid[i][j];
            }
        }

        return dp[0][0];
    }
}
