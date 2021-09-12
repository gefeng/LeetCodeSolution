package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Reachable Nodes In Subdivided Graph",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/"
)
public class Q882 {
    /**
     * Time:  O(E * logV)
     * Space: O(E + V)
     * */
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        int ans = 0;
        List<int[]>[] adj = new List[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            adj[u].add(new int[] {v, w});
            adj[v].add(new int[] {u, w});
        }

        int[] dist = new int[n];
        int[][] used = new int[n][n];
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        Arrays.fill(dist, Integer.MAX_VALUE);

        minHeap.offer(new int[] {0, 0});
        dist[0] = 0;
        while(!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();

            for(int[] nei : adj[cur[0]]) {
                int d = cur[1] + nei[1] + 1;

                if(d <= maxMoves && d < dist[nei[0]]) {
                    minHeap.offer(new int[] {nei[0], d});
                    dist[nei[0]] = d;
                    used[cur[0]][nei[0]] = nei[1];
                } else {
                    if(d > maxMoves) {
                        used[cur[0]][nei[0]] = Math.max(used[cur[0]][nei[0]], maxMoves - cur[1]);
                    } else {
                        used[cur[0]][nei[0]] = nei[1];
                    }
                }
            }
        }

        for(int i = 0; i < n; i++) {
            ans = dist[i] != Integer.MAX_VALUE ? ans + 1 : ans;

            for(int[] nei : adj[i]) {
                ans += Math.min(nei[1], used[i][nei[0]] + used[nei[0]][i]);
                used[i][nei[0]] = 0;
                used[nei[0]][i] = 0;
            }
        }

        return ans;
    }
}
