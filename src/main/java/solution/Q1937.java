package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Number of Points with Cost",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-number-of-points-with-cost/"
)
public class Q1937 {
    /*
    * 这题思路和Q42 Trapping Rain Water类似
    * */
    public long maxPoints(int[][] points) {
        int m = points.length;
        int n = points[0].length;
        long[] dpL = new long[n];
        long[] dpR = new long[n];
        long[] dp = new long[n];

        for(int i = 0; i < n; i++) {
            dp[i] = points[0][i];
        }

        for(int i = 1; i < m; i++) {
            dpL[0] = dp[0];
            dpR[n - 1] = dp[n - 1];
            for(int j = 1; j < n; j++) {
                dpL[j] = Math.max(dp[j], dpL[j - 1] - 1);
            }
            for(int j = n - 2; j >= 0; j--) {
                dpR[j] = Math.max(dp[j], dpR[j + 1] - 1);
            }

            for(int j = 0; j < n; j++) {
                dp[j] = Math.max(dpL[j], dpR[j]) + points[i][j];
            }
        }

        long max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
