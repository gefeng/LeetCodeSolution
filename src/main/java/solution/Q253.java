package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Meeting Rooms II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/meeting-rooms-ii/"
)
public class Q253 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> pQueue = new PriorityQueue<>();

        for(int[] interval : intervals) {
            if(!pQueue.isEmpty() && interval[0] >= pQueue.peek())
                pQueue.poll();
            pQueue.offer(interval[1]);
        }

        return pQueue.size();
    }

    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    private int sweepLineSol(int[][] intervals) {
        int res = 0;
        Map<Integer, Integer> map = new TreeMap<>();

        for(int[] i : intervals) {
            map.put(i[0], map.getOrDefault(i[0], 0) + 1);
            map.put(i[1], map.getOrDefault(i[1], 0) - 1);
        }

        int cnt = 0;
        for(int v : map.keySet()) {
            cnt += map.get(v);
            res = Math.max(res, cnt);
        }
        return res;
    }
}
