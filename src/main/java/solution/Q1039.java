package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Score Triangulation of Polygon",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-score-triangulation-of-polygon/"
)
public class Q1039 {
    public int minScoreTriangulation(int[] values) {
        //return topDownDp(values);
        return bottomUpDp(values);
    }

    /*
        state:
            dp[i][j] means min score to triangulate convex with start index i and end index j
        transition:
            dp[i][j] = dp[i][j - 1] +
    */
    private int bottomUpDp(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];

        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 2; j < n; j++) {

                int minScore = Integer.MAX_VALUE;

                for(int k = i + 1; k < j; k++) {
                    minScore = Math.min(minScore, dp[i][k] + dp[k][j] + values[i] * values[k] * values[j]);
                }

                //System.out.println(i + " " + j + " " + minScore);
                dp[i][j] = minScore;
            }
        }

        return dp[0][n - 1];
    }

    private int topDownDp(int[] values) {
        int n = values.length;
        return dfs(values, 0, n - 1, new Integer[n][n]);
    }

    private int dfs(int[] values, int start, int end, Integer[][] memo) {
        if(end - start < 2) {
            return 0;
        }
        if(end - start == 2) {
            return values[start] * values[start + 1] * values[end];
        }

        if(memo[start][end] != null) {
            return memo[start][end];
        }

        int minScore = Integer.MAX_VALUE;
        for(int i = start + 1; i <= end - 1; i++) {
            int score1 = dfs(values, start, i, memo);
            int score2 = dfs(values, i, end, memo);
            minScore = Math.min(minScore, score1 + score2 + values[start] * values[end] * values[i]);
        }

        memo[start][end] = minScore;
        memo[end][start] = minScore;
        return minScore;
    }
}
