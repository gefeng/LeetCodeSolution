package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Two Integers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/sum-of-two-integers/"
)
public class Q371 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int getSum(int a, int b) {
        return magicSol(a, b);
    }

    private int bitByBitSol(int a, int b) {
        int carry = 0;
        int res = 0;
        for(int i = 0; i < 32; i++) {
            int x = ((1 << i) & a) == 0 ? 0 : 1;
            int y = ((1 << i) & b) == 0 ? 0 : 1;
            res = res | ((x ^ y ^ carry) << i);
            carry = ((x & y) == 1 || (x & carry) == 1 || (y & carry) == 1) ? 1 : 0;
        }

        return res;
    }

    private int magicSol(int a, int b) {
        int res = a;
        int carry = b;
        while(b != 0) {
            res = a ^ b;
            carry = (a & b) << 1;

            a = res;
            b = carry;
        }

        return res;
    }
}
