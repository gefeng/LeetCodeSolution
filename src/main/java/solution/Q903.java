package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
       title = "Valid Permutations for DI Sequence",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/valid-permutations-for-di-sequence/"
)
public class Q903 {
    /**
     * state:
     *  dp[i][j] denotes # valid permuation of [0..i] ending with j
     * transition:
     *  dp[i][j] = sum(dp[i - 1][k]) where k:[j, i]     if s[i - 1] == 'D'
     *           = sum(dp[i - 1][k]) where k:[0, j - 1] if s[i - 1] == 'I'
     *
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    private static final long MOD = (long)1e9 + 7;
    public int numPermsDISequence(String s) {
        int n = s.length();
        long[][] dp = new long[n + 1][n + 1];

        dp[0][0] = 1;

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= i; j++) {
                if(s.charAt(i - 1) == 'D') {
                    for(int k = j; k <= i; k++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
                    }
                } else {
                    for(int k = 0; k < j; k++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
                    }
                }
            }
        }

        long ans = 0;
        for(int i = 0; i <= n; i++) {
            ans = (ans + dp[n][i]) % MOD;
        }
        return (int)ans;
    }
}
