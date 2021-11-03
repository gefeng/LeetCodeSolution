package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Subarray Sum with One Deletion",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/"
)
public class Q1186 {
    /**
     * Time:  O(N)
     * Space: O(M)
     * */
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n + 1][2];
        int ans = Integer.MIN_VALUE;

        dp[1][0] = arr[0];
        ans = dp[1][0];

        // 0 no deletion 1: one deletion
        for(int i = 2; i <= n; i++) {
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i - 1]);
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i - 1], arr[i - 1]);

            ans = Math.max(ans, dp[i][1]);
            ans = Math.max(ans, dp[i][0]);

        }

        return ans;
    }
}
