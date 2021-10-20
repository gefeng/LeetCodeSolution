package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Number of Ways to Stay in the Same Place After Some Steps",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/"
)
public class Q1269 {
    /**
     * Cannot go beyond steps
     *
     * Time:  O(S * N)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numWays(int steps, int arrLen) {
        int n = Math.min(steps, arrLen);
        int[][] dp = new int[steps + 1][n];
        dp[0][0] = 1;
        for(int i = 1; i <= steps; i++) {
            for(int j = 0; j < n; j++) {
                if(j > 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
                }
                if(j < n - 1) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD;
                }
                dp[i][j] = (dp[i][j] + dp[i - 1][j]) % MOD;
            }
        }

        return dp[steps][0];
    }
}
