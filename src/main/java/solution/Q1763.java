package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Nice Substring",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/longest-nice-substring/"
)
public class Q1763 {
    public String longestNiceSubstring(String s) {
        int n = s.length();
        if(n < 2) {
            return "";
        }

        boolean[] seen = new boolean[52];
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c >= 'a' && c <= 'z') {
                seen[c - 'a'] = true;
            } else {
                seen[c - 'A' + 26] = true;
            }
        }

        String res = s;
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if((c >= 'a' && c <= 'z' && seen[c - 'a' + 26]) ||
                    (c >= 'A' && c <= 'Z' && seen[c - 'A'])) {
                continue;
            }

            String l = longestNiceSubstring(s.substring(0, i));
            String r = longestNiceSubstring(s.substring(i + 1, n));

            return l.length() >= r.length() ? l : r;
        }

        return res;
    }
}
