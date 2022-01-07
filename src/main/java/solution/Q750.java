package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number Of Corner Rectangles",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/number-of-corner-rectangles/"
)
public class Q750 {
    /**
     * Time:  O(N ^ 2 * M)
     * Space: O(1)
     * */
    public int countCornerRectangles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        for(int l = 0; l < n; l++) {
            for(int r = l + 1; r < n; r++) {
                int cnt = 0;
                for(int i = 0; i < m; i++) {
                    if(grid[i][l] == 1 && grid[i][r] == 1) {
                        cnt++;
                    }
                }

                ans += (cnt - 1) * cnt / 2;
            }
        }

        return ans;
    }
}
