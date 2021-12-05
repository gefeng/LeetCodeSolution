package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Valid Arrangement of Pairs",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/valid-arrangement-of-pairs/"
)
public class Q2097 {
    /**
     * Time:  O(E + V)
     * Space: O(E + V)
     * */
    public int[][] validArrangement(int[][] pairs) {
        int n = pairs.length;
        List<int[]> path = eulerianPath(pairs);
        int[][] ans = new int[n][2];

        for(int i = 0; i < n; i++) {
            ans[n - i - 1] = path.get(i);
        }

        return ans;
    }

    private List<int[]> eulerianPath(int[][] pairs) {
        Map<Integer, Integer> degree = new HashMap<>();
        Map<Integer, Queue<Integer>> g = new HashMap<>();

        for(int[] p : pairs) {
            int u = p[0];
            int v = p[1];
            degree.put(u, degree.getOrDefault(u, 0) + 1);
            degree.put(v, degree.getOrDefault(v, 0) - 1);
            g.computeIfAbsent(u, k -> new ArrayDeque<>()).offer(v);
        }

        int st = -1;
        for(int x : degree.keySet()) {
            if(degree.get(x) == 1) {
                st = x;
                break;
            }
        }

        st = st == -1 ? pairs[0][0] : st; // if st == -1 then it's a eulerian cycle, start with arbitrary v.

        List<int[]> path = new ArrayList<>();
        dfs(g, st, path);
        return path;
    }

    // Postorder traverse the graph, remove edges visited.
    private void dfs(Map<Integer, Queue<Integer>> g, int cur, List<int[]> path) {
        if(g.containsKey(cur)) {
            Queue<Integer> q = g.get(cur);

            while(!q.isEmpty()) {
                int nei = q.poll();
                dfs(g, nei, path);
                path.add(new int[] {cur, nei});
            }
        }
    }
}
