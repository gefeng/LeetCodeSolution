package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

@Problem(
        title = "Shortest Path Visiting All Nodes",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/shortest-path-visiting-all-nodes/"
)
public class Q847 {
    /**
     * state of a path:
     *  (current node, visited nodes(bitmask), distance so far(or cost))
     *
     * Time:  O(2 ^ N * N ^ 2)
     * Space: O(2 ^ N * N)
     * */
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int ans = Integer.MAX_VALUE;

        Queue<int[]> q = new ArrayDeque<>();
        int[][] dp = new int[n][1 << n];

        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            q.offer(new int[] {i, 1 << i, 0});
            dp[i][1 << i] = 0;
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int mask = cur[1];
            int d = cur[2];

            if(mask == (1 << n) - 1) {
                ans = d;
                break;
            }

            for(int nei : graph[node]) {
                int nmask = mask | (1 << nei);
                if(dp[nei][nmask] > d + 1) {
                    q.offer(new int[] {nei, nmask, d + 1});
                    dp[nei][nmask] = d + 1;
                }
            }
        }

        return ans;
    }
}
