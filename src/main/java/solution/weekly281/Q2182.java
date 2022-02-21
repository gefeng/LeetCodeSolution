package solution.weekly281;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Construct String With Repeat Limit",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-281/problems/construct-string-with-repeat-limit/"
)
public class Q2182 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] cnt = new int[26];
        int n = s.length();

        for(int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        while(true) {
            int len = sb.length();
            for(int i = 25; i >= 0; i--) {
                if(cnt[i] == 0) continue;

                char c = (char)(i + 'a');

                if(sb.length() == 0 || sb.charAt(sb.length() - 1) != c) {
                    int t = 0;
                    if(sb.length() != 0) {
                        char last = sb.charAt(sb.length() - 1);
                        if(cnt[last - 'a'] != 0 && last > c) {
                            t = 1;
                        } else {
                            t = Math.min(cnt[i], repeatLimit);
                        }
                    } else {
                        t = Math.min(cnt[i], repeatLimit);
                    }

                    for(int j = 0; j < t; j++) {
                        sb.append(c);
                    }
                    cnt[i] -= t;
                }

                if(sb.length() > len) break;
            }

            if(len == sb.length()) {
                break;
            }
        }

        return sb.toString();
    }
}
