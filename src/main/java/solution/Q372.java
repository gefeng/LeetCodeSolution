package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Super Pow",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DIVIDE_AND_CONQUER,
        url = "https://leetcode.com/problems/super-pow/"
)
public class Q372 {
    /**
     * Intuition: a ^ 123 = ((a ^ 1) ^ 10 * a ^ 2) ^ 10 * a ^ 3
     * 
     * Time:  O(N)
     * Space: O(1)
     * */
    private static final int MOD = 1337;
    public int superPow(int a, int[] b) {
        int res = 1;
        int n = b.length;

        for(int i = 0; i < n; i++) {
            res = (fastPow(res, 10) % MOD) * (fastPow(a, b[i]) % MOD) % MOD;
        }
        return res;
    }

    private int fastPow(int a, int b) {
        int res = 1;
        while(b != 0) {
            if(b % 2 == 1) {
                res = (res % MOD) * (a % MOD) % MOD;
            }
            a = (a % MOD) * (a % MOD) % MOD;
            b /= 2;
        }
        return res;
    }
}
