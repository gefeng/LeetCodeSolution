package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Minimum Cost to Connect Two Groups of Points",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-cost-to-connect-two-groups-of-points/"
)
public class Q1595 {
    /**
     * For each node on the right, connect to any of the left node.
     * Use a bitmask to represent connected nodes on the left.
     * If all nodes from right are connected, connect unconnected left nodes
     * to right using best edge (cheapest).
     *
     * Time:  O(M * N * 2 ^ N)
     * Space: O(M * N)
     * */
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size();
        int n = cost.get(0).size();
        int[] rBest = new int[n];
        int[][] c = new int[m][n];

        Arrays.fill(rBest, Integer.MAX_VALUE);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                c[i][j] = cost.get(i).get(j);
                rBest[j] = Math.min(rBest[j], c[i][j]);
            }
        }

        return dfs(c, rBest, 0, 0, new Integer[m + 1][1 << n]);
    }

    private int dfs(int[][] c, int[] rBest, int l, int state, Integer[][] memo) {
        int m = c.length;
        int n = c[0].length;

        if (memo[l][state] != null) {
            return memo[l][state];
        }

        int cost;
        if (l == m) {
            cost = 0;
            for (int r = 0; r < n; r++) {
                cost += ((1 << r) & state) != 0 ? 0 : rBest[r];
            }
        } else {
            cost = Integer.MAX_VALUE;
            for (int r = 0; r < n; r++) {
                cost = Math.min(cost, dfs(c, rBest, l + 1, state | (1 << r), memo) + c[l][r]);
            }
        }

        return memo[l][state] = cost;
    }
}
