package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Substrings With Only 1s",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/number-of-substrings-with-only-1s/"
)
public class Q1513 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numSub(String s) {
        int n = s.length();
        int res = 0;

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '1') {
                cnt++;
                res = (res + cnt) % MOD;
            } else {
                cnt = 0;
            }
        }

        return res;
    }
}
