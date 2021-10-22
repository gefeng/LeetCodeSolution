package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Handshakes That Don't Cross",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/handshakes-that-dont-cross/"
)
public class Q1259 {
    /**
     * state:
     *  dp[i] means number of ways to shake hands of i people.
     * transition:
     *  dp[i] = sum(dp[j - 2] * dp[i - j]) for each j within [2, i]
     *
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numberOfWays(int num_people) {
        int n = num_people;
        long[] dp = new long[n + 1];

        dp[0] = 1L;
        dp[2] = 1L;
        for(int i = 4; i <= n; i += 2) {
            for(int j = 2; j <= i; j += 2) {
                dp[i] = (dp[i] + dp[j - 2] * dp[i - j]) % MOD;
            }
        }

        return (int)dp[n];
    }
}
