package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove One Element to Make the Array Strictly Increasing",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/"
)
public class Q1909 {
    /*
    * brute force, remove each i to justify
    * */
    public boolean canBeIncreasing(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            if(increasing(nums, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean increasing(int[] nums, int k) {
        int i = k == 0 ? 2 : 1;
        for(; i < nums.length; i++) {
            if(i == k) {
                continue;
            }

            if(nums[i] <= nums[i - 1 == k ? i - 2 : i - 1]) {
                return false;
            }
        }

        return true;
    }
}
