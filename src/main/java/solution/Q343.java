package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Integer Break",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/integer-break/"
)
public class Q343 {
    /*
    * variant coin change
    * variant complete knapsack
    * */
    public int integerBreak(int n) {
        return dpSolution(n);
        //return dpSpaceOptimizedSolution(n);
    }

    /*
        state
            dp[i][j] means max product use first i numbers to form sum j
        transition
            dp[i][j] = max(dp[i - 1][j], dp[i][j - k] * k) where  k is from 1 to i - 1
    */
    private int dpSolution(int n) {
        int[][] dp = new int[n][n + 1];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 1);
        }

        for(int i = 1; i < n; i++) {
            for(int j = 2; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                if(j - i >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - i] * i);
                }
            }
        }

        return dp[n - 1][n];
    }

    private int dpSpaceOptimizedSolution(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);

        for(int i = 1; i < n; i++) {
            for(int j = 2; j <= n; j++) {
                if(j - i >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - i] * i);
                }
            }
        }

        return dp[n];
    }
}
