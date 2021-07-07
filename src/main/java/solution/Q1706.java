package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Where Will the Ball Fall",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/where-will-the-ball-fall/"
)
public class Q1706 {
    public int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] ans = new int[n];

        for(int i = 0; i < n; i++) {
            ans[i] = simulate(grid, i);
        }

        return ans;
    }

    private int simulate(int[][] grid, int col) {
        int m = grid.length;
        int n = grid[0].length;

        int res = -1;

        int cr = 0;
        int cc = col;
        while(true) {
            int diag = grid[cr][cc];
            if((diag == 1 && (cc + 1 > n - 1 || grid[cr][cc + 1] == -1)) ||
                    (diag == -1 && (cc - 1 < 0 || grid[cr][cc - 1] == 1))) {
                res = -1;
                break;
            }

            cr = cr + 1;
            cc = cc + diag;

            if(cr == m) {
                res = cc;
                break;
            }
        }

        return res;
    }
}
