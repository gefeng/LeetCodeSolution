package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Binary String With Substrings Representing 1 To N",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/binary-string-with-substrings-representing-1-to-n/"
)
public class Q1016 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean queryString(String s, int n) {
        int m = s.length();

        if(n > m * m) {
            return false;
        }

        boolean[] seen = new boolean[n + 1];
        for(int i = 0; i < m; i++) {
            int v = 0;
            for(int j = i; j < m; j++) {
                v = v << 1 | s.charAt(j) - '0';
                if(v > n) {
                    break;
                }
                seen[v] = true;
            }
        }

        for(int i = 1; i <= n; i++) {
            if(!seen[i]) {
                return false;
            }
        }

        return true;
    }
}
