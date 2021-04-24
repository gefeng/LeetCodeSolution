package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Days Between Two Dates",
        difficulty = QDifficulty.EASY,
        tag = QTag.NONE,
        url = "https://leetcode.com/problems/number-of-days-between-two-dates/"
)
public class Q1360 {
    /*
    * leap year
    * */
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(getDays(date1) - getDays(date2));
    }

    private int getDays(String date) {
        String[] ymd = date.split("-");
        int year = Integer.parseInt(ymd[0]);
        int month = Integer.parseInt(ymd[1]);
        int day = Integer.parseInt(ymd[2]);

        int days = 0;
        for(int i = 1971; i < year; i++) {
            days += isLeapYear(i) ? 366 : 365;
        }

        for(int i = 1; i < month; i++) {
            if(i == 2) {
                days += isLeapYear(year) ? 29 : 28;
            } else if(i <= 7) {
                days += i % 2 == 0 ? 30 : 31;
            } else {
                days += i % 2 == 0 ? 31 : 30;
            }
        }

        days += day;
        return days;
    }

    private boolean isLeapYear(int year) {
        if(year % 4 != 0)
            return false;
        if(year % 100 != 0)
            return true;
        if(year % 400 != 0)
            return false;
        return true;
    }
}
