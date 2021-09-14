package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Allocate Mailboxes",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/allocate-mailboxes/"
)
public class Q1478 {
    public int minDistance(int[] houses, int k) {
        int n = houses.length;
        int[][] dist = new int[n][n];

        Arrays.sort(houses);

        for(int l = 0; l < n; l++) {
            for(int r = l; r < n; r++) {
                for(int m = l; m <= r; m++) {
                    dist[l][r] += Math.abs(houses[m] - houses[(l + r) / 2]);
                }
            }
        }

        return bottomUpSol(houses, k, dist);
    }

    /**
     * state:
     *  dp[i][k] denotes minimum distance for houses[0:i] and k mailboxes
     * transition:
     *  dp[i][k] = min(dp[j][k - 1] + dist[j + 1][i]) where j from [0:i - 1] and dist[j + 1][i]
     *  is the minimum cost to add one more mailbox for houses[j + 1, i]
     *
     * Time:  O(N ^ 2 * K)
     * Space: O(N * K)
     * */
    private int bottomUpSol(int[] houses, int k, int[][] dist) {
        int n = houses.length;
        int[][] dp = new int[n][k + 1];

        // fill the first row of the dp table
        for(int i = 0; i < n; i++) {
            dp[i][1] = dist[0][i];
        }

        for(int i = 1; i < n; i++) {
            for(int l = 2; l <= k; l++) {
                int min = Integer.MAX_VALUE;
                for(int j = 0; j < i; j++) {
                    min = Math.min(min, dp[j][l - 1] + dist[j + 1][i]);
                }
                dp[i][l] = min;
            }
        }

        return dp[n - 1][k];
    }

    /**
     * Divide houses into k groups, in each group we calculate the distance between
     * each house in the group and the mail box assigned to this group. We have to
     * put the mail box in the median house and the group to make sure the distance
     * is minimum.
     *
     * Time:  O(N ^ 2 * K)
     * Space: O(N * K)
     * */
    private int topDownSol(int[] houses, int k, int[][] dist) {
        int n = houses.length;
        return dfs(houses, dist, 0, k, new Integer[n][n + 1]);
    }

    private int dfs(int[] houses, int[][] dist, int i, int k, Integer[][] memo) {
        int n = houses.length;

        if(k == 0 && i == n) {
            return 0;
        }
        if(k == 0 || i == n) {
            return Integer.MAX_VALUE;
        }

        if(memo[i][k] != null) {
            return memo[i][k];
        }

        int min = Integer.MAX_VALUE;
        for(int j = i; j < n; j++) {
            int d = dfs(houses, dist, j + 1, k - 1, memo);
            if(d != Integer.MAX_VALUE) {
                min = Math.min(min, d + dist[i][j]);
            }
        }

        return memo[i][k] = min;
    }
}
