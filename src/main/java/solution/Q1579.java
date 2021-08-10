package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove Max Number of Edges to Keep Graph Fully Traversable",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/"
)
public class Q1579 {
    /**
     * Time:  O(E)
     * Space: O(N)
     * */
    private class DSU {
        int[] p;
        int[] w;
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

        boolean union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) {
                return false;
            }

            if(w[x] == w[y]) {
                p[y] = x;
                w[x]++;
            } else if(w[x] > w[y]) {
                p[y] = x;
            } else {
                p[x] = y;
            }

            return true;
        }

        int cntGroup() {
            int cnt = 0;
            for(int i = 1; i < p.length; i++) {
                int x = find(i);
                if(x == i) {
                    cnt++;
                }
            }

            return cnt;
        }
    }
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int res = 0;
        DSU dsu1 = new DSU(n + 1);
        DSU dsu2 = new DSU(n + 1);

        for(int[] e : edges) {
            int t = e[0], u = e[1], v = e[2];
            if(t == 1 || t == 2) {
                continue;
            }

            if(!dsu1.union(u, v)) {
                res++;
            } else {
                dsu2.union(u, v);
            }
        }

        for(int[] e : edges) {
            int t = e[0], u = e[1], v = e[2];
            if(t == 2 || t == 3) {
                continue;
            }

            if(!dsu1.union(u, v)) {
                res++;
            }
        }

        for(int[] e : edges) {
            int t = e[0], u = e[1], v = e[2];
            if(t == 1 || t == 3) {
                continue;
            }

            if(!dsu2.union(u, v)) {
                res++;
            }
        }

        boolean isConnected1 = dsu1.cntGroup() == 1 ? true : false;
        boolean isConnected2 = dsu2.cntGroup() == 1 ? true : false;

        return isConnected1 && isConnected2 ? res : -1;
    }
}
