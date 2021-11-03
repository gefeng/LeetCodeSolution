package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Day of the Week",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/day-of-the-week/"
)
public class Q1185 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public String dayOfTheWeek(int day, int month, int year) {
        String[] WD = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int[] MD = new int[] {31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};

        int days = 0;
        for(int i = 1971; i < year; i++) {
            days += isLeap(i) ? 366 : 365;
        }

        for(int i = 1; i < month; i++) {
            days += MD[i];
            days += (i == 2 && isLeap(year)) ? 1 : 0;
        }

        days += day - 1;

        return WD[(days + 5) % 7];
    }

    /**
     * Algo from wiki
     * */
    private boolean isLeap(int y) {
        if(y % 4 != 0) {
            return false;
        }

        if(y % 100 != 0) {
            return true;
        }
        if(y % 400 != 0) {
            return false;
        }
        return true;
    }
}
