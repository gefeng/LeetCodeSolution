package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "The Time When the Network Becomes Idle",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/the-time-when-the-network-becomes-idle/"
)
public class Q2039 {
    /**
     * Time:  O(E + V)
     * Space: O(E + V)
     * */
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        int ans = 0;

        List<Integer>[] adj = new List[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        Queue<Integer> q = new ArrayDeque<>();
        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        q.offer(0);
        dist[0] = 0;
        int d = 0;
        while(!q.isEmpty()) {
            int len = q.size();
            for(int i = 0; i < len; i++) {
                int cur = q.poll();

                for(int nei : adj[cur]) {
                    if(dist[nei] == -1) {
                        q.offer(nei);
                        dist[nei] = d + 1;
                    }
                }
            }
            d++;
        }

        for(int i = 1; i < n; i++) {
            int extra = (dist[i] * 2 + patience[i] - 1) / patience[i] - 1;

            ans = Math.max(ans, dist[i] * 2 + extra * patience[i]);
        }

        return ans + 1;
    }
}
