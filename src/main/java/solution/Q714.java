package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Best Time to Buy and Sell Stock with Transaction Fee",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/"
)
public class Q714 {
    /**
     * state:
     *  dp[i][0] denotes max profit at day i with no stock
     *  dp[i][1] denotes max profit at day i with a stock
     * transition:
     *  dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee)
     *  dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i])
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maxProfit(int[] prices, int fee) {
        return topDown(prices, fee);
    }

    private int bottomUp(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][1] -= prices[0];
        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }

    private int topDown(int[] prices, int fee) {
        return dfs(prices, fee, 0, 0, new Integer[prices.length][2]);
    }

    // flag: 0 no stock 1 has stock
    private int dfs(int[] prices, int fee, int day, int flag, Integer[][] memo) {
        int n = prices.length;
        if(day == n) return 0;

        if(memo[day][flag] != null) return memo[day][flag];

        int max = 0;
        if(flag == 0) {
            int buy = dfs(prices, fee, day + 1, 1, memo) - prices[day];
            int skip = dfs(prices, fee, day + 1, 0, memo);
            max = Math.max(buy, skip);
        } else {
            int sell = dfs(prices, fee, day + 1, 0, memo) + prices[day] - fee;
            int skip = dfs(prices, fee, day + 1, 1, memo);
            max = Math.max(sell, skip);
        }

        return memo[day][flag] = max;
    }
}
