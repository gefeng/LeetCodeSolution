package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reshape the Matrix",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/reshape-the-matrix/"
)
public class Q566 {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;

        if(m * n != r * c) {
            return mat;
        }

        int[][] ans = new int[r][c];
        int row = 0, col = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                ans[row][col] = mat[i][j];
                row = col == c - 1 ? row + 1 : row;
                col = (col + 1) % c;
            }
        }

        return ans;
    }
}
