package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Toss Strange Coins",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/toss-strange-coins/"
)
public class Q1230 {
    /**
     * state:
     *  dp[i][j] denotes probability of tossing first i coins with j heads.
     * transition:
     *  dp[i][j] = dp[i - 1][j] * (1 - pro[i - 1]) + dp[i - 1][j - 1] * pro[i - 1]
     *
     * Time:  O(N * T)
     * Space: O(N * T)
     * */
    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        double[][] dp = new double[n + 1][target + 1];

        dp[0][0] = 1.0;

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= Math.min(i, target); j++) {
                dp[i][j] = (1.0 - prob[i - 1]) * dp[i - 1][j];
                if(j > 0) {
                    dp[i][j] += prob[i - 1] * dp[i - 1][j - 1];
                }
            }
        }

        return dp[n][target];
    }
}
