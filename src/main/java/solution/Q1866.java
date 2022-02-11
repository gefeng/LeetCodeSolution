package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Ways to Rearrange Sticks With K Sticks Visible",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-ways-to-rearrange-sticks-with-k-sticks-visible/"
)
public class Q1866 {
    /**
     * A combinatorics problem.
     * The array can be divided into k parts and each part has a leading stick which is the tallest in its part
     * and also all the leading elements are sorted in increasing order.
     * Each time we can create a new division or add current stick to previous division.
     *
     * Time:  O(N * K)
     * Space: O(N * K)
     * */
    private static final long MOD = (long)1e9 + 7;
    public int rearrangeSticks(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];

        dp[0][0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= k; j++) {
                long a = dp[i - 1][j - 1];
                long b = dp[i - 1][j] * (i - 1) % MOD;
                dp[i][j] = (a + b) % MOD;
            }
        }

        return (int)dp[n][k];
    }
}
