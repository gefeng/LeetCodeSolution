package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Length of Subarray With Positive Product",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/"
)
public class Q1567 {
    /**
     * State:
     *  dp[i][0] denotes max len of subarray with +product ending with nums[i]
     *  dp[i][1] denotes max len of subarray with -product ending with nums[i]
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int getMaxLen(int[] nums) {
        int n = nums.length;
        int res = 0;
        int[][] dp = new int[n][2];

        dp[0][0] = nums[0] > 0 ? 1 : 0;
        dp[0][1] = nums[0] < 0 ? 1 : 0;
        res = Math.max(res, dp[0][0]);

        for(int i = 1; i < n; i++) {
            if(nums[i] == 0) {
                dp[i][0] = 0;
                dp[i][1] = 0;
            } else if(nums[i] > 0) {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1] == 0 ? 0 : dp[i - 1][1] + 1;
            } else {
                dp[i][0] = dp[i - 1][1] == 0 ? 0 : dp[i - 1][1] + 1;
                dp[i][1] = dp[i - 1][0] + 1;
            }

            res = Math.max(res, dp[i][0]);
        }

        return res;
    }
}
