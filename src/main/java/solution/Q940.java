package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Distinct Subsequences II",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/distinct-subsequences-ii/"
)
public class Q940 {
    /**
     * state:
     *  dp[i][j] denotes number of distinct subsequences ending with char j of substring(0, i)
     * transition:
     *  dp[i][j] = sum(dp[i - 1][k]) + 1 where k:['a', 'z'] if s[i - 1] - 'a' == j
     *           = dp[i - 1][j] if s[i - 1] - 'a' != j
     *
     * Time:  O(N)
     * S[ace: O(N)
     * */
    private static final long MOD = (long)1e9 + 7;
    public int distinctSubseqII(String s) {
        int n = s.length();
        long[][] dp = new long[n + 1][26];

        for(int i = 1; i <= n; i++) {
            long sum = 0;
            for(int j = 0; j < 26; j++) {
                sum = (sum + dp[i - 1][j]) % MOD;
            }

            for(int j = 0; j < 26; j++) {
                if(j == s.charAt(i - 1) - 'a') {
                    dp[i][j] = (sum + 1) % MOD;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        long ans = 0;
        for(int i = 0; i < 26; i++) {
            ans = (ans + dp[n][i]) % MOD;
        }
        return (int)(ans % MOD);
    }
}
