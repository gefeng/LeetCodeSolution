package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Smallest Range I",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/smallest-range-i/"
)
public class Q908 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int smallestRangeI(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;

        Arrays.sort(nums);

        if(nums[n - 1] - nums[0] <= k * 2) {
            return 0;
        } else {
            return nums[n - 1] - nums[0] - 2 * k;
        }
    }
}
