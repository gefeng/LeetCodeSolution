package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Champagne Tower",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/champagne-tower/"
)
public class Q799 {
    /**
     * dp[i][j] denotes how much champagne does glass[i][j] have
     *
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[100][100];

        dp[0][0] = poured;
        for(int i = 0; i < 99; i++) {
            for(int j = 0; j <= i; j++) {
                if(dp[i][j] > 1) {
                    dp[i + 1][j] += (dp[i][j] - 1) / 2;
                    dp[i + 1][j + 1] += (dp[i][j] - 1) / 2;
                }
            }
        }

        return dp[query_row][query_glass] >= 1 ? 1.0 : dp[query_row][query_glass];
    }
}
