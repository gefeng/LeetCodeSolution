package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximal Rectangle",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/maximal-rectangle/"
)
public class Q85 {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;

        int[] rows = new int[m];
        for(int l = 0; l < n; l++) {
            Arrays.fill(rows, 0);
            for(int r = l; r < n; r++) {
                int sum = 0;
                for(int i = 0; i < m; i++) {
                    rows[i] += matrix[i][r] - '0';
                    if(rows[i] != r - l + 1) {
                        rows[i] = 0;
                    }

                    sum = rows[i] == 0 ? 0 : sum + rows[i];
                    ans = Math.max(ans, sum);
                }
            }
        }

        return ans;
    }
}
