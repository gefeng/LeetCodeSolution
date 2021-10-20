package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Delete Tree Nodes",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/delete-tree-nodes/"
)
public class Q1273 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        List<Integer>[] adj = new List[nodes];
        for(int i = 0; i < nodes; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < nodes; i++) {
            if(parent[i] == -1) {
                continue;
            }
            adj[parent[i]].add(i);
        }

        return dfs(adj, 0, value)[1];
    }

    private int[] dfs(List<Integer>[] adj, int cur, int[] value) {
        int sum = value[cur];
        int cnt = 1;

        for(int nei : adj[cur]) {
            int[] ret = dfs(adj, nei, value);

            sum += ret[0];
            cnt += ret[1];
        }

        if(sum == 0) {
            cnt = 0;
        }

        return new int[] {sum, cnt};
    }
}
