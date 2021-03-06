package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.PriorityQueue;

@Problem(
        title = "Meeting Rooms II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/meeting-rooms-ii/"
)
public class Q253 {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });

        PriorityQueue<Integer> pQueue = new PriorityQueue<>();

        for(int[] interval : intervals) {
            if(!pQueue.isEmpty() && interval[0] >= pQueue.peek())
                pQueue.poll();
            pQueue.offer(interval[1]);
        }

        return pQueue.size();
    }
}
