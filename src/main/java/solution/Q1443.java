package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Minimum Time to Collect All Apples in a Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/"
)
public class Q1443 {
    /**
     * Time:  O(V)
     * Space: O(V + E)
     * */
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        int m = edges.length;
        List<Integer>[] adj = new List[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        return m * 2 - dfs(adj, 0, -1, hasApple)[1] * 2;
    }

    private int[] dfs(List<Integer>[] adj, int x, int p, List<Boolean> hasApple) {
        int cntApple = hasApple.get(x) ? 1 : 0;
        int cntRemove = 0;

        for(int nei : adj[x]) {
            if(nei != p) {
                int[] ret = dfs(adj, nei, x, hasApple);
                cntApple += ret[0];
                cntRemove += ret[1];
            }
        }

        if(cntApple == 0) {
            cntRemove = p == -1 ? cntRemove : cntRemove + 1;
        }

        return new int[] {cntApple, cntRemove};
    }
}
