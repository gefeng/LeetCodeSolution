package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Skips to Arrive at Meeting On Time",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-skips-to-arrive-at-meeting-on-time/"
)
public class Q1883 {
    /*
        state:
            dp[i][j] means minimum hours travel till ith road with j skips
        transition:
            dp[i][j] = Math.min(dp[i - 1][j - 1] + d/s, dp[i - 1][j] + ceiling(d/s))
    */
    public int minSkips(int[] dist, int speed, int hoursBefore) {
        int n = dist.length;
        int[][] dp = new int[n + 1][n + 1];

        for(int i = 1; i < n + 1; i++) {
            dp[i][0] = (dp[i - 1][0] + dist[i - 1] + speed - 1) / speed * speed;

            for(int j = 1; j < n; j++) {
                if(i < n) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + dist[i - 1]/*skip*/, (dp[i - 1][j] + dist[i - 1] + speed - 1) / speed * speed/*wait*/);
                } else {
                    dp[i][j] = dp[i - 1][j] + dist[i - 1];
                }
            }
        }

        for(int i = 0; i < n; i++) {
            if(dp[n][i] <= hoursBefore * speed) {
                return i;
            }
        }
        return -1;
    }
}
