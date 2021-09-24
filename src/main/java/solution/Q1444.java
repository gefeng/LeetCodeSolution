package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Ways of Cutting a Pizza",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/"
)
public class Q1444 {
    /**
     * Time:  O(M * N * K * (M + N))
     * Space: O(M * N * K)
     * */
    private static final int MOD = (int)1e9 + 7;

    private int m;
    private int n;
    private int[][] dp;
    public int ways(String[] pizza, int k) {
        m = pizza.length;
        n = pizza[0].length();
        dp = new int[m + 1][n + 1];

        for(int r = 1; r <= m; r++) {
            for(int c = 1; c <= n; c++) {
                dp[r][c] = dp[r - 1][c] + dp[r][c - 1] - dp[r - 1][c - 1] + (pizza[r - 1].charAt(c - 1) == 'A' ? 1 : 0);
            }
        }

        return dfs(pizza, 0, 0, k - 1, new Integer[m][n][k + 1]);
    }

    private int dfs(String[] pizza, int r, int c, int k, Integer[][][] memo) {
        if(k == 0) {
            int cnt = dp[m][n] - dp[r][n] - dp[m][c] + dp[r][c];
            return cnt > 0 ? 1 : 0;
        }

        if(memo[r][c][k] != null) {
            return memo[r][c][k];
        }

        int total = 0;
        // hor
        for(int i = r; i < m - 1; i++) {
            int cnt = dp[i + 1][n] - dp[r][n] - dp[i + 1][c] + dp[r][c];
            if(cnt > 0) {
                total = (total + dfs(pizza, i + 1, c, k - 1, memo)) % MOD;
            }
        }

        // ver
        for(int i = c; i < n - 1; i++) {
            int cnt = dp[m][i + 1] - dp[m][c] - dp[r][i + 1] + dp[r][c];
            if(cnt > 0) {
                total = (total + dfs(pizza, r, i + 1, k - 1, memo)) % MOD;
            }
        }

        return memo[r][c][k] = total;
    }
}
