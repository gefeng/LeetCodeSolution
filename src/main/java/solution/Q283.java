package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Move Zeroes",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/move-zeroes/"
)
public class Q283 {
    public void moveZeroes(int[] nums) {
        int next = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0)
                nums[next++] = nums[i];
        }
        for(int i = next; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
