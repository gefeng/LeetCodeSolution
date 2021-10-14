package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Matrix Block Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/matrix-block-sum/"
)
public class Q1314 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i < m + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                dp[i][j] = mat[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }

        int[][] ans = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int tr = Math.max(0, i - k);
                int tc = Math.max(0, j - k);
                int br = Math.min(m - 1, i + k);
                int bc = Math.min(n - 1, j + k);

                ans[i][j] = dp[br + 1][bc + 1] - dp[tr][bc + 1] - dp[br + 1][tc] + dp[tr][tc];
            }
        }

        return ans;
    }
}
