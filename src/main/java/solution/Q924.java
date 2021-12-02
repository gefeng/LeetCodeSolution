package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimize Malware Spread",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/minimize-malware-spread/"
)
public class Q924 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        int ans = -1;
        DSU dsu = new DSU(n);

        Arrays.sort(initial);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(graph[i][j] == 1) {
                    dsu.union(i, j);
                }
            }
        }

        // count island
        int[] cnt = new int[n];
        for(int x : initial) {
            cnt[dsu.find(x)]++;
        }


        int max = 0;
        for(int x : initial) {
            if(cnt[dsu.find(x)] == 1) {
                int sz = dsu.size(x);
                if(sz > max) {
                    max = sz;
                    ans = x;
                }
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
