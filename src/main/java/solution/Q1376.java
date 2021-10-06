package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "Time Needed to Inform All Employees",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/time-needed-to-inform-all-employees/"
)
public class Q1376 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int ans = 0;
        List<Integer>[] adj = new List[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++) {
            int m = manager[i];
            if(m != -1) {
                adj[m].add(i);
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {headID, 0});
        while(!queue.isEmpty()) {
            int sz = queue.size();

            for(int i = 0; i < sz; i++) {
                int[] cur = queue.poll();

                if(!adj[cur[0]].isEmpty()) {
                    for(int nei : adj[cur[0]]) {
                        queue.offer(new int[] {nei, cur[1] + informTime[cur[0]]});
                    }
                }

                ans = Math.max(ans, cur[1]);
            }
        }

        return ans;
    }
}
