package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Output Contest Matches",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/output-contest-matches/"
)
public class Q544 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public String findContestMatch(int n) {
        String[] s = new String[n];
        for(int i = 0; i < n; i++) {
            s[i] = Integer.toString(i + 1);
        }

        while(n != 1) {
            int len = s.length;
            String[] next = new String[len / 2];
            for(int i = 0; i < len / 2; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("(").append(s[i]).append(",").append(s[len - 1 - i]).append(")");
                next[i] = sb.toString();
            }

            n /= 2;
            s = next;
        }

        return s[0];
    }
}
