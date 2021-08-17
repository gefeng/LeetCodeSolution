package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Bomb Enemy",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/bomb-enemy/"
)
public class Q361 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        int[][] cnt = new int[m][n];

        for(int i = 0; i < m; i++) {
            int kills = 0;
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 'W') {
                    kills = 0;
                } else if(grid[i][j] == 'E') {
                    kills++;
                } else {
                    cnt[i][j] += kills;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            int kills = 0;
            for(int j = n - 1; j >= 0; j--) {
                if(grid[i][j] == 'W') {
                    kills = 0;
                } else if(grid[i][j] == 'E') {
                    kills++;
                } else {
                    cnt[i][j] += kills;
                }
            }
        }

        for(int j = 0; j < n; j++) {
            int kills = 0;
            for(int i = 0; i < m; i++) {
                if(grid[i][j] == 'W') {
                    kills = 0;
                } else if(grid[i][j] == 'E') {
                    kills++;
                } else {
                    cnt[i][j] += kills;
                }
            }
        }

        for(int j = 0; j < n; j++) {
            int kills = 0;
            for(int i = m - 1; i >= 0; i--) {
                if(grid[i][j] == 'W') {
                    kills = 0;
                } else if(grid[i][j] == 'E') {
                    kills++;
                } else {
                    cnt[i][j] += kills;
                    res = Math.max(res, cnt[i][j]);
                }
            }
        }

        return res;
    }
}
