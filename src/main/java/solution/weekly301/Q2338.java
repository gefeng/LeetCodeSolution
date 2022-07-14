package solution.weekly301;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count the Number of Ideal Arrays",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/contest/weekly-contest-301/problems/count-the-number-of-ideal-arrays/"
)
public class Q2338 {
    /**
     * Time:  O(N * sqrt(N))
     * Space: O(N)
     * */
    public int idealArrays(int n, int maxValue) {
        long mod = (long)1e9 + 7;
        long ans = 0;
        Comb comb = new Comb(n, mod);

        long[][] dp = new long[21][maxValue + 1];
        for(int i = 1; i <= maxValue; i++) {
            dp[1][i] = 1;
        }

        for(int i = 2; i <= 20; i++) {
            for(int j = 2; j <= maxValue; j++) {
                for(int f = 1; f * f <= j; f++) {
                    if(j % f == 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][f]) % mod;
                        if(j / f != f && j / f < j) {
                            dp[i][j] = (dp[i][j] + dp[i - 1][j / f]) % mod;
                        }
                    }
                }
            }
        }

        for(int i = 1; i <= 20; i++) {
            for(int j = 1; j <= maxValue; j++) {
                if(dp[i][j] != 0) {
                    ans = (ans + comb.get(n - 1, i - 1) * dp[i][j] % mod) % mod;
                }
            }
        }

        return (int)ans;
    }

    private class Comb {
        int MAX;
        long MOD;
        long[] fact;
        long[] invf;
        Comb(int n, long mod) {
            MAX = n;
            MOD = mod;
            fact = new long[MAX + 1];
            invf = new long[MAX + 1];

            fact[0] = 1;
            invf[0] = 1;

            for(int i = 1; i <= MAX; i++) {
                fact[i] = fact[i - 1] * i % MOD;
            }

            invf[MAX] = power(fact[MAX], MOD - 2);

            for(int i = MAX - 1; i >= 0; i--) {
                invf[i] = invf[i + 1] * (i + 1) % MOD;
            }
        }

        long get(int n, int k) {
            if(n < 0 || k < 0 || n < k) {
                return 0;
            }

            return fact[n] * (invf[k] * invf[n - k] % MOD) % MOD;
        }

        long power(long x, long y) {
            long res = 1;
            while(y > 0) {
                if(y % 2 == 1) {
                    res = res * x % MOD;
                }
                y >>= 1;
                x = x * x % MOD;
            }
            return res;
        }
    }
}
