package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Perform String Shifts",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/perform-string-shifts/"
)
public class Q1427 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public String stringShift(String s, int[][] shift) {
        int n = s.length();
        int tot = 0;
        for(int[] sh : shift) {
            tot += sh[0] == 0 ? -sh[1] : sh[1];
        }

        tot = tot % n;
        if(tot == 0) return s;
        if(tot < 0) return s.substring(-tot, n) + s.substring(0, -tot);

        return s.substring(n - tot, n) + s.substring(0, n - tot);
    }
}
