package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Subarray",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY
)
public class Q53 {
    public int maxSubArray(int[] nums) {
        int localMax = nums[0];
        int globalMax = nums[0];

        for(int i = 1; i < nums.length; ++i) {
            localMax = Math.max(nums[i], nums[i] + localMax);
            globalMax = Math.max(localMax, globalMax);
        }
        return globalMax;
    }
}
