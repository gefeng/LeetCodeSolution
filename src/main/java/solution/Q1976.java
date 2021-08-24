package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Number of Ways to Arrive at Destination",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/"
)
public class Q1976 {
    /**
     * Time:  O((V + E) * logV)
     * Space: O(V + E)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int countPaths(int n, int[][] roads) {
        List<int[]>[] adj = new List[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] r : roads) {
            int u = r[0];
            int v = r[1];
            int w = r[2];
            adj[u].add(new int[] {v, w});
            adj[v].add(new int[] {u, w});
        }


        Queue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        long[] best = new long[n];
        long[] ways = new long[n];

        Arrays.fill(best, Long.MAX_VALUE);

        pq.offer(new long[] {0, 0});
        best[0] = 0;
        ways[0] = 1;

        while(!pq.isEmpty()) {
            long[] node = pq.poll();
            int u = (int)node[0];
            long t = node[1];

            if(t > best[u]) {
                continue;
            }

            for(int[] nei : adj[u]) {
                long time = t + nei[1];
                if(time == best[nei[0]]) {
                    ways[nei[0]] = (ways[nei[0]] + ways[u]) % MOD;
                }
                if(time < best[nei[0]]) {
                    pq.offer(new long[] {nei[0], time});
                    best[nei[0]] = time;
                    ways[nei[0]] = ways[u];
                }
            }
        }

        return (int)ways[n - 1];
    }
}
