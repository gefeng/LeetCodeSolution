package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Sum of Distance in Tree",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/sum-of-distances-in-tree/"
)
public class Q834 {
    /**
     * f(x) denotes sum of the distance between the ith node in the tree and all other nodes
     * g(x) denotes sum of the distance between the ith node in the tree and all its decendant
     *
     * if we have two nodes x and y are neighbors,
     * f(x) = f(x - y) + f(y - x) + #y
     * f(y) = f(x - y) + f(y - x) + #x
     * where f(x - y) and f(y - x) means two new trees by removing the edge between x and y.
     * => f(x) - f(y) = #y - #x => f(x) = f(y) + #y - #x
     *
     * g(x) = g(x.l) + #x.l + g(x.r) + #x.r
     * we can use g(x) to calculate the sum of the distance of a tree rooted at an arbitrary node.
     *
     * Time:  O(E + V)
     * Space: O(E + V)
     * */
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[] res = new int[n];
        int[] cnt = new int[n];
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

        int[] g = dfsCnt(adj, 0, -1, cnt);
        res[0] = g[1];

        dfsSum(adj, 0, g[1], -1, cnt, res);

        return res;
    }

    private int[] dfsCnt(List<Integer>[] adj, int cur, int parent, int[] cnt) {
        int[] ret = new int[] {1, 0};

        for(int nei : adj[cur]) {
            if(nei == parent) {
                continue;
            }

            int[] info = dfsCnt(adj, nei, cur, cnt);
            ret[0] += info[0];
            ret[1] += info[1] + info[0];
        }

        cnt[cur] = ret[0];

        return ret;
    }

    private void dfsSum(List<Integer>[] adj, int cur, int sum, int parent, int[] cnt, int[] res) {
        for(int nei : adj[cur]) {
            if(nei == parent) {
                continue;
            }

            int n = adj.length;
            int numx = cnt[cur] - cnt[nei] + n - cnt[cur];
            int numy = cnt[nei];
            int sumNei = sum + numx - numy;
            res[nei] = sumNei;

            dfsSum(adj, nei, sumNei, cur, cnt, res);
        }
    }
}
