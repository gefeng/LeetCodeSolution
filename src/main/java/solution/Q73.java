package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Set Matrix Zeroes",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/set-matrix-zeroes/"
)
public class Q73 {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // mark rows/cols to be set in row0 and col0
        boolean set1stRow = false;
        boolean set1stCol = false;
        for(int i = 0; i < m; i++) {
            if(!set1stCol && matrix[i][0] == 0)
                set1stCol = true;
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                    if(!set1stRow && i == 0) {
                        set1stRow = true;
                    }
                }
            }
        }

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if(set1stRow) {
            for(int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }

        if(set1stCol) {
            for(int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
