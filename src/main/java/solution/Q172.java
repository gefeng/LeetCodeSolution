package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Factorial Trailing Zeroes",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/factorial-trailing-zeroes/"
)
public class Q172 {
    public int trailingZeroes(int n) {
        return linearTimeSol(n);
    }

    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    private int linearTimeSol(int n) {
        int res = 0;

        for(int i = 5; i <= n; i += 5) {
            int num = i;
            while(num % 5 == 0) {
                res++;
                num /= 5;
            }
        }

        return res;
    }

    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    private int logarithmicTimeSol(int n) {
        int res = 0;
        int base = 5;

        while(base <= n) {
            res += n / base;
            base *= 5;
        }

        return res;
    }
}
