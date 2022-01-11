package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Window Subsequence",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-window-subsequence/"
)
public class Q727 {
    /**
     * nxt[i][j] saves next index of character j starting from i.
     * 'nxt' array can accelerate subsequence matching.
     *
     * Time:  O(N * M)
     * Space: O(N)
     * */
    public String minWindow(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] nxt = new int[n + 1][26];
        int best = n + 1;
        int l = -1;
        for(int i = 0; i < 26; i++) {
            nxt[n][i] = n;
        }

        for(int i = n - 1; i >= 0; i--) {
            for(int j = 0; j < 26; j++) {
                nxt[i][j] = nxt[i + 1][j];
            }
            nxt[i][s1.charAt(i) - 'a'] = i;
        }


        for(int i = 0; i < n; i++) {
            if(s1.charAt(i) == s2.charAt(0)) {
                int j = i;
                boolean found = true;
                for(int k = 0; k < m; k++) {
                    int nj = nxt[j][s2.charAt(k) - 'a'];

                    if(nj == n) {
                        found = false;
                        break;
                    }

                    j = nj + 1;
                }
                if(found && j - i < best) {
                    best = j - i;
                    l = i;
                }
            }
        }

        if(l == -1 ) return "";

        return s1.substring(l, l + best);
    }
}
