package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Reducing Dishes",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/reducing-dishes/"
)
public class Q1402 {
    public int maxSatisfaction(int[] satisfaction) {
        return greedySol(satisfaction);
    }

    /**
     * state:
     *  dp[i][j] max of first i dishes with j dishes cooked
     * transition:
     *  dp[i][j] = max of dp[i - 1][j] (not cook i) & dp[i - 1][j - 1] + j * sat[i - 1]
     *
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    private int dpSol(int[] satisfaction) {
        int n = satisfaction.length;
        int[][] dp = new int[n + 1][n + 1];
        int ans = 0;

        Arrays.sort(satisfaction);

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + j * satisfaction[i - 1];
                if(j < i) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            ans = Math.max(ans, dp[n][i]);
        }

        return ans;
    }

    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    private int greedySol(int[] satisfaction) {
        int n = satisfaction.length;
        int ans = 0;

        Arrays.sort(satisfaction);

        int sum = 0;
        int coe = 0;
        for(int i = n - 1; i >= 0; i--) {
            sum += satisfaction[i];
            coe += sum;
            ans = Math.max(ans, coe);
        }
        return ans;
    }
}
