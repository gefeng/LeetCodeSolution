package solution.biweekly75;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Ways to Select Buildings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/biweekly-contest-75/problems/number-of-ways-to-select-buildings/"
)
public class Q2222 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public long numberOfWays(String s) {
        return count(s, '1') + count(s, '0');
    }

    private long count(String s, char t) {
        long ans = 0;
        int n = s.length();

        int[] dpL = new int[n];
        int[] dpR = new int[n];

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c != t) {
                cnt++;
            } else {
                dpL[i] = cnt;
            }
        }

        cnt = 0;
        for(int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c != t) {
                cnt++;
            } else {
                dpR[i] = cnt;
            }
        }

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(c == t) {
                ans += dpL[i] * dpR[i];
            }
        }

        return ans;
    }
}
