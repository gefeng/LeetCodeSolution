package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Combination Sum IV",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/combination-sum-iv/"
)
public class Q377 {
    /*
    * 题目的描述有问题，这题应该是叫permutation sum才make sense
    * */
    public int combinationSum4(int[] nums, int target) {
        //return topDownDp(nums, target);
        return bottomUpDp(nums, target);
    }

    private int topDownDp(int[] nums, int target) {
        return helper(nums, 0, target, new Integer[target + 1]);
    }

    private int helper(int[] nums, int curr, int target, Integer[] memo) {
        if(target == 0) {
            return 1;
        }
        if(target < 0 || curr == nums.length) {
            return 0;
        }

        if(memo[target] != null) {
            return memo[target];
        }

        int pick = helper(nums, 0, target - nums[curr], memo);
        int skip = helper(nums, curr + 1, target, memo);

        return memo[target] = pick + skip;
    }

    private int bottomUpDp(int[] nums, int target) {
        int n = nums.length;

        int[] dp = new int[target + 1];
        dp[0] = 1;

        for(int sum = 1; sum < target + 1; sum++) {
            for(int i = 0; i < n; i++) {
                if(sum - nums[i] >= 0) {
                    dp[sum] += dp[sum - nums[i]];
                }
            }
        }

        return dp[target];
    }
}
