package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Points You Can Obtain from Cards",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/"
)
public class Q1423 {
    /*
        state:
            dp[i][j] means maximum score obtained by taking i cards and j cards from left most
        transition:
            dp[i][j] = max(dp[i - 1][j - 1] + cardPoints[j - 1],
                           dp[i - 1][j] + cardPoints[n - (i - 1 - j) - 1]) + 1
    */
    public int maxScore(int[] cardPoints, int k) {
        return prefixSumSolution(cardPoints, k);
    }

    private int dpSolution(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[][] dp = new int[k + 1][k + 1];

        for(int i = 1; i < k + 1; i++) {
            for(int j = 0; j <= i; j++) {
                if(j < i) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + cardPoints[n - (i - 1 - j) - 1]);
                }
                if(j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + cardPoints[j - 1]);
                }
            }
        }

        int maxScore = 0;
        for(int i = 0; i < k + 1; i++) {
            maxScore = Math.max(maxScore, dp[k][i]);
        }

        return maxScore;
    }

    private int dpSpaceOptimized(int[] cardPoints, int k) {
        int n = cardPoints.length;

        int[] prev = new int[k + 1];

        for(int i = 1; i < k + 1; i++) {
            int[] curr = new int[k + 1];
            for(int j = 0; j <= i; j++) {
                if(j < i) {
                    curr[j] = Math.max(curr[j], prev[j] + cardPoints[n - (i - 1 - j) - 1]);
                }
                if(j > 0) {
                    curr[j] = Math.max(curr[j], prev[j - 1] + cardPoints[j - 1]);
                }
            }
            prev = curr;
        }

        int maxScore = 0;
        for(int i = 0; i < k + 1; i++) {
            maxScore = Math.max(maxScore, prev[i]);
        }

        return maxScore;
    }

    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private int prefixSumSolution(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] prefixSum = new int[n + 1];

        for(int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + cardPoints[i - 1];
        }

        int maxScore = 0;
        int score = 0;
        for(int i = 0; i < k + 1; i++) {
            score = prefixSum[i] + (prefixSum[n] - prefixSum[n - (k - i)]);
            maxScore = Math.max(maxScore, score);
        }

        return maxScore;
    }
}
