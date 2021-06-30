package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Largest Submatrix With Rearrangements",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/largest-submatrix-with-rearrangements/"
)
public class Q1727 {
    /*
        1. for each column, find number of consecutive ones ending at current row
        2. sort the row.
    */
    public int largestSubmatrix(int[][] matrix) {
        int maxArea = 0;
        int m = matrix.length;
        int n = matrix[0].length;

        for(int c = 0; c < n; c++) {
            int cntOne = 0;
            for(int r = 0; r < m; r++) {
                if(matrix[r][c] == 1) {
                    cntOne++;
                    matrix[r][c] = cntOne;
                } else {
                    cntOne = 0;
                }
            }
        }

        for(int r = 0; r < m; r++) {
            Arrays.sort(matrix[r]);
        }

        for(int r = 0; r < m; r++) {
            if(matrix[r][n - 1] == 0) {
                continue;
            }
            for(int c = n - 1; c >= 0; c--) {
                if(matrix[r][c] == 0) {
                    break;
                }
                maxArea = Math.max(maxArea, matrix[r][c] * (n - c));
            }
        }

        return maxArea;
    }
}
