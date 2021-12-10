package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Transpose Matrix",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/transpose-matrix/"
)
public class Q867 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] ret = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                ret[i][j] = matrix[j][i];
            }
        }

        return ret;
    }
}
