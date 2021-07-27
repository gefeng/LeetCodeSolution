package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Rank Transform of a Matrix",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/rank-transform-of-a-matrix/"
)
public class Q1632 {
    /**
     * Time:  O(M * N * (M + N))
     * Space: O(M * N * (M + N))
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

        int find(int x) {
            if(parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int setA = find(x);
            int setB = find(y);

            if(setA == setB) {
                return;
            }

            if(weight[setA] == weight[setB]) {
                parent[setB] = setA;
                weight[setA]++;
            } else if(weight[setA] > weight[setB]) {
                parent[setB] = setA;
            } else {
                parent[setA] = setB;
            }
        }
    }
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[m][n];

        Map<Integer, DSU> dsuMap = new HashMap<>();
        Map<Integer, List<int[]>> posMap = new TreeMap<>();

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                DSU dsu = dsuMap.computeIfAbsent(matrix[i][j], k -> new DSU(m + n));
                dsu.union(i, j + m);

                List<int[]> l = posMap.computeIfAbsent(matrix[i][j], k -> new ArrayList<>());
                l.add(new int[] {i, j});
            }
        }

        int[] rRank = new int[m];
        int[] cRank = new int[n];

        for(Map.Entry<Integer, List<int[]>> e : posMap.entrySet()) {
            int v = e.getKey();
            List<int[]> cells = e.getValue();

            DSU dsu = dsuMap.get(v);

            Map<Integer, Integer> rankMap = new HashMap<>();

            for(int[] c : cells) {
                int parent = dsu.find(c[0]);
                int rank = Math.max(rRank[c[0]], cRank[c[1]]) + 1;

                rankMap.put(parent, Math.max(rankMap.getOrDefault(parent, 0), rank));
            }

            for(int[] c : cells) {
                int parent = dsu.find(c[0]);
                int rank = rankMap.get(parent);
                res[c[0]][c[1]] = rank;
                rRank[c[0]] = rank;
                cRank[c[1]] = rank;
            }
        }

        return res;
    }
}
