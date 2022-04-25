package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Count Primes",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/count-primes/"
)
public class Q204 {
    /**
     * sieve
     * */
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        for(int i = 2; i * i <= n; i++) {
            if(!isPrime[i]) {
                continue;
            }

            for(int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }

        int cnt = 0;
        for(int i = 2; i < n; i++) {
            cnt = isPrime[i] ? cnt + 1 : cnt;
        }

        return cnt;
    }
}
