package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Count Subtrees With Max Distance Between Cities",
        difficulty = QDifficulty.HARD,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/count-subtrees-with-max-distance-between-cities/"
)
public class Q1617 {
    /**
     * Time:  O(2 ^ N * N)
     * Space: O(N + E)
     * */
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int[] res = new int[n - 1];
        Set<Integer>[] adj = new Set[n + 1];
        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            adj[u] = adj[u] == null ? new HashSet<>() : adj[u];
            adj[v] = adj[v] == null ? new HashSet<>() : adj[v];
            adj[u].add(v);
            adj[v].add(u);
        }

        Map<Integer, Integer> distMap = new HashMap<>();

        for(int b = 2; b < (1 << n); b++) {
            int start = getStartPoint(n, b);

            Set<Integer> visited = new HashSet<>();
            dfs(adj, n, start, b, visited);

            if(!isSubtree(n, b, visited)) {
                continue;
            }

            int[] dist = dfsDist(adj, n, start, b, new HashSet<>());

            distMap.put(dist[1], distMap.getOrDefault(dist[1], 0) + 1);
        }

        for(int d = 1; d <= n - 1; d++) {
            res[d - 1] = distMap.getOrDefault(d, 0);
        }

        return res;
    }

    private int getStartPoint(int n, int mask) {
        int start = 0;
        for(int i = 0; i < n; i++) {
            if(((1 << i) & mask) != 0) {
                start = i + 1;
                break;
            }
        }
        return start;
    }

    private boolean isSubtree(int n, int mask, Set<Integer> visited) {
        for(int i = 0; i < n; i++) {
            if(((1 << i) & mask) == 0) {
                continue;
            }

            if(!visited.contains(i + 1)) {
                return false;
            }
        }

        return true;
    }

    private void dfs(Set<Integer>[] adj, int n, int v, int mask, Set<Integer> visited) {
        visited.add(v);

        for(int u = 0; u < n; u++) {
            if(((1 << u) & mask) == 0) {
                continue;
            }

            if(adj[v].contains(u + 1) && !visited.contains(u + 1)) {
                dfs(adj, n, u + 1, mask, visited);
            }
        }
    }

    private int[] dfsDist(Set<Integer>[] adj, int n, int v, int mask, Set<Integer> visited) {
        visited.add(v);

        List<int[]> dist = new ArrayList<>();
        int maxDist = 0;
        int maxPath1 = 0;
        int maxPath2 = 0;

        for(int u = 0; u < n; u++) {
            if(((1 << u) & mask) == 0) {
                continue;
            }

            if(adj[v].contains(u + 1) && !visited.contains(u + 1)) {
                int[] d = dfsDist(adj, n, u + 1, mask, visited);

                maxDist = Math.max(maxDist, d[1]);

                if(d[0] + 1 > maxPath1) {
                    maxPath2 = maxPath1;
                    maxPath1 = d[0] + 1;
                } else if(d[0] + 1 > maxPath2) {
                    maxPath2 = d[0] + 1;
                }
            }
        }

        return new int[] { maxPath1, Math.max(maxDist, maxPath1 + maxPath2) };
    }
}
