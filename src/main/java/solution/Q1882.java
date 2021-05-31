package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Process Tasks Using Servers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/process-tasks-using-servers/"
)
public class Q1882 {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int m = servers.length;
        int n = tasks.length;
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        PriorityQueue<int[]> workingServers = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        Queue<Integer> queue = new ArrayDeque<>();
        int[] ans = new int[n];

        for(int i = 0; i < m; i++) {
            freeServers.add(new int[] {i, servers[i], 0});
        }
        for(int i = 0; i < n; i++) {
            queue.offer(i);
        }

        int time = 0;
        int idx = 0;
        while(!queue.isEmpty()) {
            while(!workingServers.isEmpty() && workingServers.peek()[2] <= time) {
                int[] server = workingServers.poll();
                server[2] = 0;
                freeServers.offer(server);
            }

            // if some servers are available and tasks are ready
            while(!freeServers.isEmpty() && !queue.isEmpty() && queue.peek() <= time) {
                int[] server = freeServers.poll();
                ans[idx++] = server[0];   // assign server id
                server[2] = time + tasks[queue.poll()]; // next available time
                workingServers.offer(server);
            }

            // aggressively forward time if no more free servers available
            time = freeServers.isEmpty() ? time = workingServers.peek()[2] : time + 1;
        }

        return ans;
    }
}
