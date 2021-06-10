package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Ascending Subarray Sum",
        difficulty = QDifficulty.EASY,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/maximum-ascending-subarray-sum/"
)
public class Q1800 {
    public int maxAscendingSum(int[] nums) {
        int n = nums.length;
        int maxSum = nums[0];
        int sum = nums[0];
        for(int i = 1; i < n; i++) {
            if(nums[i] <= nums[i - 1]) {
                sum = 0;
            }
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }
}
