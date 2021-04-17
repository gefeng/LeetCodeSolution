package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Single Element in a Sorted Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/single-element-in-a-sorted-array/"
)
public class Q540 {
    public int singleNonDuplicate(int[] nums) {
        if(nums.length == 1)
            return nums[0];

        int lo = 0;
        int hi = nums.length - 1;
        int mid = 0;
        int unique = 0;

        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;
            int leftLen = mid - lo + 1;

            if(mid > 0 && nums[mid] == nums[mid - 1]) {
                if(leftLen % 2 == 0) {
                    lo = mid + 1;
                } else {
                    hi = mid - 2;
                }
            } else if(mid < nums.length - 1 && nums[mid] == nums[mid + 1]) {
                if(leftLen % 2 == 0) {
                    hi = mid - 1;
                } else {
                    lo = mid + 2;
                }
            } else {
                unique = nums[mid];
                break;
            }
        }

        return unique;
    }
}
