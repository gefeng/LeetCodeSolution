package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Min Cost Climbing Stairs",
        difficulty = QDifficulty.EASY,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/min-cost-climbing-stairs/"
)
public class Q746 {
    /*
        state:
            dp[i] means minimum cost climbing i stairs
        transition:
            dp[i] = min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1])
    */
    public int minCostClimbingStairs(int[] cost) {
        return topDownDp(cost);
    }

    private int bottomUpDp(int[] cost) {
        int n = cost.length;

        int[] dp = new int[n + 1]; // start from step 0
        dp[0] = 0;
        dp[1] = 0; // can start from stair 1

        for(int i = 2; i < n + 1; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[n];
    }

    private int topDownDp(int[] cost) {
        return helper(cost, cost.length, new Integer[cost.length + 1]);
    }

    private int helper(int[] cost, int n, Integer[] memo) {
        if(n == 0 || n == 1) {
            return 0;
        }

        if(memo[n] != null) {
            return memo[n];
        }

        return memo[n] = Math.min(helper(cost, n - 1, memo) + cost[n - 1], helper(cost, n - 2, memo) + cost[n - 2]);
    }
}
