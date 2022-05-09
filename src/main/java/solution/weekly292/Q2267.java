package solution.weekly292;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if There Is a Valid Parentheses String Path",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/weekly-contest-292/problems/check-if-there-is-a-valid-parentheses-string-path/"
)
public class Q2267 {
    /**
     * Time:  O(M * N * (M + N))
     * Space: O(M * N * (M * N))
     * */
    public boolean hasValidPath(char[][] grid) {
//         int m = grid.length;
//         int n = grid[0].length;

//         return dfs(grid, 0, 0, 0, new Boolean[m][n][m + n + 5]);
        return dpSol(grid);
    }

    private boolean dpSol(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][][] dp = new boolean[m][n][m + n + 1];

        if(grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        dp[0][0][1] = true;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                char c = grid[i][j];
                for(int k = 0; k <= i + j + 1; k++) {
                    if(c == '(') {
                        if(k == 0) {
                            dp[i][j][k] = false;
                        } else {
                            if(i > 0) {
                                dp[i][j][k] = dp[i][j][k] || dp[i - 1][j][k - 1];
                            }
                            if(j > 0) {
                                dp[i][j][k] = dp[i][j][k] || dp[i][j - 1][k - 1];
                            }
                        }
                    } else {
                        if(k == i + j) {
                            dp[i][j][k] = false;
                        } else {
                            if(i > 0) {
                                dp[i][j][k] = dp[i][j][k] || dp[i - 1][j][k + 1];
                            }
                            if(j > 0) {
                                dp[i][j][k] = dp[i][j][k] || dp[i][j - 1][k + 1];
                            }
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }

    private boolean dfs(char[][] g, int r, int c, int open, Boolean[][][] memo) {
        int m = g.length;
        int n = g[0].length;

        if(g[r][c] == '(') {
            open++;
        } else {
            if(open == 0) {
                return memo[r][c][open] = false;
            }

            open--;
        }

        if(r == m - 1 && c == n - 1) {
            return open == 0;
        }

        if(memo[r][c][open] != null) {
            return memo[r][c][open];
        }



        int[][] DIRS = new int[][] {{1, 0}, {0, 1}};

        for(int[] dir : DIRS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n) {
                if(dfs(g, nr, nc, open, memo)) {
                    return true;
                }
            }
        }

        return memo[r][c][open] = false;
    }
}
