package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Most Stones Removed with Same Row or Column",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/"
)
public class Q947 {
    public int removeStones(int[][] edges) {
        return dfsSolution(edges);
    }

    private int dfsSolution(int[][] stones) {
        int ans = 0;
        int n = stones.length;
        int groups = 0;
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(stones, i, visited);
                groups++;
            }
        }

        return n - groups;
    }

    private void dfs(int[][] stones, int currStone, boolean[] visited) {
        visited[currStone] = true;
        for(int i = 0; i < stones.length; i++) {
            if(!visited[i] && (stones[currStone][0] == stones[i][0] || stones[currStone][1] == stones[i][1])) {
                dfs(stones, i, visited);
            }
        }
    }

    private int unionFindSolution(int[][] stones) {
        int ans = 0;
        int n = stones.length;
        Set<Integer> groups = new HashSet<>();
        int[] parent = new int[n];
        for(int i = 0; i < n; i++)
            parent[i] = i;

        for(int i = 0; i < n; i++) {
            int[] stoneA = stones[i];
            for(int j = i; j < n; j++) {
                int[] stoneB = stones[j];
                if(stoneA[0] == stoneB[0] || stoneA[1] == stoneB[1]) {
                    int setA = find(parent, i);
                    int setB = find(parent, j);
                    if(setA != setB)
                        parent[setA] = setB;
                }
            }
        }

        for(int i = 0; i < n; i++)
            groups.add(find(parent, i));

        ans = n - groups.size();
        return ans;
    }

    private int find(int[] parent, int i) {
        if(parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }
}
