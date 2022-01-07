package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Network Delay Time",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/network-delay-time/"
)
public class Q743 {
    /**
     * Time:  O(E * V)
     * Space: O(E + V)
     * */
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] g = new List[n + 1];

        for(int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        for(int[] t : times) {
            g[t[0]].add(new int[] {t[1], t[2]});
        }

        Queue<int[]> q = new ArrayDeque<>();
        int[] best = new int[n + 1];

        Arrays.fill(best, Integer.MAX_VALUE);

        q.offer(new int[] {k, 0});
        best[k] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int t = cur[1];

            for(int[] nei : g[x]) {
                int nt = nei[1] + t;
                if(best[nei[0]] > nt) {
                    q.offer(new int[] {nei[0], t + nei[1]});
                    best[nei[0]] = nt;
                }
            }
        }

        int ans = 0;
        for(int i = 1; i <= n; i++) {
            if(best[i] == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, best[i]);
        }

        return ans;
    }
}
