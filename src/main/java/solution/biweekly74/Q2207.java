package solution.biweekly74;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximize Number of Subsequences in a String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/biweekly-contest-74/problems/maximize-number-of-subsequences-in-a-string/"
)
public class Q2207 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public long maximumSubsequenceCount(String text, String pattern) {
        long ans = 0;
        int n = text.length();

        long cnt1 = 0;
        long cnt2 = 0;

        for(int i = 0; i < n; i++) {
            char c = text.charAt(i);
            if(c == pattern.charAt(0)) cnt1++;
            if(c == pattern.charAt(1)) cnt2++;
        }

        long max = Math.max(cnt1, cnt2);
        for(int i = 0; i < n; i++) {
            char c = text.charAt(i);

            if(c == pattern.charAt(1)) {
                cnt2--;
            }
            if(c == pattern.charAt(0)) {
                ans += cnt2;
            }
        }

        return ans + max;
    }
}
