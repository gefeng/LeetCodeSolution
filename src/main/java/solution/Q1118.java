package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Days in a Month",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/number-of-days-in-a-month/"
)
public class Q1118 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int numberOfDays(int year, int month) {
        int[] dm = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 30};

        if(isLeap(year)) {
            dm[1] = 29;
        }

        return dm[month - 1];
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
