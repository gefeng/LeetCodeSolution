package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Number of Restricted Paths From First to Last Node",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/"
)
public class Q1786 {
    private static final int MOD = (int)1e9 + 7;
    public int countRestrictedPaths(int n, int[][] edges) {
        List<int[]>[] graph = new List[n + 1];
        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            graph[u] = graph[u] == null ? new ArrayList<>() : graph[u];
            graph[v] = graph[v] == null ? new ArrayList<>() : graph[v];
            graph[u].add(new int[] {v, w});
            graph[v].add(new int[] {u, w});
        }


        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        shortestDistToLast(graph, d, n);

        return dfs(graph, d, 1, n, new Integer[n + 1]);
    }

    // dijkstra shortest path to n
    private void shortestDistToLast(List<int[]>[] graph, int[] d, int n) {
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        minHeap.offer(new int[] {n, 0});
        d[n] = 0;
        while(!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int node = curr[0];
            int dist = curr[1];

            for(int[] nei : graph[node]) {
                int nd = dist + nei[1];
                if(nd < d[nei[0]]) {
                    minHeap.offer(new int[] { nei[0], nd });
                    d[nei[0]] = nd;
                }
            }
        }
    }

    private int dfs(List<int[]>[] graph, int[] d, int node, int n, Integer[] memo) {
        if(node == n) {
            return 1;
        }

        if(memo[node] != null) {
            return memo[node];
        }

        long cntPath = 0;
        for(int[] nei : graph[node]) {
            if(d[nei[0]] < d[node]) {
                cntPath += dfs(graph, d, nei[0], n, memo);
            }
        }

        return memo[node] = (int)(cntPath % MOD);
    }
}
