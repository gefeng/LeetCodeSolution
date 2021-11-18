package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Smallest Integer Divisible by K",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/smallest-integer-divisible-by-k/"
)
public class Q1015 {
    /**
     * Since the answer may not fit in long, we can just save remainder.
     * 111(next) = 11(cur) * 10 + 1
     *
     * Time:  O(K)
     * Space: O(1)
     * */
    public int smallestRepunitDivByK(int k) {
        int rem = 0;

        for(int i = 0; i < k; i++) {
            rem = (rem * 10 + 1) % k;
            if(rem == 0) {
                return i + 1;
            }
        }
        return -1;
    }
}
