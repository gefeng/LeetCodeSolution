package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Max Consecutive Ones",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/max-consecutive-ones/"
)
public class Q485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1)
                count++;
            else {
                max = count > max ? count : max;
                count = 0;
            }
        }
        return Math.max(count, max);
    }
}
