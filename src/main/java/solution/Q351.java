package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Android Unlock Pattern",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/android-unlock-patterns/"
)
public class Q351 {
    /**
     * Time:  O(N * 2 ^ N)
     * Space: O(N * 2 ^ N)
     * */
    private static final int[][] DIRECTIONS1 = new int[][] {{1, 2}, {1, -2}, {-1, 2}, {-1, -2},
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
    private static final int[][] DIRECTIONS2 = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public int numberOfPatterns(int m, int n) {
        int[][] grid = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int res = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                res += dfs(grid, m, n, i, j, 1 << i * 3 + j, new Integer[9][1 << 9]);
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int m, int n, int r, int c, int mask, Integer[][] memo) {
        int picked = cntPicked(mask);

        if(picked == n) {
            return 1;
        }

        if(memo[r * 3 + c][mask] != null) {
            return memo[r * 3 + c][mask];
        }

        int cnt = picked >= m ? 1 : 0;

        for(int[] dir : DIRECTIONS1) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < 3 && nc < 3 && !isPicked(nr, nc, mask)) {
                cnt += dfs(grid, m, n, nr, nc, mask | (1 << nr * 3 + nc), memo);
            }
        }

        for(int[] dir : DIRECTIONS2) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < 3 && nc < 3 && !isPicked(nr, nc, mask)) {
                cnt += dfs(grid, m, n, nr, nc, mask | (1 << nr * 3 + nc), memo);
            }

            int nnr = r + dir[0] * 2;
            int nnc = c + dir[1] * 2;
            if(nnr >= 0 && nnc >= 0 && nnr < 3 && nnc < 3 &&
                    isPicked(nr, nc, mask) && !isPicked(nnr, nnc, mask)) {
                cnt += dfs(grid, m, n, nnr, nnc, mask | (1 << nnr * 3 + nnc), memo);
            }
        }

        return memo[r * 3 + c][mask] = cnt;
    }

    private boolean isPicked(int r, int c, int mask) {
        return ((1 << r * 3 + c) & mask) != 0;
    }

    private int cntPicked(int mask) {
        int res = 0;
        for(int i = 0; i < 9; i++) {
            if(((1 << i) & mask) != 0) {
                res++;
            }
        }
        return res;
    }
}
