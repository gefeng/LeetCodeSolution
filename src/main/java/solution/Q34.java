package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find First and Last Position of Element in Sorted Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/"
)
public class Q34 {
    public int[] searchRange(int[] nums, int target) {
        int low = -1;
        int high = -1;
        int left = 0;
        int right = nums.length - 1;
        int middle = 0;
        while(left <= right) {
            middle = left + (right - left) / 2;
            if(nums[middle] == target) {
                low = middle;
                right = middle - 1;
            } else if(nums[middle] < target)
                left = middle + 1;
            else
                right = middle - 1;
        }

        left = 0;
        right = nums.length - 1;
        while(left <= right) {
            middle = left + (right - left) / 2;
            if(nums[middle] == target) {
                high = middle;
                left = middle + 1;
            } else if(nums[middle] < target)
                left = middle + 1;
            else
                right = middle - 1;
        }

        return new int[] {low, high};
    }
}
