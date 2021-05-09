package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Continuous Increasing Subsequence",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/longest-continuous-increasing-subsequence/"
)
public class Q674 {
    public int findLengthOfLCIS(int[] nums) {
        int max = 1;
        int len = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i - 1]) {
                len++;
            } else {
                len = 1;
            }
            max = Math.max(max, len);
        }

        return max;
    }
}
