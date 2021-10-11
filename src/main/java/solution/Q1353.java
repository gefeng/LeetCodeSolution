package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Maximum Number of Events That Can Be Attended",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/"
)
public class Q1353 {
    /**
     * Time:  O(D * logN)
     * Space: O(N)
     * */
    public int maxEvents(int[][] events) {
        int ans = 0;
        int n = events.length;

        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for(int d = 1, i = 0; d < 100001; d++) {
            while(i < n && events[i][0] == d) {
                pq.offer(events[i++]);
            }

            while(!pq.isEmpty() && pq.peek()[1] < d) {
                pq.poll();
            }

            if(!pq.isEmpty()) {
                pq.poll();
                ans++;
            }
        }

        return ans;
    }
}
