package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximize Sum Of Array After K Negations",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/"
)
public class Q1005 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int largestSumAfterKNegations(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        for(int i = 0; i < n; i++) {
            if(nums[i] < 0 && k > 0) {
                nums[i] = nums[i] * -1;
                k--;
            }
        }

        int sum = 0;
        for(int x : nums) {
            sum += x;
        }

        if(k % 2 == 0) {
            return sum;
        }

        int min = Integer.MAX_VALUE;
        for(int x : nums) {
            min = Math.min(min, x);
        }

        return sum - 2 * min;
    }
}
