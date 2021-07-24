package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Range Sum Query 2D - Mutable",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_INDEXED_TREE,
        url = "https://leetcode.com/problems/range-sum-query-2d-mutable/"
)
public class Q308 {
    /**
     * K means number of queries of update() and sum()
     * Time:  O(M * N * logM * logN + K * logM * logN)
     * Space: O(M * N)
     * */
    private class BIT {
        int[][] bit;
        int m;
        int n;
        BIT(int m, int n) {
            this.m = m;
            this.n = n;
            bit = new int[m + 1][n + 1];
        }

        private void add(int x, int y, int val) {
            for(int i = x; i < m + 1; i += i & (-i)) {
                for(int j = y; j < n + 1; j += j & (-j)) {
                    bit[i][j] += val;
                }
            }
        }

        private int query(int x, int y) {
            int sum = 0;
            for(int i = x; i > 0; i -= i & (-i)) {
                for(int j = y; j > 0; j -= j & (-j)) {
                    sum += bit[i][j];
                }
            }

            return sum;
        }
    }
    private BIT bit;
    private int[][] matrix;
    public Q308(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        this.matrix = matrix;
        this.bit = new BIT(m, n);

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                bit.add(i + 1, j + 1, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        bit.add(row + 1, col + 1, val - matrix[row][col]);
        matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return bit.query(row2 + 1, col2 + 1) - bit.query(row1, col2 + 1) - bit.query(row2 + 1, col1) + bit.query(row1, col1);
    }
}
