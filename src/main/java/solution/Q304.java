package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Range Sum Query 2D - Immutable",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/range-sum-query-2d-immutable/"
)
public class Q304 {
    private int m;
    private int n;
    private int[][] prefixSum;
    public Q304(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        prefixSum = new int[m][n];
        prefixSum[0][0] = matrix[0][0];

        for(int i = 1; i < m; i++) {
            prefixSum[i][0] = prefixSum[i - 1][0] + matrix[i][0];
        }
        for(int j = 1; j < n; j++) {
            prefixSum[0][j] = prefixSum[0][j - 1] + matrix[0][j];
        }

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] +
                        prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        sum = prefixSum[row2][col2];

        if(row1 == 0 && col1 == 0) {
            return sum;
        }

        if(row1 == 0) {
            return sum - prefixSum[row2][col1 - 1];
        }

        if(col1 == 0) {
            return sum - prefixSum[row1 - 1][col2];
        }

        return sum - prefixSum[row1 - 1][col2] - prefixSum[row2][col1 - 1] + prefixSum[row1 - 1][col1 - 1];
    }
}
