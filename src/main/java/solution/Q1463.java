package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Cherry Pickup II",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/cherry-pickup-ii/"
)
public class Q1463 {
    /**
     * Time:  O((M * N) ^ 2)
     * Space: O((M * N) ^ 2)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {1, -1}, {1, 1}};
    public int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return dfs(grid, 0, 0, 0, n - 1, new Integer[m * n][m * n]);
    }

    private int dfs(int[][] grid, int r1, int c1, int r2, int c2, Integer[][] memo) {
        int m = grid.length;
        int n = grid[0].length;
        int collect = c1 == c2 ? grid[r1][c1] : grid[r1][c1] + grid[r2][c2];

        if(r1 == m - 1) {
            return collect;
        }

        int map1 = r1 * n + c1;
        int map2 = r2 * n + c2;
        if(memo[map1][map2] != null) {
            return memo[map1][map2];
        }

        int max = 0;
        for(int[] d1 : DIRECTIONS) {
            int nr1 = r1 + d1[0];
            int nc1 = c1 + d1[1];
            if(isValid(nr1, nc1, m, n)) {
                for(int[] d2 : DIRECTIONS) {
                    int nr2 = r2 + d2[0];
                    int nc2 = c2 + d2[1];
                    if(isValid(nr2, nc2, m, n)) {
                        max = Math.max(max, dfs(grid, nr1, nc1, nr2, nc2, memo) + collect);
                    }
                }
            }
        }

        return memo[map1][map2] = max;
    }

    private boolean isValid(int r, int c, int m, int n) {
        return r >= 0 && c >= 0 && r < m && c < n;
    }
}
