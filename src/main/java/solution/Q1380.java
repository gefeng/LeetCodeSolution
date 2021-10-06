package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Lucky Numbers in a Matrix",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/lucky-numbers-in-a-matrix/"
)
public class Q1380 {
    /**
     * Time:  O(M * N)
     * Space: O(M + N)
     * */
    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int[] row = new int[m];
        int[] col = new int[n];

        Arrays.fill(row, Integer.MAX_VALUE);

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                row[i] = Math.min(row[i], matrix[i][j]);
                col[j] = Math.max(col[j], matrix[i][j]);
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == row[i] && matrix[i][j] == col[j]) {
                    ans.add(matrix[i][j]);
                }
            }
        }

        return ans;
    }
}
