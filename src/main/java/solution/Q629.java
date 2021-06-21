package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "K Inverse Pairs Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/k-inverse-pairs-array/"
)
public class Q629 {
    /**
    * 这题从O(n * k * k) -> O(n * k)的优化很难
    * */
    private static final int MOD = (int)1e9 + 7;
    public int kInversePairs(int n, int k) {
        return topDownOptimized(n, k);
    }

    /*
        for a sequence [1,2,...,n] we can pick any number and put at position 0
        to form n - 1 inverse pair. Recursively repeat this process.
    */
    private int bottomUp(int n, int k) {
        return dfs(n, k, new Integer[n + 1][k + 1]);
    }

    // 1 2 3 4   ->  3 1 2 4
    private int dfs(int n, int k, Integer[][] memo) {
        if(n == 0) {
            return 0;
        }
        if(k == 0) {
            return 1;
        }

        if(memo[n][k] != null) {
            return memo[n][k];
        }

        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(k - (i - 1) < 0) {
                break;
            }

            cnt = (cnt + dfs(n - 1, k - (i - 1), memo)) % MOD;
        }

        return memo[n][k] = cnt;
    }

    private int topDown(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < k + 1; j++) {
                for(int x = 1; x <= i; x++) {
                    if(j - x + 1 >= 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - x + 1]) % MOD;
                    }
                }
            }
        }

        return dp[n][k];
    }

    private int topDownOptimized(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < k + 1; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % MOD;
                if(j - i >= 0) {
                    dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + MOD) % MOD;
                }
            }
        }

        return dp[n][k];
    }
}
