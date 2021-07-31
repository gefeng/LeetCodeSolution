package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum One Bit Operations to Make Integers Zero",
        difficulty = QDifficulty.HARD,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/"
)
public class Q1611 {
    /**
     * n = 2 -> 2 ^ 2 - 1
     * n = 4 -> 2 ^ 3 - 1
     * n = 8 -> 2 ^ 4 - 1
     * if n = 2 ^ (k - 1) -> 2 ^ k - 1  k is number of effective bits of n
     *
     * 1xxxxx -> 110000 -> 010000 -> 10000 -> 0
     *                  1               2^5-1
     *
     * f(n) -> 110000
     * f(n ^ 110000) -> 110000 ^ 110000 = 0 ?
     *
     * Time:  O(1)
     * Space: O(1)
     * */
    public int minimumOneBitOperations(int n) {
        if(n == 0) {
            return 0;
        }

        int i = 1;
        while((i << 1) <= n) {
            i <<= 1;
        }

        return minimumOneBitOperations(n ^ i ^ (i >> 1)) + i;
    }
}
