package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Partition Equal Subset Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/partition-equal-subset-sum/"
)
public class Q416 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }

        if((sum & 1) != 0) {
            return false;
        }

        //return recursiveMemo(nums, 0, sum / 2, new Boolean[nums.length][sum / 2 + 1]);
        return dp(nums, sum / 2);
    }

    private boolean recursiveMemo(int[] nums, int idx, int target, Boolean[][] memo) {
        if(target == 0) {
            return true;
        }
        if(target < 0 || idx == nums.length) {
            return false;
        }
        if(memo[idx][target] != null) {
            return memo[idx][target];
        }

        if(recursiveMemo(nums, idx + 1, target - nums[idx], memo)) {
            return memo[idx][target] = true;
        }

        if(recursiveMemo(nums, idx + 1, target, memo)) {
            return memo[idx][target] = true;
        }

        return memo[idx][target] = false;
    }

    /*
        dp[i][j] = true means has subset from [0...i] sum to j
        dp[i][j] is true if dp[i - 1][j] is true (not pick nums[i]) or if dp[i - 1][j - nums[i]] is true (pick)
    */
    private boolean dp(int[] nums, int target) {
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][target + 1];
        dp[0][0] = true;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j] || (j - nums[i - 1] >= 0 && dp[i - 1][j - nums[i - 1]]);
            }
        }

        return dp[n][target];
    }
}
