package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Matrix Diagonal Sum",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/matrix-diagonal-sum/"
)
public class Q1572 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int diagonalSum(int[][] mat) {
        int res = 0;
        int n = mat.length;

        for(int i = 0; i < n; i++) {
            res += mat[i][i];
        }

        for(int i = 0, j = n - 1; i < n; i++, j--) {
            res += mat[i][j];
        }

        return n % 2 == 0 ? res : res - mat[n / 2][n / 2];
    }
}
