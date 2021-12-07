package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Possible Bipartition",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/possible-bipartition/"
)
public class Q886 {
    /**
     * Time:  O(E + V)
     * Space: O(E + V)
     * */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] g = new List[n + 1];

        for(int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        for(int[] dis : dislikes) {
            int u = dis[0];
            int v = dis[1];
            g[u].add(v);
            g[v].add(u);
        }

        boolean ans = true;
        int[] side = new int[n + 1];
        Arrays.fill(side, -1);

        for(int i = 1; i <= n; i++) {
            if(side[i] != -1) {
                continue;
            }

            Queue<Integer> q = new ArrayDeque<>();
            q.offer(i);
            while(!q.isEmpty()) {
                int cur = q.poll();

                for(int nei : g[cur]) {
                    if(side[nei] == -1) {
                        side[nei] = side[cur] ^ 1;
                        q.offer(nei);
                    } else {
                        ans &= side[nei] != side[cur];
                    }
                }
            }
        }

        return ans;
    }
}
