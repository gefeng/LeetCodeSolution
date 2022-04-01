package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Make the XOR of All Segments Equal to Zero",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/make-the-xor-of-all-segments-equal-to-zero/"
)
public class Q1787 {
    /**
     * Time:  O(N * 1024)
     * Space: O(N * 1024)
     * */
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[k + 1][1024];

        for(int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][0] = 0;

        for(int i = 1; i <= k; i++) {
            int[] cnt = new int[1024];
            int min = Integer.MAX_VALUE;
            int minv = -1;
            int tot = n / k + (i <= (n % k) ? 1 : 0);

            for(int j = i - 1; j < n; j += k) {
                cnt[nums[j]]++;
            }

            for(int j = 0; j < 1024; j++) {
                if(dp[i - 1][j] < min) {
                    min = dp[i - 1][j];
                    minv = j;
                }
            }

            for(int j = 0; j < 1024; j++) {
                dp[i][j] = min + tot - cnt[minv ^ j];

                for(int d = i - 1; d < n; d += k) {
                    int x = nums[d] ^ j;
                    if(dp[i - 1][x] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][x] + tot - cnt[nums[d]]);
                    }
                }
            }
        }

        return dp[k][0];
    }
}
