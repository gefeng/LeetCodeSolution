package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Course Schedule IV",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/course-schedule-iv/"
)
public class Q1462 {
    /**
     * Time:  O(max(V + E * V, M))
     * Space: O(V ^ 2)
     * */
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        List<Integer>[] adj = new List[numCourses];
        int[] indegree = new int[numCourses];
        Set<Integer>[] prereq = new Set[numCourses];

        for(int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
            prereq[i] = new HashSet<>();
        }

        for(int[] e : prerequisites) {
            int u = e[0];
            int v = e[1];
            adj[u].add(v);
            indegree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int nei : adj[cur]) {
                indegree[nei]--;
                prereq[nei].add(cur);
                prereq[nei].addAll(prereq[cur]);
                if(indegree[nei] == 0) {
                    queue.offer(nei);
                }
            }
        }

        for(int[] q : queries) {
            ans.add(prereq[q[1]].contains(q[0]));
        }

        return ans;
    }
}
