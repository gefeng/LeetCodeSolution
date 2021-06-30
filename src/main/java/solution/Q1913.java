package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Product Difference Between Two Pairs",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/maximum-product-difference-between-two-pairs/"
)
public class Q1913 {
    public int maxProductDifference(int[] nums) {
        int[] max = getMax(nums);
        int[] min = getMin(nums);
        return max[0] * max[1] - min[0] * min[1];
    }

    private int[] getMax(int[] nums) {
        int max1 = 0;
        int max2 = 0;
        for(int num : nums) {
            if(num > max1) {
                max2 = max1;
                max1 = num;
            } else if(num > max2) {
                max2 = num;
            }
        }
        return new int[] {max1, max2};
    }

    private int[] getMin(int[] nums) {
        int min1 = 10001;
        int min2 = 10001;
        for(int num : nums) {
            if(num < min1) {
                min2 = min1;
                min1 = num;
            } else if(num < min2) {
                min2 = num;
            }
        }

        return new int[] {min1, min2};
    }
}
