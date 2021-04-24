package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Product of Three Numbers",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/maximum-product-of-three-numbers/"
)
public class Q628 {
    public int maximumProduct(int[] nums) {
        return onePassSolution(nums);
    }

    private int sortSolution(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);

        return Math.max(nums[n - 1] * nums[n - 2] * nums[n - 3], nums[0] * nums[1] * nums[n - 1]);
    }

    private int onePassSolution(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int max2 = max + 1;
        int max3 = max + 2;
        int min = Integer.MAX_VALUE;
        int min2 = min - 1;

        for(int num : nums) {
            if(num > max) {
                max3 = max2;
                max2 = max;
                max = num;
            } else if(num > max2) {
                max3 = max2;
                max2 = num;
            } else if(num > max3) {
                max3 = num;
            }
            if(num < min) {
                min2 = min;
                min = num;
            } else if(num < min2) {
                min2 = num;
            }
        }

        return Math.max(max * max2 * max3, max * min * min2);
    }
}
