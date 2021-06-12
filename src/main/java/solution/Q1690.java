package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Stone Game VII",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/stone-game-vii/"
)
public class Q1690 {
    public int stoneGameVII(int[] stones) {
        return recursionOptimizedSolution(stones);
    }

    private int recursionSolution(int[] stones) {
        int n = stones.length;
        int[] prefixSum = new int[n + 1];
        for(int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i - 1];
        }
        return dfs(stones, prefixSum, 0, n - 1, true, new Integer[n][n]);
    }

    private int dfs(int[] stones, int[] prefixSum, int start, int end, boolean alice, Integer[][] memo) {
        if(start == end) {
            return 0;
        }

        if(memo[start][end] != null) {
            return memo[start][end];
        }

        int score1 = prefixSum[end + 1] - prefixSum[start + 1];
        int score2 = prefixSum[end] - prefixSum[start];

        if(alice) {
            memo[start][end] = Math.max(score1 + dfs(stones, prefixSum, start + 1, end, !alice, memo),
                    score2 + dfs(stones, prefixSum, start, end - 1, !alice, memo));
        } else {
            memo[start][end] = Math.min(dfs(stones, prefixSum, start + 1, end, !alice, memo) - score1,
                    dfs(stones, prefixSum, start, end - 1, !alice, memo) - score2);
        }


        return memo[start][end];
    }

    /*
        whoever plays first will win, so let's start with player A then B
        A picks a stone and got score x in this turn then next turn if we
        assume it's a new game start with player B on the rest of the stones,
        B will win and get y score ahead. Therefore, go back to current turn
        A will win eventually, how many scores can A get ahead? x - y right?
    */
    private int recursionOptimizedSolution(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for(int stone : stones) {
            sum += stone;
        }

        return dfs(stones, sum, 0, n - 1, new Integer[n][n]);
    }

    private int dfs(int[] stones, int sum, int start, int end, Integer[][] memo) {
        if(start == end) {
            return 0;
        }

        if(memo[start][end] != null) {
            return memo[start][end];
        }

        return memo[start][end] = Math.max(
                sum - stones[start] - dfs(stones, sum - stones[start], start + 1, end, memo),
                sum - stones[end] - dfs(stones, sum - stones[end], start, end - 1, memo));
    }

    /*
    * state:
    *   dp[i][j] means max score difference by playing on stones from i to j
    * transition:
    *   dp[i][j] is the max value of,
    *           picking stones[i], sum[i+1, j] - dp[i+1][j]
    *           picking stones[j], sum[i, j - 1] - dp[i][j - 1]
    * */
    private int tabulationSolution(int[] stones) {
        int n = stones.length;
        int[] ps = new int[n + 1];

        for(int i = 1; i < n + 1; i++) {
            ps[i] = ps[i - 1] + stones[i - 1];
        }

        int[][] dp = new int[n][n];
        for(int i = n - 2; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(ps[j + 1] - ps[i + 1] - dp[i + 1][j],
                        ps[j] - ps[i] - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1];
    }
}
