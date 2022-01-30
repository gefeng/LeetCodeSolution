package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Burst Balloons",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/burst-balloons/"
)
public class Q312 {
    /**
     * state:
     *  dp[i][j] denotes max score if bursting balloons i to j.
     * transition:
     *  dp[i][j] = dp[i][k - 1] + dp[k + 1][j] + nums[i - 1] * nums[k] * nums[j + 1]
     *  where k is the last balloon to burst.
     *
     *  Time:  O(N ^ 3)
     *  Space: O(N ^ 2)
     * */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                for(int k = i; k <= j; k++) {
                    int l = k == i ? 0 : dp[i][k - 1];
                    int r = k == j ? 0 : dp[k + 1][j];
                    int score = nums[k] * (i - 1 >= 0 ? nums[i - 1] : 1) * (j + 1 < n ? nums[j + 1] : 1);
                    dp[i][j] = Math.max(dp[i][j], l + r + score);
                }
            }
        }

        return dp[0][n - 1];
    }
}
