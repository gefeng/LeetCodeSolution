package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Unique Paths",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/unique-paths/"
)
public class Q62 {
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {0, 1}};
    public int uniquePaths(int m, int n) {
        return dpSolution(m, n);
        //return dfsUniquePaths(m, n, 0, 0, new Integer[m][n]);
    }

    private int dfsUniquePaths(int m, int n, int row, int col, Integer[][] memo) {
        if(row == m - 1 && col == n - 1)
            return 1;

        if(memo[row][col] != null)
            return memo[row][col];

        int subPaths = 0;
        for(int[] dir : DIRECTIONS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if(nextRow >= 0 && nextCol >= 0 && nextRow < m && nextCol < n) {
                subPaths += dfsUniquePaths(m, n, nextRow, nextCol, memo);
            }
        }

        return memo[row][col] = subPaths;
    }

    /*
        dp[i][j] means total unique paths to cell (i, j)
        if i == 0 dp[i][j] = 1;
        else if j == 0 dp[i][j] = 1;
        else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    */
    private int dpSolution(int m, int n) {
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 || j == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
