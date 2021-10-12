package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "The K Weakest Rows in a Matrix",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/"
)
public class Q1337 {
    /**
     * Time:  O(M * N + M * logM)
     * Space: O(M)
     * */
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] rows = new int[m][2];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                rows[i][0] += mat[i][j];
                rows[i][1] = i;
            }
        }

        Arrays.sort(rows, (a, b) -> {
            if(a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int[] ans = new int[k];
        for(int i = 0; i < k; i++) {
            ans[i] = rows[i][1];
        }
        return ans;
    }
}
