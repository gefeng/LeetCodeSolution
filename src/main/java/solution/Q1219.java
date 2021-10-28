package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Path with Maximum Gold",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/path-with-maximum-gold/"
)
public class Q1219 {
    /**
     * Time:  O(M * N * 3 ^ (M * N))
     * Space: O(M * N)
     * */
    private int m;
    private int n;
    private int[][] g;
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int getMaximumGold(int[][] grid) {
        int ans = 0;
        this.g = grid;
        this.m = g.length;
        this.n = g[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(g[i][j] != 0) {
                    ans = Math.max(ans, dfs(i, j, new boolean[m][n]));
                }
            }
        }

        return ans;
    }

    private int dfs(int r, int c, boolean[][] seen) {
        seen[r][c] = true;

        int max = 0;
        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && g[nr][nc] != 0 && !seen[nr][nc]) {
                max = Math.max(max, dfs(nr, nc, seen));
            }
        }
        seen[r][c] = false;
        return max + g[r][c];
    }
}
