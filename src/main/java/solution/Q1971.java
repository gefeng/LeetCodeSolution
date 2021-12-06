package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Find if Path Exists in Graph",
        difficulty = QDifficulty.EASY,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/find-if-path-exists-in-graph/"
)
public class Q1971 {
    /**
     * Time:  O(E)
     * Space: O(N)
     * */
    public boolean validPath(int n, int[][] edges, int start, int end) {
        DSU dsu = new DSU(n);
        for(int[] e : edges) {
            dsu.union(e[0], e[1]);
        }

        return dsu.find(start) == dsu.find(end);
    }

    private class DSU {
        int[] p;
        int[] w;
        DSU(int n) {
            p = new int[n];
            w = new int[n];
            Arrays.fill(p, -1);
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
    }
}
