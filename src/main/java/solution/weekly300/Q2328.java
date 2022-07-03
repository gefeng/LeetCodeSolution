package solution.weekly300;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Number of Increasing Paths in a Grid",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/weekly-contest-300/problems/number-of-increasing-paths-in-a-grid/"
)
public class Q2328 {
    /**
     * Interesting problem.
     * Strictly increasing path implies a topological order. So we can apply dp to count all the topological orderings.
     * DFS + MEMO works as well (actually much more easier to implement). Memo number of increasing path starting at each
     * cell should avoid redundant calculation.
     *
     * Time:  O(M * N * log(M * N))
     * Space: O(M * N)
     * */
    public int countPaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int mod = (int)1e9 + 7;
        int[] dr = new int[] {0, 1, 0, -1};
        int[] dc = new int[] {1, 0, -1, 0};
        int ans = 0;
        int[][] flat = new int[m * n][3];
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int k = i * n + j;
                flat[k] = new int[] {grid[i][j], i, j};
            }
        }

        Arrays.sort(flat, Comparator.comparingInt(a -> a[0]));

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int idx = i * n + j;
                int r = flat[idx][1];
                int c = flat[idx][2];
                dp[r][c] = 1;

                for(int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if(nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] < grid[r][c]) {
                        dp[r][c] = (dp[r][c] + dp[nr][nc]) % mod;
                    }
                }
                ans = (ans + dp[r][c]) % mod;
            }
        }

        return ans;
    }
}
