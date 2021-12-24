package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Max Increase to Keep City Skyline",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/max-increase-to-keep-city-skyline/"
)
public class Q807 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        int[] dpr = new int[n];
        int[] dpc = new int[n];

        for(int i = 0; i < n; i++) {
            int maxr = 0;
            int maxc = 0;
            for(int j = 0; j < n; j++) {
                maxr = Math.max(maxr, grid[i][j]);
                maxc = Math.max(maxc, grid[j][i]);
            }
            dpr[i] = maxr;
            dpc[i] = maxc;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int min = Math.min(dpr[i], dpc[j]);
                ans += Math.max(0, min - grid[i][j]);
            }
        }

        return ans;
    }
}
