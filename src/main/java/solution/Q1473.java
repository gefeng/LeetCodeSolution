package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Paint House III",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/paint-house-iii/"
)
public class Q1473 {
    /**
     * Time:  O(M * K * N ^ 2)
     * Space: O(M * K * N)
     * */
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        return dfs(houses, cost, m, n, target, 0, 0, new Integer[m][n + 1][target + 1]);
    }

    private int dfs(int[] houses, int[][] cost, int m, int n, int k, int preColor, int cur, Integer[][][] memo) {
        if(cur == m && k == 0) {
            return 0;
        }
        if(cur == m && k != 0) {
            return -1;
        }
        if(cur != m && k < 0) {
            return -1;
        }

        if(memo[cur][preColor][k] != null) {
            return memo[cur][preColor][k];
        }

        int min = -1;
        if(houses[cur] != 0) {
            if(houses[cur] == preColor) {
                min = dfs(houses, cost, m, n, k, preColor, cur + 1, memo);
            } else {
                min = dfs(houses, cost, m, n, k - 1, houses[cur], cur + 1, memo);
            }
        } else {
            for(int c = 1; c <= n; c++) {
                int ret = c == preColor ? dfs(houses, cost, m, n, k, preColor, cur + 1, memo) :
                        dfs(houses, cost, m, n, k - 1, c, cur + 1, memo);

                if(ret != -1) {
                    min = min == -1 ? ret + cost[cur][c - 1] : Math.min(min, ret + cost[cur][c - 1]);
                }
            }
        }

        return memo[cur][preColor][k] = min;
    }
}
