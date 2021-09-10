package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Arithmetic Slices II - Subsequence",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/arithmetic-slices-ii-subsequence/"
)
public class Q446 {
    /**
     * state:
     *   dp[i][d] denotes number of subsequence ending with nums[i] having d difference
     *   between any two consecutive elements.
     * transition:
     *   dp[i][d] = sum(dp[j][d] + 1) for j from [0, i)
     *
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int res = 0;

        Map<Long, Integer>[] dp = new Map[n];
        for(int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                long dif = (long)nums[j] - nums[i];

                int cnt = dp[j].getOrDefault(dif, 0);

                res += cnt;

                dp[i].put(dif, dp[i].getOrDefault(dif, 0) + cnt + 1);
            }
        }

        return res;
    }
}
