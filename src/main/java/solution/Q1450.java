package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Students Doing Homework at a Given Time",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/number-of-students-doing-homework-at-a-given-time/"
)
public class Q1450 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int n = startTime.length;
        int ans = 0;

        for(int i = 0; i < n; i++) {
            if(queryTime >= startTime[i] && queryTime <= endTime[i]) {
                ans++;
            }
        }
        return ans;
    }
}
