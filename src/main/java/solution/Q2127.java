package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Maximum Employees to Be Invited to a Meeting",
        difficulty = QDifficulty.HARD,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/"
)
public class Q2127 {
    /**
     * We can solve this by only using topological sort.
     * The trick is to save depth for each nodes during topological sort which helps to easily calculate
     * 2 nodes cycles (a tie) + their longest arms.
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maximumInvitations(int[] favorite) {
        int ans = 0;
        int n = favorite.length;
        int[] indegree = new int[n];
        int[] depth = new int[n];  // save max depth for each node connects to cycle
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            indegree[favorite[i]]++;
            depth[i] = 1;
        }

        Queue<int[]> q = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            if(indegree[i] == 0) {
                q.offer(new int[] {i, 1});
            }
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            //System.out.println(cur[0]);
            visited[cur[0]] = true;
            depth[cur[0]] = Math.max(depth[cur[0]], cur[1]);

            int nxt = favorite[cur[0]];
            depth[nxt] = Math.max(depth[nxt], cur[1] + 1);

            if(--indegree[nxt] == 0) {
                q.offer(new int[] {nxt, cur[1] + 1});
            }
        }

        int maxCycle = 0;
        int sumTie = 0;
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;

            int j = i;
            int len = 0;
            while(!visited[j]) {
                visited[j] = true;
                j = favorite[j];
                len++;
            }

            if(len > 2) {
                maxCycle = Math.max(maxCycle, len);
            } else {
                sumTie += depth[i] + depth[favorite[i]];
            }
        }

        return Math.max(maxCycle, sumTie);
    }
}
