package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Search in Rotated Sorted Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/search-in-rotated-sorted-array/"
)
public class Q33 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int middle = 0;
        while(left <= right) {
            middle = left + (right - left) / 2;
            if(nums[middle] == target)
                return middle;
            else if(nums[middle] >= nums[left]) {  // left side is sorted
                if(target < nums[middle] && target >= nums[left])
                    right = middle - 1;
                else
                    left = middle + 1;
            } else { // right side is sorted
                if(target > nums[middle] && target <= nums[right])
                    left = middle + 1;
                else
                    right = middle - 1;
            }
        }
        return -1;
    }
}
