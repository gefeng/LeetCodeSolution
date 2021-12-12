package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Subarray Ranges",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/sum-of-subarray-ranges/"
)
public class Q2104 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(1)
     * */
    public long subArrayRanges(int[] nums) {
        long sum = 0;
        int n = nums.length;


        for(int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(int j = i; j < n; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                sum += max - min;
            }
        }

        return sum;
    }
}
