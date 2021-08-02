package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Number of Special Subsequences",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/count-number-of-special-subsequences/"
)
public class Q1955 {
    /**
     *  state:
     *      dp[i][0] means # special subsequence only contains 0 of nums[0, i)
     *      dp[i][1] means # special subsequence contains 0,1 of nums[0, i)
     *      dp[i][2] means # special subsequence contains 0,1,2 of nums[0, i)
     *  transition:
     *      dp[i][0] = dp[i - 1][0] * 2 + 1 if nums[i - 1] == 0
     *               = dp[i - 1][0] else
     *      dp[i][1] = dp[i - 1][1] * 2 + dp[i - 1][0] if nums[i - 1] == 1
     *               = dp[i - 1][1] else
     *      dp[i][2] = dp[i - 1][2] * 2 + dp[i - 1][1] if nums[i - 1] == 2
     *               = dp[i - 1][2] else
     * */
    private static final int MOD = (int)1e9 + 7;
    public int countSpecialSubsequences(int[] nums) {
        return bottomUpDpOptimizedSol(nums);
    }

    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private int bottomUpDpSol(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n + 1][3];

        for(int i = 1; i <= n; i++) {
            dp[i][0] = nums[i - 1] == 0 ? (dp[i - 1][0] * 2 % MOD + 1) % MOD : dp[i - 1][0];
            dp[i][1] = nums[i - 1] == 1 ? (dp[i - 1][1] * 2 % MOD + dp[i - 1][0]) % MOD : dp[i - 1][1];
            dp[i][2] = nums[i - 1] == 2 ? (dp[i - 1][2] * 2 % MOD + dp[i - 1][1]) % MOD : dp[i - 1][2];
        }

        return (int)dp[n][2];
    }

    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    private int bottomUpDpOptimizedSol(int[] nums) {
        int n = nums.length;
        long[] dp = new long[3];

        for(int i = 0; i < n; i++) {
            long[] next = new long[3];
            next[0] = nums[i] == 0 ? (dp[0] * 2 % MOD + 1) % MOD : dp[0];
            next[1] = nums[i] == 1 ? (dp[1] * 2 % MOD + dp[0]) % MOD : dp[1];
            next[2] = nums[i] == 2 ? (dp[2] * 2 % MOD + dp[1]) % MOD : dp[2];

            dp = next;
        }

        return (int)dp[2];
    }
}
