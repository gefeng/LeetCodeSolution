package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Number of Islands II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/number-of-islands-ii/"
)
public class Q305 {
    private class DisjointSet {
        private int[] parent;
        private int[] weight;
        DisjointSet(int n) {
            parent = new int[n];
            weight = new int[n];

            Arrays.fill(parent, -1);
        }

        int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        void union(int i, int j) {
            int setA = find(i);
            int setB = find(j);
            if(setA != setB) {
                if(weight[setA] >= weight[setB]) {
                    parent[setB] = setA;
                    weight[setA]++;
                } else {
                    parent[setA] = setB;
                    weight[setB]++;
                }
            }
        }

        boolean isValid(int i) {
            return parent[i] != -1;
        }

        void setParent(int i) {
            parent[i] = i;
        }
    }

    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        int countIsland = 0;
        DisjointSet ds = new DisjointSet(m * n);

        for(int[] pos : positions) {
            int idx = pos[0] * n + pos[1];
            if(ds.isValid(idx)) {
                ans.add(countIsland);
                continue;
            }

            ds.setParent(idx);
            countIsland++;

            for(int[] dir : DIRECTIONS) {
                int nr = pos[0] + dir[0];
                int nc = pos[1] + dir[1];
                if(nr >= 0 && nc >= 0 && nr < m && nc < n) {
                    int nIdx = nr * n + nc;
                    if(ds.isValid(nIdx) && ds.find(idx) != ds.find(nIdx)) {
                        ds.union(idx, nIdx);
                        countIsland--;
                    }
                }
            }

            ans.add(countIsland);
        }

        return ans;
    }
}
