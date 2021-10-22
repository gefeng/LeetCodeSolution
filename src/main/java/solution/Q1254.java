package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Closed Islands",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/number-of-closed-islands/"
)
public class Q1254 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    private int m;
    private int n;
    private int[][] g;

    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int closedIsland(int[][] grid) {
        g = grid;
        m = grid.length;
        n = grid[0].length;
        int ans = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    if(dfs(i, j)) {
                        ans += 1;
                    }
                }
            }
        }

        return ans;
    }

    private boolean dfs(int r, int c) {
        boolean isOk = true;

        g[r][c] = 2;

        if(r == 0 || r == m - 1 || c == 0 || c == n - 1) {
            isOk = false;
        }

        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && g[nr][nc] == 0) {
                boolean ret = dfs(nr, nc);
                if(!ret) {
                    isOk = false;
                }
            }
        }

        return isOk;
    }
}
