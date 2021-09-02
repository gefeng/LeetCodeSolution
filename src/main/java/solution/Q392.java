package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Is Subsequence",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/is-subsequence/"
)
public class Q392 {
    /**
     * Follow up: if there are lots of s, what to improve.
     * we can sort each character's positions and do binary search.
     * The time complexity will be O(N) for preprocessing plus O(K * M * logN) for
     * all the queries. Compared to Two Pointers solution which has O(K * (M + N))
     * when N is quite large and M is relatively small, the binary search will be
     * optimal.
     *
     * Time:  O(M + N)
     * Space: O(1)
     * */
    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        int i = 0;
        int j = 0;
        while(i < m && j < n) {
            if(s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }

        return i == m;
    }
}
