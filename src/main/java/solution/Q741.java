package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Cherry Pick",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/cherry-pickup/"
)
public class Q741 {
    /**
     * The problem can be converted to 2 people travel from (0, 0) to (n - 1, n - 1).
     * Is it possible that A visit a cell which B has been visited?
     * It's impossible since,
     * r1 + c1 = r2 + c2
     * You can think of A and B moving along with a line. No matter how A and B pick
     * directions(right or down), they will follow 2 unique path or they will collide
     * at some cell after the same number of steps. Where we should only count cherry
     * once and it's impossible for A to visit a cell which has been visited by B (in
     * this case, cherry on that cell will be counted twice).
     *
     * Time:  O(N ^ 3)
     * Space: O(N ^ 3)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {1, 0}};
    public int cherryPickup(int[][] grid) {
        int n = grid.length;

        int ans = dfs(grid, 0, 0, 0, new Integer[n][n][n]);

        return ans == -1 ? 0 : ans;
    }

    /*
        r1 + c1 = r2 + c2
        r2 = r1 + c1 - c2
    */
    private int dfs(int[][] grid, int r1, int c1, int c2, Integer[][][] memo) {
        int n = grid.length;

        int r2 = r1 + c1 - c2;
        if(r1 == n - 1 && c1 == n - 1) {
            return grid[r1][c1];
        }

        if(memo[r1][c1][c2] != null) {
            return memo[r1][c1][c2];
        }

        int collect = c1 == c2 ? grid[r1][c1] : grid[r1][c1] + grid[r2][c2];

        int max = -1;
        for(int[] d1 : DIRECTIONS) {
            int nr1 = r1 + d1[0];
            int nc1 = c1 + d1[1];
            if(isValid(grid, nr1, nc1)) {
                for(int[] d2 : DIRECTIONS) {
                    int nr2 = r2 + d2[0];
                    int nc2 = c2 + d2[1];
                    if(isValid(grid, nr2, nc2)) {
                        int ret = dfs(grid, nr1, nc1, nc2, memo);
                        if(ret != -1) {
                            max = Math.max(max, ret + collect);
                        }
                    }
                }
            }
        }

        return memo[r1][c1][c2] = max;
    }

    private boolean isValid(int[][] grid, int r, int c) {
        int n = grid.length;
        return r >= 0 && c >= 0 && r < n && c < n && grid[r][c] != -1;
    }
}
