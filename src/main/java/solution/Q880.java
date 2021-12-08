package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Decoded String at Index",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/decoded-string-at-index/"
)
public class Q880 {
    /**
     * If string "abc" repeated 4 times, "abc abc abc abc" and k = 8
     * It's equivalent to find (k % 3)th character.
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public String decodeAtIndex(String s, int k) {
        int n = s.length();
        long[] dp = new long[n];

        for(int i = 0; i < n; i++) {
            if(Character.isLetter(s.charAt(i))) {
                dp[i] = i == 0 ? 1 : dp[i - 1] + 1;
            } else {
                dp[i] = dp[i - 1] * (s.charAt(i) - '0');
            }
        }

        k--;
        for(int i = n - 1; i >= 0; i--) {
            if(Character.isLetter(s.charAt(i))) {
                if(k == dp[i] - 1) {
                    return s.charAt(i) + "";
                }
            } else {
                k = (int)(k % dp[i - 1]);
            }
        }

        return "";
    }
}
