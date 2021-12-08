package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Profitable Schemes",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/profitable-schemes/"
)
public class Q879 {
    /**
     * We can stop increasing profit once it reaches the requirement. This can limit the state to minimum profit.
     * state:
     *  (i, group_size, profit) # schemes on first i crimes with group size and profit
     * base:
     *  dp[0][0][0] = 1;
     * transition:
     *  (i, group_size, profit) -> (i + 1, group_size, profit)  if not pick ith crime
     *  (i, group_size, profit) -> (i + 1, group_size + group[i], profit + profit[i]) if pick ith crime
     *
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    private static final long MOD = (long)1e9 + 7;
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;
        long[][] dp = new long[n + 1][minProfit + 1];
        dp[0][0] = 1L;

        for(int i = 0; i < m; i++) {
            long[][] ndp = new long[n + 1][minProfit + 1];
            for(int j = 0; j <= n; j++) {
                for(int k = 0; k <= minProfit; k++) {
                    ndp[j][k] += dp[j][k];
                    ndp[j][k] %= MOD;

                    if(j + group[i] <= n) {
                        ndp[j + group[i]][Math.min(k + profit[i], minProfit)] += dp[j][k];
                        ndp[j + group[i]][Math.min(k + profit[i], minProfit)] %= MOD;
                    }
                }
            }
            dp = ndp;
        }

        long ans = 0;
        for(int i = 0; i <= n; i++) {
            ans += dp[i][minProfit];
            ans %= MOD;
        }

        return (int)ans;
    }
}
