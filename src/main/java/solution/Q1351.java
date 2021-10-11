package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Negative Numbers in a Sorted Matrix",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/"
)
public class Q1351 {
    /**
     * Time:  O(M + N)
     * Space: O(1)
     * */
    public int countNegatives(int[][] g) {
        int ans = 0, m = g.length, n = g[0].length;

        for(int i = m - 1, j = 0; i >= 0; i--) {
            while(j < n && g[i][j] >= 0) {
                j++;
            }
            ans += n - j;
        }

        return ans;
    }
}
