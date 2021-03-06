package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Meeting Rooms",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/meeting-rooms/"
)
public class Q252 {
    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals.length < 2)
            return true;

        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });

        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i - 1][1] > intervals[i][0])
                return false;
        }
        return true;
    }
}
