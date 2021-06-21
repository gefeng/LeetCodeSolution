package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Count Pairs Of Nodes",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/count-pairs-of-nodes/"
)
public class Q1782 {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        Map<Integer, Integer>[] graph = new Map[n + 1];
        int[] degree = new int[n + 1];
        int[] sortedDegree = new int[n + 1];

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];

            int a = Math.min(u, v);
            int b = Math.max(u, v);
            graph[a] = graph[a] == null ? new HashMap<>() : graph[a];
            graph[a].put(b, graph[a].getOrDefault(b, 0) + 1);

            degree[u]++;
            degree[v]++;
            sortedDegree[u]++;
            sortedDegree[v]++;
        }

        Arrays.sort(sortedDegree);

        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++) {
            int q = queries[i];

            int l = 1;
            int r = n;
            while(l < r) {
                if(sortedDegree[l] + sortedDegree[r] > q) {
                    ans[i] += (r - l);
                    r--;
                } else {
                    l++;
                }
            }

            for(int a = 1; a <= n; a++) {
                if(graph[a] == null) {
                    continue;
                }
                for(int b : graph[a].keySet()) {
                    if(degree[a] + degree[b] > q &&
                            degree[a] + degree[b] - graph[a].get(b) <= q) {
                        ans[i]--;
                    }
                }
            }
        }

        return ans;
    }
}
