package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Subrectangle Queries",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/subrectangle-queries/"
)
public class Q1476 {
    private int[][] matrix;
    private int m;
    private int n;
    public Q1476(int[][] rectangle) {
        matrix = rectangle;
        m = matrix.length;
        n = matrix[0].length;
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for(int i = row1; i <= row2; i++) {
            for(int j = col1; j <= col2; j++) {
                matrix[i][j] = newValue;
            }
        }
    }

    public int getValue(int row, int col) {
        return matrix[row][col];
    }
}
