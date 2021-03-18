package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Jump Game",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/jump-game/"
)
public class Q55 {
    public boolean canJump(int[] nums) {
        return greedySolution(nums);
    }

    // O(n)
    private boolean greedySolution(int[] nums) {
        int l = nums.length - 2;
        int r = nums.length - 1;
        while(l >= 0 && r >= 0) {
            if(nums[l] != 0 && nums[l] >= r - l) { // can reach r from l
                r = l;
                l--;
            } else {
                l--;
            }
        }
        return r == 0;
    }

    // memoization  O(n^2)
    private boolean jump(int[] nums, int currIdx, Boolean[] memo) {
        if(currIdx == nums.length - 1)
            return true;
        if(currIdx > nums.length - 1)
            return false;

        if(memo[currIdx] != null)
            return memo[currIdx];

        boolean canJump = false;
        for(int i = 1; i <= nums[currIdx]; i++) {
            if(jump(nums, currIdx + i, memo)) {
                canJump = true;
                break;
            }
        }
        memo[currIdx] = canJump;
        return canJump;
    }

    // dp O(n^2)
    private boolean dpSolution(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for(int i = 1; i < nums.length; i++) {
            for(int j = 1; j <= i; j++) {
                if(dp[i - j] && nums[i - j] >= j) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[nums.length - 1];
    }
}
