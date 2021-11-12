package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Flip Columns For Maximum Number of Equal Rows",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/"
)
public class Q1072 {
    /**
     * Time:  O(M ^ 2 * N)
     * Space: O(1)
     * */
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;

        for(int i = 0; i < m; i++) {
            int cnt = 1;

            int[] flip = new int[n];
            for(int j = 0; j < n; j++) {
                flip[j] = matrix[i][j] ^ 1;
            }

            for(int j = i + 1; j < m; j++) {
                if(Arrays.equals(matrix[i], matrix[j]) || Arrays.equals(flip, matrix[j])) {
                    cnt++;
                }
            }

            ans = Math.max(ans, cnt);
        }

        return ans;
    }
}
