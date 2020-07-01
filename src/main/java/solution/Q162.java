package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Peak Element",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/find-peak-element/"
)
public class Q162 {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int middle = 0;
        while(left < right) {
            middle = left + (right - left) / 2;
            if(nums[middle] < nums[middle + 1])
                left = middle + 1;
            else
                right = middle;
        }
        return left;
    }
}
