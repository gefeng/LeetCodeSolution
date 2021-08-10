package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Ways to Split a String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/number-of-ways-to-split-a-string/"
)
public class Q1573 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numWays(String s) {
        int res = 0;
        int n = s.length();
        int total = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '1') {
                total++;
            }
        }

        if(total % 3 != 0) {
            return 0;
        }
        if(total == 0) {
            return (int)((long)(n - 1) * (n - 2) / 2 % MOD);
        }

        int cntZero = 0;
        int cntOne = 0;
        int i = 0;
        while(i < n && cntOne != total / 3) {
            if(s.charAt(i) == '1') {
                cntOne++;
            }
            i++;
        }

        while(i < n && s.charAt(i) == '0') {
            i++;
            cntZero++;
        }

        res = cntZero;
        cntZero = 0;
        cntOne = 0;
        while(i < n && cntOne != total / 3) {
            if(s.charAt(i) == '1') {
                cntOne++;
            }
            i++;
        }

        while(i < n && s.charAt(i) == '0') {
            i++;
            cntZero++;
        }

        return (int)((long)(res + 1) * (cntZero + 1) % MOD);
    }
}
