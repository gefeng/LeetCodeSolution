package solution.weekly289;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Trailing Zeros in a Cornered Path",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.PREFIX_SUM,
        url = "https://leetcode.com/contest/weekly-contest-289/problems/maximum-trailing-zeros-in-a-cornered-path/"
)
public class Q2245 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public int maxTrailingZeros(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;

        int[][][] l = new int[m][n + 1][2];
        int[][][] u = new int[n][m + 1][2];

        for(int i = 0; i < m; i++) {
            for(int j = 1; j <= n; j++) {
                int v = grid[i][j - 1];
                int cnt2 = 0;
                int cnt5 = 0;
                for(int k = v; k % 2 == 0; k /= 2) {
                    cnt2++;
                }

                for(int k = v; k % 5 == 0; k /= 5) {
                    cnt5++;
                }

                l[i][j][0] = l[i][j - 1][0] + cnt2;
                l[i][j][1] = l[i][j - 1][1] + cnt5;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 1; j <= m; j++) {
                int v = grid[j - 1][i];
                int cnt2 = 0;
                int cnt5 = 0;

                for(int k = v; k % 2 == 0; k /= 2) {
                    cnt2++;
                }

                for(int k = v; k % 5 == 0; k /= 5) {
                    cnt5++;
                }

                u[i][j][0] = u[i][j - 1][0] + cnt2;
                u[i][j][1] = u[i][j - 1][1] + cnt5;
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int max = 0;
                max = Math.max(max, Math.min(l[i][j + 1][0] + u[j][i][0], l[i][j + 1][1] + u[j][i][1]));

                max = Math.max(max, Math.min(l[i][n][0] - l[i][j][0] + u[j][i][0], l[i][n][1] - l[i][j][1] + u[j][i][1]));

                max = Math.max(max, Math.min(l[i][j + 1][0] + u[j][m][0] - u[j][i + 1][0], l[i][j + 1][1] + u[j][m][1] - u[j][i + 1][1]));

                max = Math.max(max, Math.min(l[i][n][0] - l[i][j][0] + u[j][m][0] - u[j][i + 1][0], l[i][n][1] - l[i][j][1] + u[j][m][1] - u[j][i + 1][1]));

                ans = Math.max(ans, max);
            }
        }

        return ans;
    }
}
