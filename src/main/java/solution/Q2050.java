package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "Parallel Courses III",
        difficulty = QDifficulty.HARD,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/parallel-courses-iii/"
)
public class Q2050 {
    /**
     * Save maximum time needed before start the current course.
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minimumTime(int n, int[][] relations, int[] time) {
        int ans = 0;
        List<Integer>[] adj = new List[n + 1];
        int[] indegree = new int[n + 1];
        int[] maxTime = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] r : relations) {
            adj[r[0]].add(r[1]);
            indegree[r[1]]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            int t = time[cur - 1] + maxTime[cur];
            ans = Math.max(ans, t);

            for(int nei : adj[cur]) {
                indegree[nei]--;
                maxTime[nei] = Math.max(maxTime[nei], t);

                if(indegree[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        return ans;
    }
}
