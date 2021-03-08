package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Coin Change",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/coin-change/"
)
public class Q322 {
    public int coinChange(int[] coins, int amount) {
        return bfsFindShortestPath(coins, amount);
    }

    private int bfsFindShortestPath(int[] coins, int amount) {
        int level = 0;
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        queue.offer(amount);
        visited.add(amount);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int remain = queue.poll();
                if(remain == 0)
                    return level;
                for(int coin : coins) {
                    if(remain - coin >= 0 && !visited.contains(remain - coin)) {
                        queue.offer(remain - coin);
                        visited.add(remain - coin);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    private int recursiveMemoization(int[] coins, int amount) {
        return ccHelper(coins, amount, new Integer[amount + 1]);
    }

    private int ccHelper(int[] coins, int amount, Integer[] memo) {
        if(amount < 0)
            return -1;
        if(amount == 0)
            return 0;
        if(memo[amount] != null)
            return memo[amount];

        int minCoins = -1;
        for(int coin : coins) {
            int numCoins = ccHelper(coins, amount - coin, memo);
            if(numCoins != -1)
                minCoins = minCoins == -1 ? numCoins + 1: Math.min(minCoins, numCoins + 1);
        }
        memo[amount] = minCoins;
        return minCoins;
    }

    // dp[i] = minimum number of coins to make up amount
    // dp[i] = dp[i - 1] + 1; if i - 1 is valid and dp[i - 1] != -1
    private int dpSolution(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int i = 1; i <= amount; i++) {
            for(int coin : coins) {
                int remain = i - coin;
                if(remain >= 0 && dp[remain] != -1)
                    dp[i] = dp[i] == -1 ? dp[remain] + 1 : Math.min(dp[i], dp[remain] + 1);
            }
        }

        return dp[amount];
    }
}
