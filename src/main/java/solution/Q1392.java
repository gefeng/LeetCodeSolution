package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Happy Prefix",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/longest-happy-prefix/"
)
public class Q1392 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String longestPrefix(String s) {
        int n = s.length();
        int[] dp = new int[n];

        for(int i = 0, j = 1; j < n; j++) {
            if(s.charAt(i) == s.charAt(j)) {
                dp[j] = ++i;
            } else {
                if(i > 0) {
                    i = dp[i - 1];
                    j--;
                }
            }
        }

        return s.substring(0, dp[n - 1]);
    }
}
