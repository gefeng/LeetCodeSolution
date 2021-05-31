package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimize Maximum Pair Sum in Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/"
)
public class Q1877 {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);

        int lo = 0;
        int hi = nums.length - 1;

        int max = 0;
        while(lo < hi) {
            max = Math.max(max, nums[lo++] + nums[hi--]);
        }

        return max;
    }
}
