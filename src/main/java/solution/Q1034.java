package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Coloring A Border",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/coloring-a-border/"
)
public class Q1034 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int[][] g;
    private int m;
    private int n;
    private int[][] ret;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        g = grid;
        m = g.length;
        n = g[0].length;
        ret = new int[m][n];

        if(g[row][col] == color) {
            return g;
        }

        dfs(row, col, g[row][col], color);

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(ret[i][j] <= 0) {
                    ret[i][j] = g[i][j];
                }
            }
        }

        return ret;
    }

    private void dfs(int r, int c, int oColor, int nColor) {
        boolean isBorder = false;
        if(r == 0 || c == 0 || r == m - 1 || c == n - 1) {
            isBorder = true;
        }
        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && g[nr][nc] != oColor) {
                isBorder = true;
            }
        }

        if(isBorder) {
            ret[r][c] = nColor;
        } else {
            ret[r][c] = -1;
        }

        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && ret[nr][nc] == 0 && g[nr][nc] == oColor) {
                dfs(nr, nc, oColor, nColor);
            }
        }
    }
}
