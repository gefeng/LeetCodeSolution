package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Absolute Differences in a Sorted Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.PREFIX_SUM,
        url = "https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/"
)
public class Q1685 {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int[] prefixSum = new int[n + 1];

        for(int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        for(int i = 0; i < n; i++) {
            int l = i == 0 ? 0 : nums[i] * i - prefixSum[i];
            int r = i == n - 1 ? 0 : prefixSum[n] - prefixSum[i + 1] - nums[i] * (n - 1 - i);
            ans[i] = l + r;
        }

        return ans;
    }
}
