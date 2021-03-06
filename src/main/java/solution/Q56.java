package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Merge Intervals",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/merge-intervals/"
)
public class Q56 {
    /*
        Easy to solve could have follow up like don't use extra memory
    */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });

        int l = 0;
        int r = 0;
        while(r < intervals.length) {
            if(intervals[r][0] <= intervals[l][1]) {
                intervals[l][0] = intervals[l][0];
                intervals[l][1] = Math.max(intervals[l][1], intervals[r][1]);
                r++;
            } else {
                l++;
                intervals[l][0] = intervals[r][0];
                intervals[l][1] = intervals[r][1];
            }
        }

        return Arrays.copyOfRange(intervals, 0, l + 1);
    }
}
