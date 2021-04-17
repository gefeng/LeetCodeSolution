package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Spiral Matrix II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/spiral-matrix-ii/"
)
public class Q59 {
    private static final int[][] DIRECTIONS = new int[][] {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int total = n * n;
        int val = 1;
        int dirIdx = 0;
        int row = 0;
        int col = 0;
        while(val <= total) {
            matrix[row][col] = val;

            int[] dir = DIRECTIONS[dirIdx];
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < n &&
                    matrix[nextRow][nextCol] == 0) {
                row = nextRow;
                col = nextCol;
            } else {
                dirIdx = (dirIdx + 1) % 4;
                dir = DIRECTIONS[dirIdx];
                row = row + dir[0];
                col = col + dir[1];
            }

            val++;
        }

        return matrix;
    }
}
