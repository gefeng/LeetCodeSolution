package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Difference Between Highest and Lowest of K Scores",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/"
)
public class Q1984 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int minimumDifference(int[] nums, int k) {
        if(k == 1) {
            return 0;
        }

        Arrays.sort(nums);

        int res = Integer.MAX_VALUE;
        for(int i = k - 1; i < nums.length; i++) {
            res = Math.min(res, nums[i] - nums[i - k + 1]);
        }

        return res;
    }
}
