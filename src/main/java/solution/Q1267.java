package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Servers that Communicate",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/count-servers-that-communicate/"
)
public class Q1267 {
    /**
     * Time:  O(M * N)
     * Space: O(M + N)
     * */
    public int countServers(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;

        int[] rows = new int[m];
        int[] cols = new int[n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                rows[i] += grid[i][j];
                cols[j] += grid[i][j];
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    if(rows[i] > 1 || cols[j] > 1) {
                        ans++;
                    }
                }
            }
        }

        return  ans;
    }
}
