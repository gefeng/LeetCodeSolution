package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Unique Path II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/unique-paths-ii/"
)
public class Q63 {
    /*
    * 用dp小心石头在第一排和第一列的情况
    * */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1)
            return 0;
        return dpSolution(obstacleGrid);
    }

    private int recursive(int[][] obstacleGrid, int row, int col, Integer[][] memo) {
        if(row == obstacleGrid.length - 1 && col == obstacleGrid[0].length - 1) {
            return obstacleGrid[row][col] == 0 ? 1 : 0;
        }

        if(memo[row][col] != null)
            return memo[row][col];

        int totalPath = 0;
        int nextRow = row + 1;
        int nextCol = col + 1;
        if(nextRow < obstacleGrid.length && obstacleGrid[nextRow][col] != 1)
            totalPath += recursive(obstacleGrid, nextRow, col, memo);

        if(nextCol < obstacleGrid[0].length && obstacleGrid[row][nextCol] != 1)
            totalPath += recursive(obstacleGrid, row, nextCol, memo);

        return memo[row][col] = totalPath;
    }

    private int dpSolution(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(obstacleGrid[i][j] == 1)
                    dp[i][j] = 0;
                else if(i == 0 && j == 0)
                    dp[i][j] = 1;
                else if(i == 0)
                    dp[i][j] = dp[i][j - 1];
                else if(j == 0)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
