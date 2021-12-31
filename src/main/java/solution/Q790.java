package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Domino and Tromino Tiling",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/domino-and-tromino-tiling/"
)
public class Q790 {
    /**
     * state:
     *  dp[i][0] denotes number of ways to generate covered tiling with width i
     *  dp[i][1] denotes number of ways to generate uncovered tiling with with i  (i.e. |_ width = 2)
     * transition:
     *  dp[i][0] = dp[i - 1][0] (add |) + dp[i - 2][0] (add =) + dp[i - 1][1]
     *  dp[i][1] = dp[i - 2][0] * 2 + dp[i - 1][1]
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numTilings(int n) {
        if(n < 3) return n;

        long[][] dp = new long[n + 1][2];
        dp[1][0] = 1;
        dp[2][0] = 2;
        dp[2][1] = 2;

        for(int i = 3; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 2][0] + dp[i - 1][1]) % MOD;
            dp[i][1] = (dp[i - 2][0] * 2 + dp[i - 1][1]) % MOD;
        }

        return (int)dp[n][0];
    }
}
