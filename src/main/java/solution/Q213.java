package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "House Robber II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/house-robber-ii/"
)
public class Q213 {
    /*
        state:
            dp[i][0] max profit so far if rob 1st house
            dp[i][1] max profit so far if don't rob 1st house
        transition:
            dp[i][0] = max(dp[i - 1][0], dp[i - 2][0] + nums[i]) if i < n - 1
            dp[i][0] = dp[i - 1][0] if i == n - 1
            dp[i][1] = max(dp[i - 1][1], dp[i - 2][1] + nums[i])
    */
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) {
            return nums[0];
        }

        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[1][0] = Math.max(nums[0], nums[1]);
        dp[0][1] = 0;
        dp[1][1] = nums[1];

        for(int i = 2; i < n; i++) {
            if(i == n - 1) {
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = Math.max(dp[i - 2][0] + nums[i], dp[i - 1][0]);
            }
            dp[i][1] = Math.max(dp[i - 2][1] + nums[i], dp[i - 1][1]);
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
