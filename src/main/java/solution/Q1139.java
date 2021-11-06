package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest 1-Bordered Square",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/largest-1-bordered-square/"
)
public class Q1139 {
    /**
     * Time:  O(M * N * min(M, N))
     * Space: O(M * N)
     * */
    public int largest1BorderedSquare(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dpR = new int[m][n];
        int[][] dpC = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    dpR[i][j] = j == 0 ? 1 : dpR[i][j - 1] + 1;
                    dpC[i][j] = i == 0 ? 1 : dpC[i - 1][j] + 1;
                }
            }
        }

        for(int l = Math.min(m, n); l > 0; l--) {
            for(int i = 0; i <= m - l; i++) {
                for(int j = 0; j <= n - l; j++) {
                    if(grid[i][j] == 0) {
                        continue;
                    }

                    if(dpR[i][j + l - 1] >= l && dpR[i + l - 1][j + l - 1] >= l &&
                            dpC[i + l - 1][j] >= l && dpC[i + l - 1][j + l - 1] >= l) {
                        return l * l;
                    }
                }
            }
        }

        return 0;
    }
}
