package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Kth Smallest Instructions",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/kth-smallest-instructions/"
)
public class Q1643 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public String kthSmallestPath(int[] destination, int k) {
        int m = destination[0] + 1;
        int n = destination[1] + 1;

        int[][] dp = new int[m][n];  // dp[i][j] means number of ways to reach the destination from (i, j)

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(i == m - 1 && j == n - 1) {
                    dp[i][j] = 1;
                } else if(i == m - 1) {
                    dp[i][j] = dp[i][j + 1];
                } else if(j == n - 1) {
                    dp[i][j] = dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }

        int r = 0;
        int c = 0;
        StringBuilder ins = new StringBuilder();
        while(r != m - 1 || c != n - 1) {
            if(r == m - 1) {
                ins.append('H');
                c++;
            } else if(c == n - 1) {
                ins.append('V');
                r++;
            } else {
                if(dp[r][c + 1] >= k) {
                    ins.append('H');
                    c++;
                } else {
                    ins.append('V');
                    k -= dp[r][c + 1];
                    r++;
                }
            }
        }

        return ins.toString();
    }
}
