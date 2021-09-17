package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Max Dot Product of Two Subsequence",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/max-dot-product-of-two-subsequences/"
)
public class Q1458 {
    /**
     * Similar to longest common subsequence (a.k.a LCS).
     *
     * state:
     *  dp[i][j] denotes max dot product of nums1.subseq[0, i] and nums2.subseq[0, j]
     * transition:
     *  dp[i][j] = max(nums1[i] * nums2[j], dp[i - 1][j - 1] + nums1[i] * nums2[j], dp[i - 1][j], dp[i][j - 1])
     *
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = nums1[i] * nums2[j];

                if(i > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }

                if(j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }

                if(i > 0 && j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + nums1[i] * nums2[j]);
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
