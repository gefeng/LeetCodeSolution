package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Non-overlapping Intervals",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/non-overlapping-intervals/"
)
public class Q435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length < 2)
            return 0;

        int count = 0;
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });

        int lo = intervals[0][0];
        int hi = intervals[0][1];
        for(int i = 1, prev = 0; i < intervals.length; i++) {
            if(intervals[i][0] < hi) {
                count++;
                hi = Math.min(hi, intervals[i][1]);
            }
            else
                hi = intervals[i][1];
        }

        return count;
    }
}
