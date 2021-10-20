package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Palindrome Partitioning III",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/palindrome-partitioning-iii/"
)
public class Q1278 {
    /**
     * Time:  O(N ^ 2 * K)
     * Space: O(N ^ 2 + N ^ K)
     * */
    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] dp1 = new int[n][n];

        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                if(i == j) {
                    dp1[i][j] = 0;
                } else if(j - i == 1) {
                    dp1[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
                } else {
                    dp1[i][j] = s.charAt(i) == s.charAt(j) ? dp1[i + 1][j - 1] : dp1[i + 1][j - 1] + 1;
                }
            }
        }

        int[][] dp2 = new int[n + 1][k + 1];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(dp2[i], Integer.MAX_VALUE);
        }

        /*
            dp[i][j] denotes min change to divide substring(0, i) with j partitions.
            dp[i][j] = min(dp[l][j - 1] + cost(l, i - 1)) for l from 1 to i - 1
        */
        dp2[0][0] = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= Math.min(i, k); j++) {
                for(int l = 0; l <= i - 1; l++) {
                    if(dp2[l][j - 1] != Integer.MAX_VALUE) {
                        dp2[i][j] = Math.min(dp2[i][j], dp2[l][j - 1] + dp1[l][i - 1]);
                    }
                }
            }
        }

        return dp2[n][k];

        //return dfs(s, k, 0, dp1, new Integer[n][k + 1]);
    }
}
