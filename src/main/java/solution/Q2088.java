package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Fertile Pyramids in a Land",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/count-fertile-pyramids-in-a-land/"
)
public class Q2088 {
    /**
     * The constraints  m * n <= 10^5 allow us to brute force.
     * The only trick is to calculate prefix sum for each row to have a O(1) pyramid's bottom lookup later.
     *
     * Time:  O(M * N * min(M, N))
     * Space: O(M * N)
     * */
    public int countPyramids(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;

        int[][] preSum = new int[m][n + 1];
        for(int i = 0; i < m; i++) {
            for(int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i][j - 1] + grid[i][j - 1];
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    continue;
                }

                for(int h = 2; i + h - 1 < m; h++) {
                    int l = j - (h - 1);
                    int r = j + (h - 1);
                    if(l >= 0 && r < n && preSum[i + h - 1][r + 1] - preSum[i + h - 1][l] == 2 * h - 1) {
                        ans++;
                    } else {
                        break;
                    }
                }

                for(int h = 2; i - (h - 1) >= 0; h++) {
                    int l = j - (h - 1);
                    int r = j + (h - 1);
                    if(l >= 0 && r < n && preSum[i - h + 1][r + 1] - preSum[i - h + 1][l] == 2 * h - 1) {
                        ans++;
                    } else {
                        break;
                    }
                }
            }
        }

        return  ans;
    }
}
