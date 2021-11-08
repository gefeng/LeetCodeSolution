package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "Parallel Courses",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/parallel-courses/"
)
public class Q1136 {
    /**
     * Time:  O(E + V)
     * Space: O(E + V)
     * */
    public int minimumSemesters(int n, int[][] relations) {
        List<Integer>[] adj = new List[n + 1];
        int[] indegree = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] e : relations) {
            int u = e[0];
            int v = e[1];
            adj[u].add(v);
            indegree[v]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }

        int cntS = 0;
        int cntC = 0;
        while(!q.isEmpty()) {
            int sz = q.size();
            for(int i = 0; i < sz; i++) {
                int cur = q.poll();
                cntC += 1;
                for(int nei : adj[cur]) {
                    indegree[nei] -= 1;
                    if(indegree[nei] == 0) {
                        q.offer(nei);
                    }
                }
            }
            cntS += 1;
        }

        return cntC == n ? cntS : -1;
    }
}
