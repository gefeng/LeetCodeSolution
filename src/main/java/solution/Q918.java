package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Sum Circular Subarray",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-sum-circular-subarray/"
)
public class Q918 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        int cur = 0;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            cur += nums[i];
            ans = Math.max(ans, cur);
            cur = Math.max(cur, 0);
        }

        cur = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            cur += nums[i];
            min = Math.min(min, cur);
            cur = Math.min(cur, 0);
        }

        return sum == min ? ans : Math.max(ans, sum - min);
    }
}
