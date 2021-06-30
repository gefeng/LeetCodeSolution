package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Alternating Subsequence Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-alternating-subsequence-sum/"
)
public class Q1911 {
    public long maxAlternatingSum(int[] nums) {
        return topDownDp(nums);
    }

    private long bottomUpDp(int[] nums) {
        return dfs(nums, 0, 1, new Long[nums.length][3]);
    }

    private long dfs(int[] nums, int i, int sign, Long[][] memo) {
        if(i == nums.length) {
            return 0;
        }

        if(memo[i][sign + 1] != null) {
            return memo[i][sign + 1];
        }

        long pick = dfs(nums, i + 1, sign * -1, memo) + sign * nums[i];
        long skip = dfs(nums, i + 1, sign, memo);

        return memo[i][sign + 1] = Math.max(pick, skip);
    }

    /*
        state:
            dp[i][0] means max subseq sum using first i elements and i - 1 is even
            dp[i][1] means max subseq sum using first i elements and i - 1 is odd
        transition:
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + nums[i - 1])
            dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - nums[i - 1]);
    */
    private long topDownDp(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n + 1][2];

        for(int i = 1; i < n + 1; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + nums[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - nums[i - 1]);
        }

        return Math.max(dp[n][0], dp[n][1]);
    }
}
