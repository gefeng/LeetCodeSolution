package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Absolute Sum of Any Subarray",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/"
)
public class Q1749 {
    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        int max = 0;
        int min = 0;
        int sum = 0;

        for(int num : nums) {
            sum += num;
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        }

        return max - min;
    }
}
