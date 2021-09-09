package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Best Meeting Point",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/best-meeting-point/"
)
public class Q296 {
    /**
     * This problem can be solved by finding median index of all rows and cols of
     * all 1s cells. But I prefer the brute force approach to convert 2D to 1D and
     * improve the time complexity from (M * N) ^ 2 to M ^ 2 + N ^ 2.
     *
     * Time:  O(M * N + M ^ 2 + N ^ 2)
     * Space: O(M + N)
     * */
    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }

        int minX = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = 0; j < n; j++) {
                sum += Math.abs(j - i) * col[j];
            }
            minX = Math.min(minX, sum);
        }

        int minY = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            int sum = 0;
            for(int j = 0; j < m; j++) {
                sum += Math.abs(j - i) * row[j];
            }
            minY = Math.min(minY, sum);
        }

        return minX + minY;
    }
}
