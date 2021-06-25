package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Count Ways to Make Array With Product",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/count-ways-to-make-array-with-product/"
)
public class Q1735 {
    /*
        n = 3 k = 8
        prime factorized:  2 * 2 * 2
        f = 3 n = 3
        stars: 3
        bars:  2 (split into 3 parts)

        n - 1 + f
    */
    private static final int MOD = (int)1e9 + 7;
    public int[] waysToFillArray(int[][] queries) {
        int[] primes = getPrimes();
        long[][] combs = getCombs();

        int[] ans = new int[queries.length];
        Arrays.fill(ans, 1);
        for(int i = 0; i < queries.length; i++) {
            int n = queries[i][0];
            int k = queries[i][1];
            long q = 1;

            for(int p : primes) {
                int f = 0;
                while(k % p == 0) {
                    f++;
                    k /= p;
                }
                q = (q * combs[n - 1 + f][f]) % MOD;
                //System.out.println(combs[n - 1 + f][f]);
                //ans[i] = (int)(((long)ans[i] * combs[n - 1 + f][f]) % MOD);
            }
            q = k == 1 ? q : (q * n) % MOD;
            ans[i] = (int)(q % MOD);
            //ans[i] = k == 1 ? ans[i] : (int)(((long)ans[i] * n) % MOD);
        }

        return ans;
    }

    private int[] getPrimes() {
        List<Integer> primes = new ArrayList<>();

        for(int i = 2; i < 100; i++) {
            if(isPrime(i)) {
                primes.add(i);
            }
        }

        int[] ret = new int[primes.size()];
        for(int i = 0; i < ret.length; i++) {
            ret[i] = primes.get(i);
        }
        return ret;
    }

    private boolean isPrime(int n) {
        if(n <= 3) {
            return true;
        }

        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) {
                return false;
            }
        }

        return true;
    }

    /*
        no more than 13 factors
        max k = 10000
        so there will be max 10000 - 1 + 13 = 10012 stars + bars
        calculate pascal's triangles which gives lookup for nCk
    */
    private long[][] getCombs() {
        long[][] combs = new long[10013][14];
        combs[0][0] = 1;
        for(int n = 1; n < 10013; n++) {
            for(int k = 0; k < 14; k++) {
                combs[n][k] = k == 0 ? 1 : combs[n - 1][k - 1] + combs[n - 1][k] % MOD;
            }
        }
        return combs;
    }
}
