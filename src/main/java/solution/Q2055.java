package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Plates Between Candles",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/plates-between-candles/"
)
public class Q2055 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int m = queries.length;
        int[] ans = new int[m];

        int[] l = new int[n];
        int[] r = new int[n];
        int[] preSum = new int[n + 1];

        for(int i = 0, close = -1; i < n; i++) {
            char c = s.charAt(i);
            if(c == '|') {
                close = i;
            }
            l[i] = close;
        }

        for(int i = n - 1, close = -1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c == '|') {
                close = i;
            }
            r[i] = close;
        }

        for(int i = 1; i <= n; i++) {
            if(s.charAt(i - 1) == '*') {
                preSum[i] = preSum[i - 1] + 1;
            } else {
                preSum[i] = preSum[i - 1];
            }
        }

        for(int i = 0; i < m; i++) {
            int[] q = queries[i];
            int lc = -1;
            int rc = -1;
            if(s.charAt(q[0]) == '*') {
                lc = r[q[0]] > q[1] ? -1 : r[q[0]];
            } else {
                lc = q[0];
            }
            if(s.charAt(q[1]) == '*') {
                rc = l[q[1]] < q[0] ? -1 : l[q[1]];
            } else {
                rc = q[1];
            }

            if(lc != -1 && rc != -1) {
                ans[i] = preSum[rc + 1] - preSum[lc];
            }
        }

        return ans;
    }
}
