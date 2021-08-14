package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Ugly Number III",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/ugly-number-iii/"
)
public class Q1201 {
    /**
     * Time:  O(logN * logN)
     * Space: O(1)
     * */
    public int nthUglyNumber(int n, int a, int b, int c) {
        int lo = 1;
        int hi = (int)2e9;
        int res = 0;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // count how many numbers are divisible by a, b or c less or equal to mid.
            if(count(mid, a, b, c) >= n) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return res;
    }

    private int count(int n, int a, int b, int c) {
        return (int)(n / a + n / b + n / c - n / lcm(a, b) - n / lcm(a, c) - n / lcm(b, c) + n / lcm(a, lcm(b, c)));
    }

    private long gcd(long a, long b) {
        if(a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    private long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
}
