package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Burst Balloons",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/burst-balloons/"
)
public class Q312 {
    /*
        state:
            dp[i][j] means max score by bursting the balloons i to j  not including i and j
        transition:
            dp[i][j] = max(dp[i][k] + dp[k][j] + nums[k] * nums[i] * nums[j])

        [3,1,5,6,8,1,2]
           1,5,6,8,1
    */
    public int maxCoins(int[] nums) {
        int n = nums.length;

        int[] scores = new int[n + 2];
        scores[0] = 1;
        scores[n + 1] = 1;
        for(int i = 0; i < n; i++) {
            scores[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];

        for(int i = n + 1; i >= 0; i--) {
            for(int j = i + 2; j < n + 2; j++) {
                for(int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + scores[i] * scores[k] * scores[j]);
                }
            }
        }

        return dp[0][n + 1];
    }
}
