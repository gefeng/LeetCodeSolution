package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if There is a Valid Path in a Grid",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/"
)
public class Q1391 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    private static final int[][] D = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int[][] S = new int[][] {
            {0, -1, 2, -1}, {-1, 1, -1, 3}, {-1, 1, 2, -1}, {0, 1, -1, -1}, {-1, -1, 2, 3}, {0, -1, -1, 3}
    };
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        return dfs(grid, 0, 0, new boolean[m][n]);
    }

    private boolean dfs(int[][] grid, int r, int c, boolean[][] visited) {
        int m = grid.length;
        int n = grid[0].length;

        if(r == m - 1 && c == n - 1) {
            return true;
        }

        visited[r][c] = true;

        int cell = grid[r][c];

        for(int d : S[cell - 1]) {
            if(d != -1) {
                int nr = r + D[d][0];
                int nc = c + D[d][1];

                if(nr >= 0 && nc >= 0 && nr < m && nc < n && !visited[nr][nc]) {
                    int nCell = grid[nr][nc];
                    if(S[nCell - 1][(d + 2) % 4] != -1) {
                        if(dfs(grid, nr, nc, visited)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
