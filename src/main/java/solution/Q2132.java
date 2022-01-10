package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Stamping the Grid",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/stamping-the-grid/"
)
public class Q2132 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;
        int h = stampHeight;
        int w = stampWidth;

        int[][] psum = new int[m + 1][n + 1];
        int[][] stamps = new int[m][n];
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                psum[i][j] = psum[i - 1][j] + psum[i][j - 1] - psum[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }

        for(int i = h - 1; i < m; i++) {
            for(int j = w - 1; j < n; j++) {
                int x = i - h + 1;
                int y = j - w + 1;
                if(grid[i][j] == 0 && psum[i + 1][j + 1] - psum[x][j + 1] - psum[i + 1][y] + psum[x][y] == 0) {
                    stamps[i][j]++;
                }
            }
        }

        psum = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                psum[i][j] = psum[i - 1][j] + psum[i][j - 1] - psum[i - 1][j - 1] + stamps[i - 1][j - 1];
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    int x = Math.min(i + h - 1, m - 1);
                    int y = Math.min(j + w - 1, n - 1);

                    if(psum[x + 1][y + 1] - psum[i][y + 1] - psum[x + 1][j] + psum[i][j] == 0) return false;
                }
            }
        }

        return true;
    }
}
