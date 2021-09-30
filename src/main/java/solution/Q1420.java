package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Build Array Where You Can Find The Maximum Exactly K Comparisons",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons/"
)
public class Q1420 {
    /**
     * Time:  O(N * K * M)
     * Space: O(N * K * M)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numOfArrays(int n, int m, int k) {
        return bottomUpSol(n, m, k);
    }
    
    /**
     * state:
     *  dp[i][j][k] denotes number of ways to build array with length i, max element j and search cost k
     * transition:
     *  dp[i][j][k] = dp[i - 1][j][k] * j + sum(dp[i - 1][a][k - 1] where a in [1, j - 1])
     * */
    private int bottomUpSol(int n, int m, int k) {
        int[][][] dp = new int[n + 1][m + 1][k + 1];
        int ans = 0;

        for(int i = 1; i <= m; i++) {
            dp[1][i][1] = 1;
        }

        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                for(int l = 1; l <= k; l++) {
                    long res = 0;

                    res = ((long)dp[i - 1][j][l] * j) % MOD;

                    for(int a = 1; a < j; a++) {
                        res = (res + dp[i - 1][a][l - 1]) % MOD;
                    }

                    dp[i][j][l] = (int)(res % MOD);
                }
            }
        }

        for(int i = 1; i <= m; i++) {
            ans = (ans + dp[n][i][k]) % MOD;
        }
        return ans;
    }

    private int topDownSol(int n, int m, int k) {
        return dfs(n, m, 0, k, 0, new Integer[n][k + 1][101]);
    }

    private int dfs(int n, int m, int i, int k, int prevMax, Integer[][][] memo) {
        if(i == n) {
            return k == 0 ? 1 : 0;
        }

        if(memo[i][k][prevMax] != null) {
            return memo[i][k][prevMax];
        }

        int cnt = 0;
        for(int d = 1; d <= prevMax; d++) {
            cnt = (cnt + dfs(n, m, i + 1, k, prevMax, memo)) % MOD;
        }

        if(k > 0) {
            for(int d = prevMax + 1; d <= m; d++) {
                cnt = (cnt + dfs(n, m, i + 1, k - 1, d, memo)) % MOD;
            }
        }

        return memo[i][k][prevMax] = cnt;
    }
}
