package solution.weekly297;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Fair Distribution of Cookies",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/weekly-contest-297/problems/fair-distribution-of-cookies/"
)
public class Q2305 {
    /**
     * Time:  O(2 ^ N * 2 ^ N * K)
     * Space: O(2 ^ N * K)
     * */
    public int distributeCookies(int[] cookies, int k) {
        int n = cookies.length;
        int[][] dp = new int[k + 1][1 << n];  // minimum unfairness of first i children
        int[] sum = new int[1 << n];

        for(int i = 1; i < (1 << n); i++) {
            for(int j = 0; j < n; j++) {
                if((i & (1 << j)) != 0) {
                    sum[i] += cookies[j];
                }
            }
            dp[1][i] = sum[i];
        }

        for(int i = 2; i <= k; i++) {
            for(int j = 0; j < (1 << n); j++) {
                int min = Integer.MAX_VALUE;
                for(int m = j; m > 0; m = (m - 1) & j) {
                    min = Math.min(min, Math.max(dp[i - 1][m], sum[j ^ m]));
                }
                dp[i][j] = min;
            }
        }

        return dp[k][(1 << n) - 1];
    }
}
