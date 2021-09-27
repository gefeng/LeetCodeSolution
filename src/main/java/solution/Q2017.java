package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Grid Game",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/grid-game/"
)
public class Q2017 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        long ans = Long.MAX_VALUE;

        long[] preSum1 = new long[n + 1];
        long[] preSum2 = new long[n + 1];

        for(int i = 1; i < n + 1; i++) {
            preSum1[i] = preSum1[i - 1] + grid[0][i - 1];
            preSum2[i] = preSum2[i - 1] + grid[1][i - 1];
        }

        for(int i = 0; i < n; i++) {
            long s2 = Math.max(preSum1[n] - preSum1[i + 1], preSum2[i]);
            ans = Math.min(ans, s2);
        }

        return ans;
    }
}
