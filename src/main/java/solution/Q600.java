package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Non-negative Integers without Consecutive Ones",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/"
)
public class Q600 {
    /**
     * f(n) means number of binary representations without consecutive ones
     * f(n) = f(n - 1) + f(n - 2) (fibonacci)
     * if b(n) starts with 0 then there are totally f(n - 1) valid representations
     * if b(n) starts with 10 then there are totally f(n - 2) valid representations
     *
     * Time:  O(1)
     * Space: O(1)
     * */
    public int findIntegers(int n) {
        int[] fib = new int[31];
        fib[0] = 1;
        fib[1] = 2;

        for(int i = 2; i < 31; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        return dfs(n, fib);
    }

    private int dfs(int n, int[] fib) {
        if(n < 3) {
            return n + 1;
        }

        int res = 0;
        for(int i = 29; i >= 0; i--) {
            if(((1 << i) & n) != 0) {
                if(((1 << i - 1) & n) == 0) {
                    res = dfs(n ^ (1 << i), fib) + fib[i];
                } else {
                    res = fib[i + 1];
                }
                break;
            }
        }

        return res;
    }
}
