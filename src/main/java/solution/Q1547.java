package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Cost to Cut a Stick",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-cost-to-cut-a-stick/"
)
public class Q1547 {
    /**
     * state:
     *  dp[i][j] denotes min total cost to cut stick [cuts[i], cuts[j]]
     * transition:
     *  dp[i][j] = min(dp[i][k] + dp[k][j]) + (cuts[j] - cuts[i]) for k from (i, j)
     *
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] c = new int[m + 2];
        int[][] dp = new int[m + 2][m + 2];

        c[0] = 0;
        c[m + 1] = n;
        for(int i = 0; i < m; i++) {
            c[i + 1] = cuts[i];
        }

        Arrays.sort(c);

        for(int i = m + 1; i >= 0; i--) {
            for(int j = i + 2; j < m + 2; j++) {
                int min = Integer.MAX_VALUE;
                int base = c[j] - c[i];
                for(int k = i + 1; k < j; k++) {
                    min = Math.min(min, dp[i][k] + dp[k][j] + base);
                }
                dp[i][j] = min;
            }
        }

        return dp[0][m + 1];
    }
}
