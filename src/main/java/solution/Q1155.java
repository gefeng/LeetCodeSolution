package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number Of Dice Rolls With Target Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/"
)
public class Q1155 {
    private static final int FACTOR = 1000000007;
    public int numRollsToTarget(int d, int f, int target) {
        return memoizationSolution(d, f, target);
    }

    /*
     * dfs with duplicated calculations
     * Time: O(f^d)
     * Space: O(d)
     * */
    private int dfsSolution(int d, int f, int target) {
        return dfs(d, f, target, 0);
    }
    private int dfs(int d, int f, int target, int roll) {
        if(roll == d)
            return target == 0 ? 1 : 0;

        int total = 0;
        for(int i = 1; i <= f; i++)
            total = (total + dfs(d, f, target - i, roll + 1)) % FACTOR;
        return total;
    }

    /*
    * Time: O(d^2 * f)
    * Space: O(d^2 * f)
    * */
    private int memoizationSolution(int d, int f, int target) {
        return dfsWithMemo(d, f, target, 0, new Integer[d + 1][d * f + 1]);
    }
    private int dfsWithMemo(int d, int f, int target, int roll, Integer[][] memo) {
        if(roll > d || target < 0 || target > d * f)
        return target == 0 ? 1 : 0;

        if(memo[roll][target] != null)
            return memo[roll][target];

        int total = 0;
        for(int i = 1; i <= f; i++)
            total = (total + dfsWithMemo(d, f, target - i, roll + 1, memo)) % FACTOR;
        memo[roll][target] = total;
        return total;
    }


    private int dpSolution(int d, int f, int target) {
        int n = d * f;
        if(target < 1 || target > n)
            return 0;

        int[][] dp = new int[d + 1][n + 1];
        for(int i = 1; i <= f; i++)
            dp[1][i] = 1; // initlize

        for(int i = 2; i <= d; i++) {
            int maxSum = Math.min(target, i * f);
            for(int j = i; j < maxSum + 1; j++) {
                for(int k = 1; k <= f; k++) {
                    if(j - k >= 1)
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % FACTOR;
                }
            }
        }

        return dp[d][target];
    }
}
