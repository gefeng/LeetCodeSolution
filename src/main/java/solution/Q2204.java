package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "Distance to a Cycle in Undirected Graph",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/distance-to-a-cycle-in-undirected-graph/"
)
public class Q2204 {
    public int[] distanceToCycle(int n, int[][] edges) {
        int[] ans = new int[n];
        List<Integer>[] g = new List[n];
        int[] degree = new int[n];

        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        for(int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
            degree[e[0]]++;
            degree[e[1]]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            if(degree[i] == 1) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int nei : g[cur]) {
                if(--degree[nei] == 1) {
                    q.offer(nei);
                }
            }
        }

        q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(degree[i] > 1) {

                q.offer(i);
                visited[i] = true;
            }
        }

        int d = 0;
        while(!q.isEmpty()) {
            int sz = q.size();
            for(int i = 0; i < sz; i++) {
                int cur = q.poll();

                ans[cur] = d;

                for(int nei : g[cur]) {
                    if(!visited[nei]) {
                        visited[nei] = true;
                        q.offer(nei);
                    }
                }
            }
            d++;
        }

        return ans;
    }
}
