package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Problem(
        title = "Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/"
)
public class Q1489 {
    /**
     * Time:  O(E * V)
     * Space: O(E + V)
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
    }
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());

        int[][] e = new int[m][4];

        for(int i = 0; i < m; i++) {
            e[i] = new int[] {edges[i][0], edges[i][1], edges[i][2], i};
        }

        Arrays.sort(e, Comparator.comparingInt(a -> a[2]));

        int minw = getMSTExclusive(n, e, -1);

        for(int i = 0; i < m; i++) {
            int w = getMSTExclusive(n, e, i);

            if(w > minw) {
                ans.get(0).add(e[i][3]);
            } else {
                w = getMSTInclusive(n, e, i);
                if(w == minw) {
                    ans.get(1).add(e[i][3]);
                }
            }
        }

        return ans;
    }

    private int getMSTExclusive(int n, int[][] edges, int ei) {
        int w = 0;
        int m = edges.length;
        DSU dsu = new DSU(n);

        for(int i = 0; i < m; i++) {
            if(i == ei) {
                continue;
            }
            int[] e = edges[i];

            if(dsu.union(e[0], e[1])) {
                w += e[2];
            }
        }

        int p = dsu.find(0);
        for(int i = 1; i < n; i++) {
            if(dsu.find(i) != p) {
                w = Integer.MAX_VALUE;
                break;
            }
        }

        return w;
    }

    private int getMSTInclusive(int n, int[][] edges, int ii) {
        int w = 0;
        int m = edges.length;
        DSU dsu = new DSU(n);

        dsu.union(edges[ii][0], edges[ii][1]);
        w = edges[ii][2];

        for(int i = 0; i < m; i++) {
            if(i == ii) {
                continue;
            }
            int[] e = edges[i];

            if(dsu.union(e[0], e[1])) {
                w += e[2];
            }
        }

        int p = dsu.find(0);
        for(int i = 1; i < n; i++) {
            if(dsu.find(i) != p) {
                w = Integer.MAX_VALUE;
                break;
            }
        }

        return w;
    }
}
