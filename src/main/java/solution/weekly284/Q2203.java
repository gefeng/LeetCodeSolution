package solution.weekly284;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Minimum Weighted Subgraph With the Required Paths",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/contest/weekly-contest-284/problems/minimum-weighted-subgraph-with-the-required-paths/"
)
public class Q2203 {
    /**
     * Time:  O(E * logE)
     * Space: O(E + V)
     * */
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<int[]>[] g = new List[n];
        List<int[]>[] rg = new List[n];
        long ans = Long.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            rg[i] = new ArrayList<>();
        }

        for(int[] e : edges) {
            g[e[0]].add(new int[] {e[1], e[2]});
            rg[e[1]].add(new int[] {e[0], e[2]});
        }

        long[] fscr1 = dijkstra(n, g, src1);
        long[] fscr2 = dijkstra(n, g, src2);
        long[] fdest = dijkstra(n, rg, dest);

        for(int i = 0; i < n; i++) {
            if(fscr1[i] != Long.MAX_VALUE && fscr2[i] != Long.MAX_VALUE && fdest[i] != Long.MAX_VALUE) {
                ans = Math.min(ans, fscr1[i] + fscr2[i] + fdest[i]);
            }
        }

        return ans == Long.MAX_VALUE ? -1 : ans;
    }

    private long[] dijkstra(int n, List<int[]>[] g, int src) {
        Queue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        long[] best = new long[n];

        Arrays.fill(best, Long.MAX_VALUE);

        pq.offer(new long[] {src, 0});
        best[src] = 0;

        while(!pq.isEmpty()) {
            long[] cur = pq.poll();
            int x = (int)cur[0];
            long w = cur[1];

            if(w > best[x]) continue;

            for(int[] nei : g[x]) {
                int nx = nei[0];
                long nw = w + nei[1];

                if(nw < best[nx]) {
                    best[nx] = nw;
                    pq.offer(new long[] {nx, nw});
                }
            }
        }

        return best;
    }
}
