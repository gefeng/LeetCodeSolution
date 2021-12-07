package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Sum of Subsequence Widths",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/sum-of-subsequence-widths/"
)
public class Q891 {
    /**
     * Just math. I can't solve :(. Copied uwi's code.
     *
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    private static final long MOD = (long)1e9 + 7;
    public int sumSubseqWidths(int[] nums) {
        int n = nums.length;
        long ans = 0;

        Arrays.sort(nums);

        long sum = 0;
        long tot = 0;
        for(int i = n - 1; i > 0; i--) {
            sum = sum * 2 + nums[i];
            tot = tot * 2 + 1;
            sum %= MOD;
            tot %= MOD;

            ans = (MOD + ans + sum - tot * nums[i - 1] % MOD) % MOD;
        }

        return (int)ans;
    }
}
