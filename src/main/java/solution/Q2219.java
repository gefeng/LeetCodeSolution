package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Sum Score of Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.PREFIX_SUM,
        url = "https://leetcode.com/problems/maximum-sum-score-of-array/"
)
public class Q2219 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public long maximumSumScore(int[] nums) {
        int n = nums.length;
        long ans = Long.MIN_VALUE;
        long lsum = 0;
        long rsum = 0;

        for(int l = 0, r = n - 1; l < n; l++, r--) {
            lsum += nums[l];
            rsum += nums[r];
            ans = Math.max(ans, Math.max(lsum, rsum));
        }

        return ans;
    }
}
