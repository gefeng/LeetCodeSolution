package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Best Time to Buy and Sell Stock with Cooldown",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/"
)
public class Q309 {
    public int maxProfit(int[] prices) {
        return stateMachineSol(prices);
    }

    /**
     * action can be performed on day i: 0 buy 1 sell
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    private int topDownSol(int[] prices) {
        return dfs(prices, 0, 0, new Integer[prices.length][2]);
    }

    private int dfs(int[] prices, int i, int action, Integer[][] memo) {
        int n = prices.length;

        if(i >= n) {
            return 0;
        }

        if(memo[i][action] != null) {
            return memo[i][action];
        }

        int max = 0;
        if(action == 0) {
            max = Math.max(dfs(prices, i + 1, 1, memo) - prices[i], dfs(prices, i + 1, 0, memo));
        } else {
            max = Math.max(dfs(prices, i + 2, 0, memo) + prices[i], dfs(prices, i + 1, 1, memo));
        }

        return memo[i][action] = max;
    }

    /**
     * Good approach based on state machine
     * held -> held/sold
     * sold -> reset
     * reset -> held/reset
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    private int stateMachineSol(int[] prices) {
        int n = prices.length;
        int held = Integer.MIN_VALUE;
        int sold = Integer.MIN_VALUE;
        int reset = 0;

        for(int i = 0; i < n; i++) {
            int nheld = Math.max(held, reset - prices[i]);
            int nsold = held + prices[i];
            int nreset = Math.max(sold, reset);
            held = nheld;
            sold = nsold;
            reset = nreset;
        }

        return Math.max(reset, Math.max(held, sold));
    }
}
