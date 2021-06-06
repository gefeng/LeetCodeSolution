package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Range Sum Query - Immutable",
        difficulty = QDifficulty.EASY,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/range-sum-query-immutable/"
)
public class Q303 {
    int[] dp;
    public Q303(int[] nums) {
        int n = nums.length;
        dp = new int[n + 1];
        for(int i = 1; i < n + 1; i++) {
            dp[i] = dp[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int left, int right) {
        return dp[right + 1] - dp[left];
    }
}
