package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Swaps to Arrange a Binary Grid",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-swaps-to-arrange-a-binary-grid/"
)
public class Q1536 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2);
     * */
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int res = 0;
        int[] rows = new int[n];
        int[][] preSum = new int[n + 1][n + 1];

        for(int i = 0; i < n; i++) {
            rows[i] = i;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 1; j < n + 1; j++) {
                preSum[i][j] = preSum[i][j - 1] + grid[i][j - 1];
            }
        }

        for(int i = n - 1; i > 0; i--) {
            int r = -1;

            for(int j = n - 1 - i; j < n; j++) {
                int or = rows[j];
                if(preSum[or][n] - preSum[or][n - i] == 0) {
                    r = j;
                    break;
                }
            }

            if(r == -1) {
                return -1;
            }

            while(r != n - 1 - i) {
                int temp = rows[r - 1];
                rows[r - 1] = rows[r];
                rows[r] = temp;
                res++;
                r--;
            }
        }

        return res;
    }
}
