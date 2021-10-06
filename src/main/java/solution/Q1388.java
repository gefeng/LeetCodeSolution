package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Pizza With 3n Slices",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/pizza-with-3n-slices/"
)
public class Q1388 {
    /**
     * Time:  O()
     * Space: O()
     * */
    public int maxSizeSlices(int[] slices) {
        int n = slices.length;
        // return Math.max(dfs(slices, 0, n - 1, n / 3, new Integer[n][n / 3 + 1]),
        //                 dfs(slices, 1, n, n / 3, new Integer[n][n / 3 + 1]));

        return Math.max(getMax(Arrays.copyOfRange(slices, 0, n - 1), n / 3),
                getMax(Arrays.copyOfRange(slices, 1, n), n / 3));
    }

    /**
     * dp[i][j] denotes max sum by picking j number from first i elements
     * dp[i][j] = max(dp[i - 1][j], dp[i - 2][j - 1] + slices[i - 1])
     * */
    private int getMax(int[] slices, int k) {
        int n = slices.length;
        int ans = 0;
        int[][] dp = new int[n + 1][k + 1];

        for(int i = 1; i <= n; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], slices[i - 1]);
        }
        for(int i = 2; i <= n; i++) {
            for(int j = 2; j <= k; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 2][j - 1] + slices[i - 1]);
            }
        }

        return dp[n][k];
    }

    // can pick last if first it not pick and
    private int dfs(int[] slices, int cur, int n, int left, Integer[][] memo) {
        if(left == 0) {
            return 0;
        }
        if(cur > n - 1) {
            return -1;
        }

        if(memo[cur][left] != null) {
            return memo[cur][left];
        }

        int max = 0;

        int skip = dfs(slices, cur + 1, n, left, memo);
        int pick = dfs(slices, cur + 2, n, left - 1, memo);

        if(pick != -1) {
            pick = pick + slices[cur];
        }

        max = Math.max(skip, pick);

        return memo[cur][left] = max;
    }
}
