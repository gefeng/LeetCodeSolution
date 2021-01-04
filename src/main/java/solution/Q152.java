package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Product Subarray",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-product-subarray/"
)
public class Q152 {
    public int maxProduct(int[] nums) {
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int curr = nums[0];
        int max = maxSoFar;
        for(int i = 1; i < nums.length; i++) {
            curr = nums[i];
            int tempMax = maxSoFar;
            maxSoFar = Math.max(maxSoFar * curr, curr);
            maxSoFar = Math.max(maxSoFar, minSoFar * curr);
            minSoFar = Math.min(minSoFar * curr, curr);
            minSoFar = Math.min(minSoFar, tempMax * curr);
            max = Math.max(max, maxSoFar);
        }

        return max;
    }
}
