package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Shortest Path with Alternating Colors",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/shortest-path-with-alternating-colors/"
)
public class Q1129 {
    /**
     * Note: instead of do bfs for each node we can bfs once and save the shortest path for each node.
     *
     * Time:  O(E + V)
     * Space: O(E + V)
     * */
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        int[] ans = new int[n];

        List<int[]>[] adj = new List[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] re : red_edges) {
            int u = re[0];
            int v = re[1];
            adj[u].add(new int[] {v, 0});
        }
        for(int[] be : blue_edges) {
            int u = be[0];
            int v = be[1];
            adj[u].add(new int[] {v, 1});
        }

        int[][] dp = new int[n][2];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        dp[0][1] = 0;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        q.offer(new int[] {0, 1});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int[] nei : adj[cur[0]]) {
                if(nei[1] != cur[1] && dp[nei[0]][nei[1]] > dp[cur[0]][cur[1]] + 1) {
                    dp[nei[0]][nei[1]] = dp[cur[0]][cur[1]] + 1;
                    q.offer(nei);
                }
            }
        }

        for(int i = 0; i < n; i++) {
            ans[i] = Math.min(dp[i][0], dp[i][1]);
            ans[i] = ans[i] == Integer.MAX_VALUE ? -1 : ans[i];
        }

        return ans;
    }
}
