package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Split Array With Same Average",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/split-array-with-same-average/"
)
public class Q805 {
    /**
     * state:
     *  dp[i][j][k] is it possible to have sum k with j numbers using first i elements
     * transition:
     *  dp[i][j][k] = true if dp[i - 1][j - 1][k - nums[i - 1]] is true
     *                     if dp[i - 1][j][k] is true
     *
     *  sum / n == a / k == b / n - k
     *  a = (sum * k) / n
     *
     * Time:  O(N ^ 2 * sum)
     * Space: O(N ^ 2 * sum)
     * */
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;

        int sum = 0;
        for(int x : nums) sum += x;

        boolean[][][] dp = new boolean[n + 1][n + 1][sum + 1];
        for(int i = 0; i <= n; i++) {
            dp[i][0][0] = true;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= Math.min(i, n / 2); j++) {
                for(int k = 0; k <= sum; k++) {
                    if(k - nums[i - 1] >= 0 && dp[i - 1][j - 1][k - nums[i - 1]]) {
                        dp[i][j][k] = true;
                    }

                    if(dp[i - 1][j][k]) {
                        dp[i][j][k] = true;
                    }

                    if(dp[i][j][k] && sum * j % n == 0 && k == sum * j / n) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
