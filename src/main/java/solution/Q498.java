package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Diagonal Traverse",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/diagonal-traverse/"
)
public class Q498 {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return new int[0];

        int width = matrix[0].length;
        int height = matrix.length;
        int[] dt = new int[width * height];
        int row = 0;
        int col = 0;
        int dir = -1;
        for(int i = 0; i < dt.length; i++) {
            dt[i] = matrix[row][col];
            row += dir;
            col -= dir;
            if(col > width - 1) {
                row += 2;
                col = width - 1;
                dir *= -1;
            }
            if(row < 0) {
                row = 0;
                dir *= -1;
            }
            if(row > height - 1) {
                row = height - 1;
                col += 2;
                dir *= -1;
            }
            if(col < 0) {
                col = 0;
                dir *= -1;
            }
        }

        return dt;
    }
}
