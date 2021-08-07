package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Min Cost to Connect All Points",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/min-cost-to-connect-all-points/"
)
public class Q1584 {
    /**
     * Time:  O(N ^ 2 * logN)
     * Space: O(N ^ 2)
     * */
    private class DSU {
        int[] parent;
        int[] weight;
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

        boolean union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) {
                return false;
            }

            if(weight[x] == weight[y]) {
                parent[y] = x;
                weight[x]++;
            } else if(weight[x] > weight[y]) {
                parent[y] = x;
            } else {
                parent[x] = y;
            }

            return true;
        }
    }
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int res = 0;
        DSU dsu = new DSU(n);
        int[][] edges = new int[(n - 1) * n][3];

        for(int i = 0, k = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                int d = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges[k][0] = i;
                edges[k][1] = j;
                edges[k][2] = d;
                k++;
            }
        }

        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));

        for(int[] e : edges) {
            if(dsu.union(e[0], e[1])) {
                res += e[2];
            }
        }

        return res;
    }
}
