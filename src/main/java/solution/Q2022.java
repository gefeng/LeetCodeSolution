package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Convert 1D Array Into 2D Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/convert-1d-array-into-2d-array/"
)
public class Q2022 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public int[][] construct2DArray(int[] original, int m, int n) {
        int[][] ans = new int[m][n];
        int idx = 0;
        if(original.length != m * n) {
            return new int[0][0];
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                ans[i][j] = original[idx++];
            }
        }

        return ans;
    }
}
