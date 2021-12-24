package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Find Eventual Safe States",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/find-eventual-safe-states/"
)
public class Q802 {
    /**
     * Time:  O(E + V + V * logV)
     * Space: O(E + V + V)
     * */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> ans = new ArrayList<>();
        int n = graph.length;

        List<Integer>[] g = new List[n];
        int[] indegree = new int[n];
        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++) {
            for(int x : graph[i]) {
                g[x].add(i);
                indegree[i]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            ans.add(cur);

            for(int nei : g[cur]) {
                indegree[nei]--;
                if(indegree[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        Collections.sort(ans);

        return ans;
    }
}
