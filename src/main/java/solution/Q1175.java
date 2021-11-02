package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Prime Arrangements",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/prime-arrangements/"
)
public class Q1175 {
    /**
     * Time:  O(N * sqrt(n))
     * Space: O(1)
     * */
    private static final long MOD = (long)1e9 + 7;
    public int numPrimeArrangements(int n) {
        int cntP = 0;

        for(int i = 1; i <= n; i++) {
            if(isPrime(i)) {
                cntP++;
            }
        }

        long ans = 1;
        for(int i = 1; i <= cntP; i++) {
            ans = ans * i % MOD;
        }
        for(int i = 1; i <= n - cntP; i++) {
            ans = ans * i % MOD;
        }

        return (int)ans;
    }

    private boolean isPrime(int x) {
        for(int i = 2; i * i <= x; i++) {
            if(x % i == 0) {
                return false;
            }
        }
        return x != 1;
    }
}
