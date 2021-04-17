package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Coin Change 2",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/coin-change-2/"
)
public class Q518 {
    /*
    * 这题重要思路: 可以选或者不选一枚coin
    * */
    public int change(int amount, int[] coins) {
        return recursiveMemo(amount, coins, 0, new Integer[coins.length][amount + 1]);
    }

    private int recursive(int amount, int[] coins, int idx) {
        if(amount < 0)
            return 0;
        if(amount == 0)
            return 1;
        if(idx == coins.length)
            return 0;

        int total = 0;
        total += recursive(amount - coins[idx], coins, idx); // pick and not move
        total += recursive(amount, coins, idx + 1); // not pick and move
        return total;
    }

    private int recursiveMemo(int amount, int[] coins, int idx, Integer[][] memo) {
        if(amount < 0)
            return 0;
        if(amount == 0)
            return 1;
        if(idx == coins.length)
            return 0;

        if(memo[idx][amount] != null)
            return memo[idx][amount];

        int total = 0;
        total += recursiveMemo(amount - coins[idx], coins, idx, memo); // pick and not move
        total += recursiveMemo(amount, coins, idx + 1, memo); // not pick and move

        memo[idx][amount] = total;
        return total;
    }

    private int dp1(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        for(int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }
        for(int i = 1; i <= coins.length; i++) {
            for(int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j >= coins[i - 1])
                    dp[i][j] += dp[i][j - coins[i - 1]];
            }
        }

        return dp[coins.length][amount];
    }

    private int dp2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for(int i = 0; i < coins.length; i++) {
            for(int j = 0; j <= amount; j++) {
                if(j >= coins[i])
                    dp[j] = dp[j] + dp[j - coins[i]];
            }
        }

        return dp[amount];
    }
}
