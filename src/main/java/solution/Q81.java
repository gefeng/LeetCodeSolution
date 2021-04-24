package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Search in Rotated Sorted Array II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/search-in-rotated-sorted-array-ii/"
)
public class Q81 {
    /*
    * Special case:
    *        /
    *  -----/   /------
    *          /
    * */
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;
        int mid = 0;
        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if(nums[mid] == target) {
                return true;
            }

            if(nums[mid] == nums[lo] && nums[mid] == nums[hi]) {
                lo++;
                hi--;
            } else if(nums[mid] <= nums[hi]) {
                if(target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    if(target <= nums[hi]) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
            } else if(nums[mid] > nums[hi]) {
                if(target > nums[mid]) {
                    lo = mid + 1;
                } else {
                    if(target <= nums[hi]) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
            }
        }

        return false;
    }
}
