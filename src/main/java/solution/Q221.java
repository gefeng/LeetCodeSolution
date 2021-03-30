package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximal Square",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximal-square/"
)
public class Q221 {
    public int maximalSquare(char[][] matrix) {
        return dpSolution(matrix);
    }

    private int dpMemo(char[][] matrix) {
        int max = 0;
        Integer[][] memo = new Integer[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dfs(matrix, i, j, memo));
            }
        }
        return max * max;
    }

    private int dfs(char[][] matrix, int row, int col, Integer[][] memo) {
        if(matrix[row][col] == '0')
            return 0;
        if(row + 1 > matrix.length - 1 || col + 1 > matrix[0].length - 1)
            return 1;

        if(memo[row][col] != null)
            return memo[row][col];

        int r = dfs(matrix, row, col + 1, memo);
        int d = dfs(matrix, row + 1, col, memo);
        int rd = dfs(matrix, row + 1, col + 1, memo);

        int min = Math.min(Math.min(r, d), rd) + 1;
        memo[row][col] = min;
        return min;
    }

    private int dpSolution(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(matrix[i][j] == '0')
                    dp[i][j] = 0;
                else if(i == m - 1 || j == n - 1)
                    dp[i][j] = 1;
                else
                    dp[i][j] = Math.min(Math.min(dp[i + 1][j], dp[i][j + 1]), dp[i + 1][j + 1]) + 1;
                max = Math.max(max, dp[i][j]);
            }
        }

        return max * max;
    }
}
