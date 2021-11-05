package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Day of the Year",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/day-of-the-year/"
)
public class Q1154 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int dayOfYear(String date) {
        String[] s = date.split("-");
        int[] dom = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int y = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int d = Integer.parseInt(s[2]);

        boolean isLeap = isLeap(y);

        if(isLeap) {
            dom[1] = 29;
        }

        int days = 0;
        for(int i = 1; i < m; i++) {
            days += dom[i - 1];
        }

        return  days + d;
    }

    private boolean isLeap(int y) {
        if(y % 4 != 0) {
            return false;
        } else if(y % 100 != 0) {
            return true;
        } else if(y % 400 != 0) {
            return false;
        } else {
            return true;
        }
    }
}
