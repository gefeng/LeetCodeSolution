package solution.biweekly81;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Distinct Roll Sequences",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/biweekly-contest-81/problems/number-of-distinct-roll-sequences/"
)
public class Q2318 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int distinctSequences(int n) {
        long mod = (long)1e9 + 7;
        long[][] dp = new long[7][7];

        if(n == 1) return 6;

        for(int i = 1; i <= 6; i++) {
            for(int j = 1; j <= 6; j++) {
                if(i != j && gcd(i, j) == 1) {
                    dp[i][j] = 1;
                }
            }
        }

        for(int i = 3; i <= n; i++) {
            long[][] ndp = new long[7][7];
            for(int j = 1; j <= 6; j++) {
                for(int k = 1; k <= 6; k++) {
                    for(int p = 1; p <= 6; p++) {
                        if(p != j && p != k && gcd(p, k) == 1) {
                            ndp[k][p] = (ndp[k][p] + dp[j][k]) % mod;
                        }
                    }
                }
            }
            dp = ndp;
        }

        long ans = 0;
        for(int i = 1; i <= 6; i++) {
            for(int j = 1; j <= 6; j++) {
                ans = (ans + dp[i][j]) % mod;
            }
        }

        return (int)ans;
    }

    private int gcd(int a, int b) {
        if(a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}
