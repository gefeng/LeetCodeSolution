package solution.weekly287;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Operations to Convert Time",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-287/problems/minimum-number-of-operations-to-convert-time/"
)
public class Q2224 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int convertTime(String current, String correct) {
        int ans = 0;
        int min1 = toMin(current);
        int min2 = toMin(correct);
        int d = min2 - min1;

        ans += d / 60;
        d = d % 60;
        ans += d / 15;
        d = d % 15;
        ans += d / 5;
        d = d % 5;
        ans += d;

        return ans;
    }

    private int toMin(String t) {
        return Integer.parseInt(t.substring(0, 2)) * 60 + Integer.parseInt(t.substring(3, 5));
    }
}
