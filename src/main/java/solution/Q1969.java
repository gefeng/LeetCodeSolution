package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Non-Zero Product of the Array Elements",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/minimum-non-zero-product-of-the-array-elements/"
)
public class Q1969 {
    /**
     * Didn't solve during contest. Actually example 3 gives huge hint.
     * we can derive the formula,
     * res = (2 ^ p - 1) * pow(2 ^ p - 2, (2 ^ p - 2) / 2)
     * Again cursed math problem by Java, can be solved by fast power in logN time,
     * but need power mod version to handle overflow.
     *
     * Time:  O(logN)
     * Space: O(1)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int minNonZeroProduct(int p) {
        long last = getPow(2, p) - 1;
        return (int)((last % MOD) * getPowMod(last - 1, (last - 1) / 2) % MOD);
    }

    private long getPow(long x, long y) {
        long res = 1;

        while(y != 0) {
            if(y % 2 == 1) {
                res = (res * x);
            }
            x *= x;
            y /= 2;
        }

        return res;
    }

    private long getPowMod(long x, long y) {
        long res = 1;

        while(y != 0) {
            if(y % 2 == 1) {
                res = (res % MOD) * (x % MOD) % MOD;
            }
            x = (x % MOD) * (x % MOD) % MOD;
            y /= 2;
        }

        return res;
    }
}
