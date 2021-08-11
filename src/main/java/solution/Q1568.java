package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Days to Disconnect Island",
        difficulty = QDifficulty.HARD,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island/"
)
public class Q1568 {
    /**
     * Time:  O(M ^ 2 * N ^ 2)
     * Space: O(M * N)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int minDays(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if(!isConnected(grid)) {
            return 0;
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    continue;
                }

                grid[i][j] = 0;
                if(!isConnected(grid)) {
                    return 1;
                }
                grid[i][j] = 1;
            }
        }

        return 2;
    }

    private boolean isConnected(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int cnt = 0;
        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, i, j, visited);
                    cnt++;
                }
            }
        }
        return cnt == 1;
    }

    private void dfs(int[][] grid, int r, int c, boolean[][] visited) {
        int m = grid.length;
        int n = grid[0].length;
        visited[r][c] = true;

        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] != 0 && !visited[nr][nc]) {
                dfs(grid, nr, nc, visited);
            }
        }
    }
}
