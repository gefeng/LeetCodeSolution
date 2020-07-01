package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Minimum in Rotated Sorted Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/"
)
public class Q153 {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int middle = 0;
        int min = Integer.MAX_VALUE;
        while(left <= right) {
            middle = left + (right - left) / 2;
            min = nums[middle] < min ? nums[middle] : min;
            if(nums[middle] > nums[nums.length - 1]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return min;
    }
}
