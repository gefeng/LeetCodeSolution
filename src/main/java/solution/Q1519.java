package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Number of Nodes in the Sub-Tree With the Same Label",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/"
)
public class Q1519 {
    /**
     * Time:  O(V + E)
     * Space: O(V + E)
     * */
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        int[] res = new int[n];
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

        dfs(adj, 0, -1, labels, res);

        return res;
    }

    private int[] dfs(List<Integer>[] adj, int cur, int parent, String labels, int[] res) {
        int[] ret = new int[26];

        for(int nei : adj[cur]) {
            if(nei != parent) {
                int[] cnt = dfs(adj, nei, cur, labels, res);

                for(int i = 0; i < 26; i++) {
                    ret[i] += cnt[i];
                }
            }
        }

        int label = labels.charAt(cur) - 'a';
        ret[label]++;
        res[cur] = ret[label];

        return ret;
    }
}
