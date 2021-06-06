package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Paint House",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/paint-house/"
)
public class Q256 {
    /*
        state:
            dp[i][j] means minimum cost to paint till i house with last house with color j
        transition:
            dp[i][j] =
                min(dp[i - 1][1], dp[i - 1][2]) + costs[i - 1][0] if j = red
                min(dp[i - 1][0], dp[i - 1][2]) + costs[i - 1][1] if j = blue
                min(dp[i - 1][0], dp[i - 1][1]) + costs[i - 1][2] if j = green
    */
    public int minCost(int[][] costs) {
        return dpSpaceOptimized(costs);
    }

    private int dpSolution(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n + 1][3];

        for(int i = 1; i < n + 1; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i - 1][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i - 1][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i - 1][2];
        }

        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }

    private int dpSpaceOptimized(int[][] costs) {
        int n = costs.length;
        int[] prevCosts = new int[3];
        int[] currCosts = new int[3];

        for(int i = 1; i < n + 1; i++) {
            currCosts[0] = Math.min(prevCosts[1], prevCosts[2]) + costs[i - 1][0];
            currCosts[1] = Math.min(prevCosts[0], prevCosts[2]) + costs[i - 1][1];
            currCosts[2] = Math.min(prevCosts[0], prevCosts[1]) + costs[i - 1][2];

            int[] temp = prevCosts;
            prevCosts = currCosts;
            currCosts = temp;
        }

        return Math.min(prevCosts[0], Math.min(prevCosts[1], prevCosts[2]));
    }
}
