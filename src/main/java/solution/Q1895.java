package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Magic Square",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/largest-magic-square/"
)
public class Q1895 {
    /*
    * 这题是prefix sum在矩阵里的应用，特别写了一下两条对角线的prefix sum
    * 特别注意antidiagnol的情况，一般prefix sum如果只是从前面得出后一位，只需要首位padding
    * 但是由于antidiagnol需要知道col+1的情况，所有末尾也要padding，这就是为什么antidiagnol
    * 的prefix sum matrix 需要n+2 columns
    * */
    private int[][] rs;
    private int[][] cs;
    private int[][] ds1;
    private int[][] ds2;
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        rs = new int[m + 1][n + 1];
        cs = new int[m + 1][n + 1];
        ds1 = new int[m + 1][n + 1];
        ds2 = new int[m + 1][n + 2];

        for(int i = 1; i < m + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                rs[i][j] = rs[i][j - 1] + grid[i - 1][j - 1];
                cs[i][j] = cs[i - 1][j] + grid[i - 1][j - 1];
                ds1[i][j] = ds1[i - 1][j - 1] + grid[i - 1][j - 1];
                ds2[i][j] = ds2[i - 1][j + 1] + grid[i - 1][j - 1];
            }
        }

        for(int k = Math.min(m, n); k > 1; k--) {
            for(int i = 0; i <= m - k; i++) {
                for(int j = 0; j <= n - k; j++) {
                    if(isMagicSquare(i, j, k)) {
                        return k;
                    }
                }
            }
        }

        return 1;
    }

    private boolean isMagicSquare(int r, int c, int k) {
        int sum = rs[r + 1][c + k] - rs[r + 1][c];

        for(int i = 0; i < k; i++) {
            if(rs[r + i + 1][c + k] - rs[r + i + 1][c] != sum ||
                    cs[r + k][c + i + 1] - cs[r][c + i + 1] != sum) {
                return false;
            }
        }

        return ds1[r + k][c + k] - ds1[r][c] == sum &&
                ds2[r + k][c + 1] - ds2[r][c + k + 1] == sum;
    }
}
