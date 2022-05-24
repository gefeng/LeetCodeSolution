package solution.biweekly78;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Substring With Largest Variance",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/biweekly-contest-78/problems/substring-with-largest-variance/"
)
public class Q2272 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int largestVariance(String s) {
        int n = s.length();
        int ans = 0;
        boolean[] seen = new boolean[26];
        for(int i = 0; i < n; i++) {
            seen[s.charAt(i) - 'a'] = true;
        }

        for(char c1 = 'a'; c1 <= 'z'; c1++) {
            for(char c2 = 'a'; c2 <= 'z'; c2++) {
                if(c1 == c2 || !seen[c1 - 'a'] || !seen[c2 - 'a']) {
                    continue;
                }

                boolean has = false;
                int cur = 0;
                int max = 0;
                for(int i = 0; i < n; i++) {
                    if(s.charAt(i) == c1) {

                        cur++;
                    } else if(s.charAt(i) == c2) {
                        has = true;
                        cur--;
                    }

                    if(cur < 0) {
                        cur = 0;
                        has = false;
                    }

                    if(has) {
                        max = Math.max(max, cur);
                    } else {
                        max = Math.max(max, cur - 1);
                    }

                }
                ans = Math.max(ans, max);

                has = false;
                cur = 0;
                max = 0;
                for(int i = 0; i < n; i++) {
                    if(s.charAt(i) == c2) {
                        cur++;
                    } else if(s.charAt(i) == c1) {
                        has = true;
                        cur--;
                    }

                    if(cur < 0) {
                        cur = 0;
                        has = false;
                    }

                    if(has) {
                        max = Math.max(max, cur);
                    } else {
                        max = Math.max(max, cur - 1);
                    }
                }
                ans = Math.max(ans, max);
            }
        }

        return ans;
    }
}
