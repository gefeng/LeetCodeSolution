package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Number At Least Twice of Others",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/largest-number-at-least-twice-of-others/"
)
public class Q747 {
    public int dominantIndex(int[] nums) {
        if(nums.length == 1)
            return 0;

        int max = Integer.MIN_VALUE;
        int secMax = Integer.MIN_VALUE;
        int index = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > max) {
                secMax = max;
                max = nums[i];
                index = i;
            } else if(nums[i] > secMax)
                secMax = nums[i];
        }
        return secMax * 2 > max ? -1 : index;
    }
}
