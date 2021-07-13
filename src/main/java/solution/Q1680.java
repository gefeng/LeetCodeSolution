package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Concatenation of Consecutive Binary Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/"
)
public class Q1680 {
    private static final int MOD = (int)1e9 + 7;
    public int concatenatedBinary(int n) {
        long res = 0;
        int len = 0;

        for(int i = 1; i <= n; i++) {
            len = (i & (i - 1)) == 0 ? len + 1 : len;
            res = ((res << len) + i) % MOD;
        }

        return (int)res;
    }
}
