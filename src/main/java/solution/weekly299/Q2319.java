package solution.weekly299;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if Matrix Is X-Matrix",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/contest/weekly-contest-299/problems/check-if-matrix-is-x-matrix/"
)
public class Q2319 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(1)
     * */
    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j || i == n - 1 - j) {
                    if(grid[i][j] == 0) {
                        return false;
                    }
                } else {
                    if(grid[i][j] != 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
