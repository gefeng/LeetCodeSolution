package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Dice Roll Simulation",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/dice-roll-simulation/"
)
public class Q1223 {
    /*
        dp[i][j][k] denotes seq can be generated after rolling i rounds last roll is j and j appears consecutivly k times
    */
    /**
     * state:
     *  dp[i][j][k] denotes seq can be generated after rolling i rounds last roll is j and j appears consecutivly k times
     *
     * Time:  O(N * (6 * max) ^ 2)
     * Space: O(6 * max)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int dieSimulator(int n, int[] rollMax) {
        int ans = 0;
        int[][] dp = new int[7][16];

        for(int i = 1; i <= 6; i++) {
            dp[i][1] = 1;
        }

        for(int i = 2; i <= n; i++) {
            int[][] ndp = new int[7][16];

            for(int j = 1; j <= 6; j++) {
                for(int k = 1; k <= rollMax[j - 1]; k++) {
                    if(k == 1) {
                        for(int l = 1; l <= 6; l++) {
                            if(l == j) {
                                continue;
                            }
                            for(int m = 1; m <= rollMax[l - 1]; m++) {
                                ndp[j][k] = (ndp[j][k] + dp[l][m]) % MOD;
                            }
                        }
                    } else {
                        ndp[j][k] = dp[j][k - 1];
                    }
                }
            }

            dp = ndp;
        }

        for(int i = 1; i <= 6; i++) {
            for(int j = 1; j < 16; j++) {
                ans = (ans + dp[i][j]) % MOD;
            }
        }

        return ans;
    }
}
