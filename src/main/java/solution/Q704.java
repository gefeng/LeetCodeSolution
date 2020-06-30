package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Binary Search",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/binary-search/"
)
public class Q704 {
    /**why not middle = (left + right) / 2? cuz right + left can cause overflow**/
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int middle;
        while(left <= right) {
            middle = left + (right - left) / 2;
            if(nums[middle] == target)
                return middle;
            else if(nums[middle] < target)
                left = middle + 1;
            else
                right = middle - 1;
        }
        return - 1;
    }
}
