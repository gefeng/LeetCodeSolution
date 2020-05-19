package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Student Attendance Record I",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/student-attendance-record-i/"
)
public class Q551 {
    public boolean checkRecord(String s) {
        int absentCount = 0;
        int lateCount = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'A')
                absentCount++;

            if(s.charAt(i) == 'L')
                lateCount++;
            else {
                lateCount = 0;
            }

            if(absentCount > 1 || lateCount > 2)
                return false;
        }
        return true;
    }
}
