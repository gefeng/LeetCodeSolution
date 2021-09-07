package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Difference Between Largest and Smallest Value in Three Moves",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/"
)
public class Q1509 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int minDifference(int[] nums) {
        int n = nums.length;
        int res = Integer.MAX_VALUE;

        Arrays.sort(nums);

        if(n < 5) {
            return 0;
        }

        res = Math.min(nums[n - 2] - nums[2], nums[n - 3] - nums[1]);
        res = Math.min(res, nums[n - 1] - nums[3]);
        res = Math.min(res, nums[n - 4] - nums[0]);

        return res;
    }
}
