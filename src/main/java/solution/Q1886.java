package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Determine Whether Matrix Can Be Obtained By Rotation",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/"
)
public class Q1886 {
    /*
    *   血的教训，矩阵旋转方式：
    *   1. transpose + inverse column
    *   2. rm[j][n - i - 1] = m[i][j];   　
    * */
    public boolean findRotation(int[][] mat, int[][] target) {
        if(Arrays.deepEquals(mat, target)) {
            return true;
        }

        for(int i = 0; i < 3; i++) {
            mat = rotate(mat);
            if(Arrays.deepEquals(mat, target)) {
                return true;
            }
        }

        return false;
    }

    private int[][] rotate(int[][] mat) {
        int n = mat.length;
        int[][] r = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                r[j][n - i - 1] = mat[i][j];
            }
        }

        return r;
    }
}
