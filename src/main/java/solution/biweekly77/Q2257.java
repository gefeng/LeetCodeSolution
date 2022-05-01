package solution.biweekly77;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Unguarded Cells in the Grid",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/biweekly-contest-77/problems/count-unguarded-cells-in-the-grid/"
)
public class Q2257 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] dp = new int[m][n];
        int[][] g = new int[m][n];
        int ans = 0;

        for(int[] gd : guards) {
            g[gd[0]][gd[1]] = 1;
        }

        for(int[] w : walls) {
            g[w[0]][w[1]] = 2;
        }

        for(int i = 0; i < m; i++) {
            int guard = 0;
            for(int j = 0; j < n; j++) {
                if(g[i][j] == 0) {
                    dp[i][j] |= guard;
                } else {
                    guard = g[i][j] == 1 ? 1 : 0;
                }
            }

            guard = 0;
            for(int j = n - 1; j >= 0; j--) {
                if(g[i][j] == 0) {
                    dp[i][j] |= guard;
                } else {
                    guard = g[i][j] == 1 ? 1 : 0;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            int guard = 0;
            for(int j = 0; j < m; j++) {
                if(g[j][i] == 0) {
                    dp[j][i] |= guard;
                } else {
                    guard = g[j][i] == 1 ? 1 : 0;
                }
            }

            guard = 0;
            for(int j = m - 1; j >= 0; j--) {
                if(g[j][i] == 0) {
                    dp[j][i] |= guard;
                    if(dp[j][i] == 0) {
                        ans++;
                    }
                } else {
                    guard = g[j][i] == 1 ? 1 : 0;
                }
            }
        }

        return ans;
    }
}
