package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Last Stone Weight II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/last-stone-weight-ii/"
)
public class Q1049 {
    /*
        split stones into 2 groups, find the minimum difference between two groups
        state:
            dp[i][j] means is there a subset of [stones[0], stones[i]] where sum(subset) == j
        transition:
            dp[i][j] = dp[i - 1][j - stones[i - 1]] | dp[i - 1][j]
    */
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for(int st : stones) {
            sum += st;
        }

        int[][] dp = new int[n + 1][sum / 2 + 1];
        dp[0][0] = 0;

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= sum / 2; j++) {
                if(j - stones[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return sum - 2 * dp[n][sum / 2];
    }
}
