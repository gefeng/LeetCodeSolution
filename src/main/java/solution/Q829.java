package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Consecutive Numbers Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/consecutive-numbers-sum/"
)
public class Q829 {
    /**
     * Derive the formula:
     *  n = x + (x + 1) + (x + 2) + ... + (x + k - 1)
     *  n = kx + k(k - 1) / 2
     *  x = (2n - k(k - 1)) / 2k
     *  2n - k(k - 1) > 0 && (2n - k(k - 1)) % 2k == 0
     *
     * Time:  O(sqr(n))
     * Space: O(1)
     * */
    public int consecutiveNumbersSum(int n) {
        int ans = 1;

        long k = 2;
        while(true) {
            long x = (long)n * 2 - k * (k - 1);
            if(x <= 0) break;

            if(x % (2 * k) == 0) {
                ans++;
            }
            k++;
        }

        return ans;
    }
}
