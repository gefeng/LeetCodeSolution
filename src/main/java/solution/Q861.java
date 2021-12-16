package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Score After Flipping Matrix",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/score-after-flipping-matrix/"
)
public class Q861 {
    /**
     * Time:  O(M * N)
     * Space: O(1)
     * */
    public int matrixScore(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i < m; i++) {
            if(grid[i][0] == 0) {
                for(int j = 0; j < n; j++) {
                    grid[i][j] ^= 1;
                }
            }
        }

        for(int i = 1; i < n; i++) {
            int cnt = 0;
            for(int j = 0; j < m; j++) {
                cnt += grid[j][i];
            }
            if(cnt < m - cnt) {
                for(int j = 0; j < m; j++) {
                    grid[j][i] ^= 1;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            int val = 0;
            for(int j = 0; j < n; j++) {
                val = val << 1 | grid[i][j];
            }
            ans += val;
        }

        return ans;
    }
}
