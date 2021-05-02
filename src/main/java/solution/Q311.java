package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sparse Matrix Multiplication",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/sparse-matrix-multiplication/"
)
public class Q311 {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        return optimized(mat1, mat2);
    }

    private int[][] bruteForce(int[][] mat1, int[][] mat2) {
        int numRow1 = mat1.length;
        int numRow2 = mat2.length;
        int numCol1 = mat1[0].length;
        int numCol2 = mat2[0].length;
        int[][] ans = new int[numRow1][numCol2];

        for(int i = 0; i < numRow1; i++) {
            for(int j = 0; j < numCol2; j++) {
                for(int k = 0; k < numRow2; k++) {
                    ans[i][j] += (mat1[i][k] * mat2[k][j]);
                }
            }
        }

        return ans;
    }

    private int[][] optimized(int[][] mat1, int[][] mat2) {
        int numRow1 = mat1.length;
        int numRow2 = mat2.length;
        int numCol1 = mat1[0].length;
        int numCol2 = mat2[0].length;
        int[][] ans = new int[numRow1][numCol2];

        for(int i = 0; i < numRow1; i++) {
            for(int k = 0; k < numRow2; k++) {
                if(mat1[i][k] == 0)
                    continue;
                for(int j = 0; j < numCol2; j++) {
                    ans[i][j] += (mat1[i][k] * mat2[k][j]);
                }
            }
        }

        return ans;
    }
}
