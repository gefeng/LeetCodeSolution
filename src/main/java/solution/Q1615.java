package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Maximal Network Rank",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/maximal-network-rank/"
)
public class Q1615 {
    /**
     * Time:  O(E + V ^ 2)
     * Space: O(E + V)
     * */
    public int maximalNetworkRank(int n, int[][] roads) {
        int res = 0;
        Set<Integer>[] adj = new Set[n];

        for(int[] r : roads) {
            int u = r[0];
            int v = r[1];

            adj[u] = adj[u] == null ? new HashSet<>() : adj[u];
            adj[v] = adj[v] == null ? new HashSet<>() : adj[v];
            adj[u].add(v);
            adj[v].add(u);
        }

        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                Set<Integer> neis1 = adj[i];
                Set<Integer> neis2 = adj[j];

                int rank = (neis1 == null ? 0 : neis1.size()) + (neis2 == null ? 0 : neis2.size());
                if(neis1 != null && neis1.contains(j)) {
                    rank--;
                }

                res = Math.max(res, rank);
            }
        }

        return res;
    }
}
