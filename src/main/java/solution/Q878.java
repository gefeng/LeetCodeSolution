package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Nth Magical Number",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/nth-magical-number/"
)
public class Q878 {
    /**
     * Time:  O(log(N * min(a, b)))
     * Space: O(1)
     * */
    private static final long MOD = (long)1e9 + 7;
    public int nthMagicalNumber(int n, int a, int b) {
        long lo = 0;
        long hi = (long)1e15;
        long ans = 0;
        int lcm = a * b / gcd(a, b);

        while(lo <= hi) {
            long mid = hi + lo >> 1;
            long x = mid / a + mid / b - mid / lcm;

            if(x >= n) {
                hi = mid - 1;
                ans = mid;
            } else {
                lo = mid + 1;
            }
        }
        return (int)(ans % MOD);
    }

    private int gcd(int a, int b) {
        if(a == 0) return b;
        return gcd(b % a, a);
    }
}
