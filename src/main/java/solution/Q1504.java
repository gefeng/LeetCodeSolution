package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Count Submatrices With All Ones",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/count-submatrices-with-all-ones/"
)
public class Q1504 {
    /**
     * matrix compression, active[i] == 1 means for ith row from l to r are all 1s.
     *
     * Time:  O(M * N ^ 2)
     * Space: O(M)
     * */
    public int numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int res = 0;

        for(int l = 0; l < n; l++) {
            int[] active = new int[m];
            Arrays.fill(active, 1);
            for(int r = l; r < n; r++) {
                for(int i = 0; i < m; i++) {
                    active[i] &= mat[i][r];
                }

                int cnt = 0;
                for(int i = 0; i < m; i++) {
                    cnt = active[i] == 1 ? cnt + 1 : 0;
                    res += cnt;
                }
            }
        }

        return res;
    }
}
