package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Remove Covered Intervals",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/remove-covered-intervals/"
)
public class Q1288 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int removeCoveredIntervals(int[][] intervals) {
        int n = intervals.length;
        int cnt = 0;

        Arrays.sort(intervals, (a, b) -> {
            if(a[0] == b[0]) {
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int[] pre = intervals[0];
        for(int i = 1; i < n; i++) {
            int[] cur = intervals[i];
            if(cur[1] <= pre[1]) {
                cnt++;
            } else {
                pre = cur;
            }
        }

        return n - cnt;
    }
}
