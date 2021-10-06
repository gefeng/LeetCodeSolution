package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Frog Position After T Seconds",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/frog-position-after-t-seconds/"
)
public class Q1377 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<Integer>[] adj = new List[n + 1];

        for(int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] e : edges) {
            int u = e[0], v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        return dfs(adj, 1, t, target, new boolean[n + 1]);
    }

    private double dfs(List<Integer>[] adj, int v, int t, int target, boolean[] seen) {
        if(t == 0) {
            return v == target ? 1.0 : 0.0;
        }
        if(v == target) {
            for(int nei : adj[v]) {
                if(!seen[nei]) {
                    return 0.0;
                }
            }
            return 1.0;
        }

        seen[v] = true;

        int sz = adj[v].size();

        int cntSeen = 0;
        double pro = 0.0;
        for(int nei : adj[v]) {
            if(seen[nei]) {
                cntSeen++;
                continue;
            }

            double ret = dfs(adj, nei, t - 1, target, seen);
            if(ret != 0) {
                pro = ret;
            }
        }

        if(pro == 0) {
            return 0;
        }

        return pro * (1.0 / (sz - cntSeen));
    }
}
