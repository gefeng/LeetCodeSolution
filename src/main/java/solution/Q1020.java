package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Enclaves",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/number-of-enclaves/"
)
public class Q1020 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int[][] g;
    private int m;
    private int n;
    public int numEnclaves(int[][] grid) {
        g = grid;
        m = g.length;
        n = g[0].length;
        int ans = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    if(g[i][j] == 1) {
                        dfs(i, j);
                    }
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                ans += g[i][j];
            }
        }

        return ans;
    }

    private void dfs(int r, int c) {
        g[r][c] = 0;

        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(nr >= 0 && nc >= 0 && nr < m && nc < n && g[nr][nc] == 1) {
                dfs(nr, nc);
            }
        }
    }
}
