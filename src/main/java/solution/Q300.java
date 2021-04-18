package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Increasing Subsequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/longest-increasing-subsequence/"
)
public class Q300 {
    public int lengthOfLIS(int[] nums) {
        return dpSolution(nums);
    }

    private int recursiveSolution(int[] nums) {
        int maxLen = 0;
        Integer[] memo = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++) {
            maxLen = Math.max(maxLen, findSubsequence(nums, i, memo));
        }
        return maxLen;
    }

    private int findSubsequence(int[] nums, int start, Integer[] memo) {
        if(start == nums.length)
            return 0;

        if(memo[start] != null)
            return memo[start];

        int maxLen = 1;
        for(int i = start + 1; i < nums.length; i++) {
            if(nums[i] > nums[start])
                maxLen = Math.max(maxLen, findSubsequence(nums, i, memo) + 1);
        }

        return memo[start] = maxLen;
    }

    /*
        dp[i] means the maximum length of increasing sequence end with nums[i]
        dp[i] = max(dp[i], dp[i - k] + 1) for any k < i and nums[i] > nums[i - k];
    */
    private int dpSolution(int[] nums) {
        int[] dp = new int[nums.length];

        int maxLen = 0;
        for(int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }
}
