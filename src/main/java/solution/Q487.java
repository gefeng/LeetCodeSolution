package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Max Consecutive Ones II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/max-consecutive-ones-ii/"
)
public class Q487 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        int zPointer = -1;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                if(zPointer != -1) {
                    count = i - zPointer - 1;
                }
                zPointer = i;
            }
            count++;
            max = Math.max(max, count);
        }

        return max;
    }
}
