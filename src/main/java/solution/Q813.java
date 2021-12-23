package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Sum of Averages",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/largest-sum-of-averages/"
)
public class Q813 {
    /**
     * state:
     *  dp[i][j] denotes maximum score of j cuts on first i elements
     * transition:
     *  dp[i][j] -> dp[i + 1][j + 1], dp[i + 2][j + 1], ... dp[n][j + 1]
     *
     * Time:  O(N ^ 2 * K)
     * Space: O(N * K)
     * */
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[][] dp = new double[n + 1][k];

        double sum = 0;
        for(int i = 1; i <= n; i++) {
            sum += nums[i - 1];
            dp[i][0] = sum / i;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < k - 1; j++) {
                sum = 0;
                for(int l = i + 1; l <= n; l++) {
                    sum += nums[l - 1];
                    dp[l][j + 1] = Math.max(dp[l][j + 1], dp[i][j] + sum / (l - i));
                }
            }
        }

        double ans = 0;
        for(int i = 0; i < k; i++) {
            ans = Math.max(ans, dp[n][i]);
        }

        return ans;
    }
}
