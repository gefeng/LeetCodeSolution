package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Optimize Water Distribution in a Village",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/optimize-water-distribution-in-a-village/"
)
public class Q1168 {
    /**
     * Minimum spanning tree (MST) solved by Kruskal's Algorithm.
     * The only tricky is to convert wells to edges connected to a new house 0.
     * Only house 0 has well which simplify the problems to a standard MST problem.
     *
     * M: number of pipes
     * N: number of houses
     * Time:  O((M + N) * log(M + N))
     * Space: O(M + N)
     * */
    private class DSU {
        private int[] parent;
        private int[] weight;

        DSU(int n) {
            parent = new int[n];
            weight = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }

            return parent[i];
        }

        void union(int i, int j) {
            int x = find(i);
            int y = find(j);

            if(x == y) {
                return;
            }

            if(weight[x] == weight[y]) {
                parent[y] = x;
                weight[x]++;
            } else if(weight[x] > weight[y]) {
                parent[y] = x;
            } else {
                parent[x] = y;
            }
        }
    }
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int m = pipes.length;
        int res = 0;
        int[][] edges = new int[m + n][3];
        DSU dsu = new DSU(n + 1);

        for(int i = 0; i < n; i++) {
            edges[i] = new int[] {0, i + 1, wells[i]};
        }

        for(int i = 0; i < m; i++) {
            edges[i + n] = pipes[i];
        }

        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));

        for(int[] e : edges) {
            int h1 = e[0];
            int h2 = e[1];
            if(dsu.find(h1) != dsu.find(h2)) {
                dsu.union(h1, h2);
                res += e[2];
            }
        }

        return res;
    }
}
