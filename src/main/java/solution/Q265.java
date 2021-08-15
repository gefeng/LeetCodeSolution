package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Paint House II",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/paint-house-ii/"
)
public class Q265 {
    public int minCostII(int[][] costs) {
        return bottomUpOptimizedSol(costs);
    }

    /**
     * Time:  O(N * K ^ 2)
     * Space: O(N * K)
     * */
    private int topDownSol(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;

        return dfs(costs, n, k, 0, k, new Integer[n][k + 1]);
    }

    private int dfs(int[][] costs, int n, int k, int i, int preColor, Integer[][] memo) {
        if(i == n) {
            return 0;
        }

        if(memo[i][preColor] != null) {
            return memo[i][preColor];
        }

        int min = Integer.MAX_VALUE;
        for(int c = 0; c < k; c++) {
            if(c == preColor) {
                continue;
            }

            min = Math.min(min, dfs(costs, n, k, i + 1, c, memo) + costs[i][c]);
        }

        return memo[i][preColor] = min;
    }

    /**
     * state:
     *  dp[i][j] denotes min cost to paint i houses with last house painted color j
     * transition:
     *  dp[i][j] = min(dp[i - 1][k] + costs[i - 1][j]) where k != j
     *
     * Time:  O(N * K ^ 2)
     * Space: O(N * K)
     * */
    private int bottomUpSol(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        int res = Integer.MAX_VALUE;
        int[][] dp = new int[n + 1][k];

        for(int i = 0; i < k; i++) {
            dp[1][i] = costs[0][i];
        }

        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < k; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for(int c = 0; c < k; c++) {
                    if(c != j) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][c] + costs[i - 1][j]);
                    }
                }
            }
        }

        for(int i = 0; i < k; i++) {
            res = Math.min(res, dp[n][i]);
        }
        return res;
    }

    /**
     * observed that only minimum cost and the second minimum cost
     * from last column are needed to calculate current column.
     *
     * Time:  O(N * K)
     * Space: O(1)
     * */
    private int bottomUpOptimizedSol(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        int[] preMin1 = new int[] {k, 0};
        int[] preMin2 = new int[] {k, 0};

        for(int i = 0; i < n; i++) {
            int[] curMin1 = new int[] {k, Integer.MAX_VALUE};
            int[] curMin2 = new int[] {k, Integer.MAX_VALUE};
            for(int j = 0; j < k; j++) {
                int cost = j == preMin1[0] ? preMin2[1] + costs[i][j] : preMin1[1] + costs[i][j];
                if(cost < curMin1[1]) {
                    curMin2[0] = curMin1[0];
                    curMin2[1] = curMin1[1];
                    curMin1[0] = j;
                    curMin1[1] = cost;
                } else if(cost < curMin2[1]) {
                    curMin2[0] = j;
                    curMin2[1] = cost;
                }
            }
            preMin1 = curMin1;
            preMin2 = curMin2;
        }

        return preMin1[1];
    }
}
