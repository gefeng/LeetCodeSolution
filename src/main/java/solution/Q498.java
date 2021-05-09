package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Diagonal Traverse",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/diagonal-traverse/"
)
public class Q498 {
    private static final int[][] DIRECTIONS = new int[][] {{-1, 1}, {1, -1}};
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] ans = new int[m * n];

        int r = 0;
        int c = 0;
        int d = 0;
        int i = 0;
        while(i < m * n) {
            while(r >= 0 && c >= 0 && r < m && c < n) {
                ans[i++] = mat[r][c];
                r += DIRECTIONS[d][0];
                c += DIRECTIONS[d][1];
            }

            // move one step horizontally or vertically depends on current direction
            if(d == 0) {
                c++;
            } else {
                r++;
            }

            d = (d + 1) % DIRECTIONS.length;
            r += DIRECTIONS[d][0];
            c += DIRECTIONS[d][1];
            if(r < 0 || c < 0 || r > m - 1 || c > n - 1) {
                r += DIRECTIONS[d][0];
                c += DIRECTIONS[d][1];
            }
        }

        return ans;
    }
}
