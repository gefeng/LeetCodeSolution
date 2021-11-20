package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Cost to Merge Stones",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-cost-to-merge-stones/"
)
public class Q1000 {
    /**
     * state:
     *  dp[i][j][m] denotes minimum cost to merge stones[i] to stones[j] into m piles.
     * transition:
     *  dp[i][j][m] = min(dp[i][mid][1] + dp[mid + 1][j][m - 1]) m : [2,k]
     *  dp[i][j][1] = dp[i][j][k] + sum(stones[i], stones[j])    m = 1
     *
     * Time:  O(N ^ 3 * K)
     * Space: O(N ^ 2 * K)
     * */
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        int[][][] dp = new int[n][n][k + 1];
        int[] preSum = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + stones[i - 1];
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        for(int i = 0; i < n; i++) {
            dp[i][i][1] = 0;
        }

        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                for(int m = 2; m <= k; m++) {
                    for(int l = i; l < j; l++) {
                        int x = dp[i][l][1];
                        int y = dp[l + 1][j][m - 1];
                        if(x != Integer.MAX_VALUE && y != Integer.MAX_VALUE) {
                            dp[i][j][m] = Math.min(dp[i][j][m], x + y);
                        }
                    }
                }

                dp[i][j][1] = dp[i][j][k] == Integer.MAX_VALUE ? dp[i][j][1] : dp[i][j][k] + preSum[j + 1] - preSum[i];
            }
        }

        return dp[0][n - 1][1] == Integer.MAX_VALUE ? -1 : dp[0][n - 1][1];
    }
}
