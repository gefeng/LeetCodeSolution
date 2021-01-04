package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.LinkedList;
import java.util.List;

@Problem(
        title = "Insert Interval",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/insert-interval/"
)
public class Q57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> newIntervals = new LinkedList<>();
        int i = 0;
        int n = intervals.length;
        int newStart = 0;
        int newEnd = 0;

        while(i < n && intervals[i][1] < newInterval[0]) {
            newIntervals.add(intervals[i++]);
        }

        newStart = i == n ? newInterval[0] : Math.min(intervals[i][0], newInterval[0]);

        while(i < n && intervals[i][0] <= newInterval[1]) {
            i++;
        }

        newEnd = i == 0 ? newInterval[1] : Math.max(intervals[i - 1][1], newInterval[1]);
        newIntervals.add(new int[] {newStart, newEnd});

        while(i < n) {
            newIntervals.add(intervals[i++]);
        }

        int[][] ans = new int[newIntervals.size()][2];
        i = 0;
        for(int[] interval : newIntervals) {
            ans[i++] = interval;
        }

        return ans;
    }
}
