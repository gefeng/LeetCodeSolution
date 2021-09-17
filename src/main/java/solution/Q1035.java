package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Uncrossed Lines",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/uncrossed-lines/"
)
public class Q1035 {
    /**
     * Longest common subsequence
     *
     * state:
     *  dp[i][j] denotes LCS of subarray nums1[0, i - 1] and subarray nums2[0, j - 1]
     * transition:
     *  dp[i][j] = dp[i - 1][j - 1] + 1 if nums1[i - 1] == nums2[j - 1]
     *           = max(dp[i - 1][j], dp[i][j - 1]) if nums1[i - 1] != nums2[j - 1]
     *
     * Time:  O(M * N)
     * Space: O(M * N) can be reduced to O()
     * */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Time:  O(M * N)
     * Space: O(N)
     * */
    private int spaceOptimized(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] dp = new int[n + 1];

        for(int i = 1; i <= m; i++) {
            int[] next = new int[n + 1];
            for(int j = 1; j <= n; j++) {
                if(nums1[i - 1] == nums2[j - 1]) {
                    next[j] = dp[j - 1] + 1;
                } else {
                    next[j] = Math.max(dp[j], next[j - 1]);
                }
            }
            dp = next;
        }

        return dp[n];
    }
}
