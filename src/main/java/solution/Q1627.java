package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Graph Connectivity With Threshold",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/graph-connectivity-with-threshold/"
)
public class Q1627 {
    /**
     * Time:  O(N * logN + M) actually way more less because i * j hit bounds much faster than i * 2
     * Space: O(N)
     * */
    private class DSU {
        private int[] p;
        private int[] w;
        DSU(int n) {
            p = new int[n];
            w = new int[n];
            for(int i = 0; i < n; i++) {
                p[i] = i;
            }
        }

        int find(int i) {
            if(p[i] != i) {
                p[i] = find(p[i]);
            }
            return p[i];
        }

        void union(int i, int j) {
            int x = find(i);
            int y = find(j);

            if(x == y) {
                return;
            }

            if(w[x] == w[y]) {
                p[y] = x;
                w[x]++;
            } else if(w[x] > w[y]) {
                p[y] = x;
            } else {
                p[x] = y;
            }
        }
    }
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        DSU dsu = new DSU(n + 1);

        for(int i = threshold + 1; i <= n; i++) {
            for(int j = 2; i * j <= n; j++) {
                dsu.union(i, i * j);
            }
        }

        for(int[] q : queries) {
            int x = dsu.find(q[0]);
            int y = dsu.find(q[1]);
            if(x == y) {
                res.add(true);
            } else {
                res.add(false);
            }
        }

        return res;
    }
}
