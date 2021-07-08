package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Checking Existence of Edge Length Limited Paths",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/"
)
public class Q1697 {
    /*
        sort edge list
        sort queries
    */
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
            if(x != y) {
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
    }
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int l = edgeList.length;
        int m = queries.length;
        boolean[] ans = new boolean[m];
        DSU dsu = new DSU(n);

        int[][] q = new int[m][4];
        for(int i = 0; i < m; i++) {
            int[] query = queries[i];
            q[i] = new int[] {query[0], query[1], query[2], i};
        }

        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));
        Arrays.sort(q, Comparator.comparingInt(a -> a[2]));

        for(int i = 0, j = 0; i < m; i++) {
            int[] query = q[i];
            while(j < l && edgeList[j][2] < query[2]) {
                dsu.union(edgeList[j][0], edgeList[j][1]);
                j++;
            }

            if(dsu.find(query[0]) == dsu.find(query[1])) {
                ans[query[3]] = true;
            }
        }

        return ans;
    }
}
