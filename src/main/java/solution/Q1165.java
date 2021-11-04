package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Single-Row Keyboard",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/biweekly-contest-7/problems/single-row-keyboard/"
)
public class Q1165 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int calculateTime(String keyboard, String word) {
        int n = word.length();
        int ans = 0;

        int pre = 0;
        for(int i = 0; i < n; i++) {
            char c = word.charAt(i);

            int cur = keyboard.indexOf(c);
            ans += Math.abs(cur - pre);

            pre = cur;
        }
        return ans;
    }
}
