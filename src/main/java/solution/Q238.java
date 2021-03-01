package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Product of Array Except Self",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/product-of-array-except-self/"
)
public class Q238 {
    public int[] productExceptSelf(int[] nums) {
        return prefixSuffixSolution(nums);
    }

    // O(N) with extra space
    private int[] prefixSuffixSolution(int[] nums) {
        int n = nums.length;
        int[] output = new int[n];
        int[] prefix = new int[n];
        int[] suffix = new int[n];

        int product = 1;
        for(int i = 0; i < nums.length; i++) {
            prefix[i] = product;
            product *= nums[i];
        }

        product = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            suffix[i] = product;
            product *= nums[i];
        }

        for(int i = 0; i < n; i++)
            output[i] = prefix[i] * suffix[i];

        return output;
    }

    // O(N) without extra space
    private int[] spaceOptimizeSolution(int[] nums) {
        int n = nums.length;
        int[] output = new int[n];

        int product = 1;
        for(int i = 0; i < n; i++) {
            output[i] = product;
            product *= nums[i];
        }

        product = 1;
        for(int i = n - 1; i >= 0; i--) {
            output[i] *= product;
            product *= nums[i];
        }

        return output;
    }
}
