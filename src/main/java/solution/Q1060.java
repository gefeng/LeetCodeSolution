package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Missing Element in Sorted Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/missing-element-in-sorted-array/"
)
public class Q1060 {
    /*
    * 思路蛮绕的，countMissing这个函数是关键
    * */
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;
        int mid = 0;
        int idx = -1;
        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;
            int count = countMissing(nums, mid);

            if(count >= k) {
                hi = mid - 1;
                idx = mid;
            } else {
                lo = mid + 1;
            }
        }

        if(idx != -1) {
            return nums[idx - 1] + k - countMissing(nums, idx - 1);
        }
        return nums[n - 1] + k - countMissing(nums, n - 1);
    }

    private int countMissing(int[] nums, int i) {
        return nums[i] - nums[0] - i;
    }
}
