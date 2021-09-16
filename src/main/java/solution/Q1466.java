package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Reorder Routes to Make All Paths Lead to the City Zero",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/"
)
public class Q1466 {
    /**
     * Time:  O(E + V)
     * Space: O(E + V)
     * */
    public int minReorder(int n, int[][] connections) {
        List<int[]>[] adj = new List[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] e : connections) {
            int u = e[0];
            int v = e[1];
            adj[u].add(new int[] {v, 1});
            adj[v].add(new int[] {u, -1});
        }

        return dfs(adj, 0, -1);
    }

    private int dfs(List<int[]>[] adj, int cur, int p) {
        int cnt = 0;

        for(int[] nei : adj[cur]) {
            if(nei[0] != p) {
                cnt += dfs(adj, nei[0], cur) + (nei[1] == 1 ? 1 : 0);
            }
        }

        return cnt;
    }
}
