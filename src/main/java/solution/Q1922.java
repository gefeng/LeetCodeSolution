package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Good Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/count-good-numbers/"
)
public class Q1922 {
    /*
    * quick power O(logN)
    * x ^ n if n is odd -> x * x ^ (n - 1)
    * x ^ n if n is even -> x ^ (n / 2) * x ^ (n / 2)
    * */
    private final static int MOD = (int)1e9 + 7;
    public int countGoodNumbers(long n) {
        return (int)((pow(5, (n + 1) / 2) * pow(4, n / 2)) % MOD);
    }

    // quick power
    private long pow(long x, long y) {
        long res = 1;

        while(y != 0) {
            if((y & 1) == 1) {
                res = (res * x) % MOD;
            }
            y >>= 1;
            x = (x * x) % MOD;
        }

        return res;
    }
}
