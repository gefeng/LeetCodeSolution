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
     *
     * Time:  O(N ^ 3)
     * Space: O(N ^ 3)
     * */
    private final static int MOD = (int)1e9 + 7;
    private int n;
    private int minProfit;
    private int[] group;
    private int[] profit;
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        this.n = n;
        this.minProfit = minProfit;
        this.group = group;
        this.profit = profit;

        return dfs(0, 0, 0, new Integer[group.length][n + 1][minProfit + 1]);
    }

    private int dfs(int cur, int peo, int pro, Integer[][][] memo) {
        if(cur == group.length) {
            return pro == minProfit ? 1 : 0;
        }

        if(memo[cur][peo][pro] != null) {
            return memo[cur][peo][pro];
        }

        int cnt = 0;
        cnt += dfs(cur + 1, peo, pro, memo);

        if(peo + group[cur] <= n) {
            cnt = (cnt + dfs(cur + 1, peo + group[cur], Math.min(pro + profit[cur], minProfit), memo)) % MOD;
        }

        return memo[cur][peo][pro] = cnt;
    }
}
