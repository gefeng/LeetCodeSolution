package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Maximum Path Quality of a Graph",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/maximum-path-quality-of-a-graph/"
)
public class Q2065 {
    /**
     * Each node has at most 4 neighbors which means max_degree = 4.
     * maxTime <= 100 and time for each edge >= 10 which denotes maximum depth will be 10.
     * Therefore the amount of recursion call is bounded to 4^10 which leads to a brute force solution.
     *
     * Time:  O(4 ^ 10)
     * Space: O(N)
     * */
    int ans = 0;
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        List<int[]>[] adj = new List[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            adj[u].add(new int[] {v, w});
            adj[v].add(new int[] {u, w});
        }

        dfs(adj, values, maxTime, 0, 0, 0, new int[n]);

        return ans;
    }

    private void dfs(List<int[]>[] adj, int[] values, int maxTime, int cur, int t, int sum, int[] cnt) {
        cnt[cur] += 1;
        if(cnt[cur] == 1) {
            sum += values[cur];
        }

        if(cur == 0) {
            ans = Math.max(ans, sum);
        }

        for(int[] nei : adj[cur]) {
            if(nei[1] + t <= maxTime) {
                dfs(adj, values, maxTime, nei[0], t + nei[1], sum, cnt);
            }
        }

        cnt[cur] -= 1;
    }
}
