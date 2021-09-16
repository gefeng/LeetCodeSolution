package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Product of Two Elements in an Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/"
)
public class Q1464 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int maxProduct(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);

        return (nums[n - 1] - 1) * (nums[n - 2] - 1);
    }
}
