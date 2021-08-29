package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the Derangement of An Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/find-the-derangement-of-an-array/"
)
public class Q634 {
    /**
     * state:
     *  dp[i] denotes # ways to derangement with i slots
     * there are 2 possible ways to replace i,
     * 1. pick j and swap i and j
     * 2. pick j put j on position i and put something else on j
     *
     * transition:
     *  dp[i] = dp[i - 1] * (n - 1) + dp[i - 2] * (n - 1)
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int findDerangement(int n) {
        if(n < 2) {
            return 0;
        }

        long[] dp = new long[n + 1];
        dp[1] = 0;
        dp[2] = 1;

        for(int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) * (i - 1) % MOD;
        }

        return (int)(dp[n] % MOD);
    }
}
