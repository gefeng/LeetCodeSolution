package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Ones and Zeroes",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/ones-and-zeroes/"
)
public class Q474 {
    public int findMaxForm(String[] strs, int m, int n) {
        return bottomUpSol(strs, m, n);
    }

    /**
     * Time:  O(L * M * N)
     * Space: O(L * M * N)
     * */
    private int topDownSol(String[] strs, int m, int n) {
        int sz = strs.length;
        int[][] cnt = new int[sz][2];

        for(int i = 0; i < sz; i++) {
            String s = strs[i];
            int len = s.length();
            for(int j = 0; j < len; j++) {
                cnt[i][s.charAt(j) - '0']++;
            }
        }

        return dfs(strs, cnt, 0, m, n, new Integer[sz][m + 1][n + 1]);
    }

    private int dfs(String[] strs, int[][] cnt, int i, int m, int n, Integer[][][] memo) {
        if(i == strs.length) {
            return 0;
        }

        if(memo[i][m][n] != null) {
            return memo[i][m][n];
        }

        int max = dfs(strs, cnt, i + 1, m, n, memo);

        if(cnt[i][0] <= m && cnt[i][1] <= n) {
            max = Math.max(max, dfs(strs, cnt, i + 1, m - cnt[i][0], n - cnt[i][1], memo) + 1);
        }

        return memo[i][m][n] = max;
    }

    /**
     * state:
     *  dp[i][j][k] denotes size of the largest subsets of sets[0, i] with at most j 0's and k 1's.
     * transition:
     *  dp[i][j][k] = max(max(dp[i - 1][j][k], dp[i - 1][j - cnt0][k - cnt1]));
     *
     * Time:  O(L * M * N)
     * Space: O(L * M * N)
     * */
    private int bottomUpSol(String[] strs, int m, int n) {
        int sz = strs.length;
        int[][] cnt = new int[sz][2];

        for(int i = 0; i < sz; i++) {
            String s = strs[i];
            int len = s.length();
            for(int j = 0; j < len; j++) {
                cnt[i][s.charAt(j) - '0']++;
            }
        }

        int[][][] dp = new int[sz + 1][m + 1][n + 1];

        for(int i = 1; i <= sz; i++) {
            for(int j = 0; j <= m; j++) {
                for(int k = 0; k <= n; k++) {
                    dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k]);
                    if(cnt[i - 1][0] <= j && cnt[i - 1][1] <= k) {
                        dp[i][j][k] = Math.max(dp[i][j][k], 1 + dp[i - 1][j - cnt[i - 1][0]][k - cnt[i - 1][1]]);
                    }
                }
            }
        }

        return dp[sz][m][n];
    }
}
