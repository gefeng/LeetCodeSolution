package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Minimum Cost to Reach City With Discounts",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/minimum-cost-to-reach-city-with-discounts/"
)
public class Q2093 {
    /**
     * Time:  O(E * D * log(E * D))
     * Space: O(E + V + V * D)
     * */
    public int minimumCost(int n, int[][] highways, int discounts) {
        int ans = -1;
        List<int[]>[] g = new List[n];
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[][] best = new int[n][discounts + 1];

        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            Arrays.fill(best[i], Integer.MAX_VALUE);
        }

        for(int[] h : highways) {
            g[h[0]].add(new int[] {h[1], h[2]});
            g[h[1]].add(new int[] {h[0], h[2]});
        }

        pq.offer(new int[] {0, 0, 0});
        best[0][0] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int c = cur[1];
            int d = cur[2];

            if(x == n - 1) {
                ans = c;
                break;
            }

            if(c > best[x][d]) continue;

            for(int[] nei : g[x]) {
                int nx = nei[0];
                int nc = nei[1] + c;
                int nd = d + 1;
                if(nc < best[nx][d]) {
                    pq.offer(new int[] {nx, nc, d});
                    best[nx][d] = nc;
                }

                if(nd <= discounts && nei[1] / 2 + c < best[nx][nd]) {
                    pq.offer(new int[] {nx, nei[1] / 2 + c, nd});
                    best[nx][nd] = nei[1] / 2 + c;
                }
            }
        }

        return ans;
    }
}
