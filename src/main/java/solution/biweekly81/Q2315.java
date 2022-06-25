package solution.biweekly81;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Asterisks",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/biweekly-contest-81/problems/count-asterisks/"
)
public class Q2315 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int countAsterisks(String s) {
        int n = s.length();
        int ans = 0;
        int st = 0;

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c == '|') {
                st ^= 1;
            }

            if(c == '*' && st == 0) {
                ans += 1;
            }
        }

        return ans;
    }
}
