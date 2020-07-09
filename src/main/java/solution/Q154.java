package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Minimum in Rotated Sorted Array II",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/"
)
public class Q154 {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while(left < right) {
            mid = left + (right - left) / 2;
            if(nums[mid] > nums[right])
                left = mid + 1;
            else if(nums[mid] < nums[right])
                right = mid;
            else
                right--;
        }
        return nums[left];
    }
}
