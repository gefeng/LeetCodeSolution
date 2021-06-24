package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Out of Boundary Paths",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/out-of-boundary-paths/"
)
public class Q576 {
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final int MOD = (int)1e9 + 7;
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        return topDownDp(m, n, maxMove, startRow, startColumn);
    }

    private int bottomUpDp(int m, int n, int maxMove, int startRow, int startColumn) {
        return dfs(m, n, maxMove, startRow, startColumn, 0, new Integer[m][n][maxMove]);
    }

    private int dfs(int m, int n, int maxMove, int sr, int sc, int cntMoves, Integer[][][] memo) {
        if(sr < 0 || sc < 0 || sr > m - 1 || sc > n - 1) {
            return 1;
        }
        if(cntMoves == maxMove) {
            return 0;
        }

        if(memo[sr][sc][cntMoves] != null) {
            return memo[sr][sc][cntMoves];
        }

        int cnt = 0;
        for(int[] dir : DIRECTIONS) {
            int nr = sr + dir[0];
            int nc = sc + dir[1];
            cnt = (cnt + dfs(m, n, maxMove, nr, nc, cntMoves + 1, memo)) % MOD;
        }

        return memo[sr][sc][cntMoves] = cnt;
    }


    /*
        state:
            dp[i][j] means number of pathes to reach i, j at kth move
        transition:
            dp[i][j] = dp[i - 1][j] + dp[i + 1][j] + dp[i][j - 1] + dp[j + 1]
    */
    private int topDownDp(int m, int n, int maxMove, int startRow, int startColumn) {
        int cnt = 0;
        int[][] dpCurr = new int[m][n];
        dpCurr[startRow][startColumn] = 1;

        for(int k = 1; k <= maxMove; k++) {
            int[][] dpNext = new int[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if (i == m - 1) cnt = (cnt + dpCurr[i][j]) % MOD;
                    if (j == n - 1) cnt = (cnt + dpCurr[i][j]) % MOD;
                    if (i == 0) cnt = (cnt + dpCurr[i][j]) % MOD;
                    if (j == 0) cnt = (cnt + dpCurr[i][j]) % MOD;

                    dpNext[i][j] = (((i > 0 ? dpCurr[i - 1][j] : 0) +
                            (i < m - 1 ? dpCurr[i + 1][j] : 0)) % MOD +
                            ((j > 0 ? dpCurr[i][j - 1] : 0) +
                                    (j < n - 1 ? dpCurr[i][j + 1] : 0)) % MOD) % MOD;
                }
            }

            dpCurr = dpNext;
        }

        return cnt;
    }
}
