package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Matrix Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/maximum-matrix-sum/"
)
public class Q1975 {
    /**
     * Time:  O(M * N)
     * Space: O(1)
     * */
    public long maxMatrixSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                cnt = matrix[i][j] < 0 ? cnt + 1 : cnt;
                min = Math.min(min, Math.abs(matrix[i][j]));
            }
        }

        long sum = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                sum += Math.abs(matrix[i][j]);
            }
        }

        return cnt % 2 == 0 ? sum : sum - 2 * min;
    }
}
