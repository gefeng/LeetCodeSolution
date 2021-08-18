package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Wiggle Subsequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/wiggle-subsequence/"
)
public class Q376 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int wiggleMaxLength(int[] nums) {
        return Math.max(test(nums, 1), test(nums, -1));
    }

    private int test(int[] nums, int sign) {
        int n = nums.length;
        int pre = nums[0];
        int res = 1;

        for(int i = 1; i < n; i++) {
            if((nums[i] - pre) * sign > 0) {
                sign *= -1;
                res++;

            }
            pre = nums[i];
        }

        return res;
    }
}
