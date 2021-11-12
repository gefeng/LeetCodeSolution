package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Shortest Common Supersequence",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/shortest-common-supersequence/"
)
public class Q1092 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int len = dp[m][n];
        char[] lcs = new char[len];

        for(int i = m, j = n, k = len - 1; i > 0 && j > 0;) {
            if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs[k--] = str1.charAt(i - 1);
                i--;
                j--;
            } else if(dp[i - 1][j] == dp[i][j]) {
                i--;
            } else {
                j--;
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        int k = 0;
        for(; k < len; k++) {
            while(str1.charAt(i) != lcs[k]) {
                sb.append(str1.charAt(i++));
            }
            while(str2.charAt(j) != lcs[k]) {
                sb.append(str2.charAt(j++));
            }
            sb.append(lcs[k]);
            i++;
            j++;
        }

        while(i < m) {
            sb.append(str1.charAt(i++));
        }
        while(j < n) {
            sb.append(str2.charAt(j++));
        }

        return sb.toString();
    }
}
