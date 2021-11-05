package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Decrease Elements To Make Array Zigzag",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/decrease-elements-to-make-array-zigzag/"
)
public class Q1144 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int movesToMakeZigzag(int[] nums) {
        return Math.min(getSteps(nums, 0), getSteps(nums, 1));
    }

    private int getSteps(int[] nums, int start) {
        int steps = 0;
        int n = nums.length;
        for(int i = start; i < n; i += 2) {
            int dec = 0;
            if(i - 1 >= 0 && nums[i] >= nums[i - 1]) {
                dec = nums[i] - nums[i - 1] + 1;
            }
            if(i + 1 < n && nums[i] >= nums[i + 1]) {
                dec = Math.max(dec, nums[i] - nums[i + 1] + 1);
            }

            steps += dec;
        }

        return steps;
    }
}
