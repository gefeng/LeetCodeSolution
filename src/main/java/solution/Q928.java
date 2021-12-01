package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Minimize Malware Spread II",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/minimize-malware-spread-ii/"
)
public class Q928 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int ans = -1;
        int n = graph.length;
        DSU dsu = new DSU(n);

        Arrays.sort(initial);

        boolean[] clean = new boolean[n];
        Arrays.fill(clean, true);
        for(int x : initial) {
            clean[x] = false;
        }
        for(int i = 0; i < n; i++) {
            if(!clean[i]) continue;
            for(int j = i + 1; j < n; j++) {
                if(!clean[j]) continue;
                if(graph[i][j] == 1) {
                    dsu.union(i, j);
                }
            }
        }

        Map<Integer, Set<Integer>> g = new HashMap<>();
        int[] cnt = new int[n];
        for(int u : initial) {
            for(int v = 0; v < n; v++) {
                if(clean[v] && graph[u][v] == 1) {
                    g.computeIfAbsent(u, k -> new HashSet<>()).add(dsu.find(v));
                }
            }

            if(g.get(u) != null) {
                for(int c : g.get(u)) {
                    cnt[c]++;
                }
            }
        }

        int max = 0;
        for(int u : initial) {
            int tot = 0;
            if(g.get(u) != null) {
                for(int c : g.get(u)) {
                    if(cnt[c] == 1) {
                        tot += dsu.size(c);
                    }
                }
            }
            if(tot > max) {
                max = tot;
                ans = u;
            }
        }

        return ans == -1 ? initial[0] : ans;
    }

    private class DSU {
        int[] p;
        int[] w;
        DSU(int n) {
            p = new int[n];
            w = new int[n];
            Arrays.fill(p, -1);
            Arrays.fill(w, 1);
        }

        int find(int i) {
            if(p[i] < 0) return i;
            return p[i] = find(p[i]);
        }

        void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) return;
            if(w[x] >= w[y]) {
                p[y] = x;
                w[x] += w[y];
            } else {
                p[x] = y;
                w[y] += w[x];
            }
        }

        int size(int i) {
            return w[find(i)];
        }
    }
}
