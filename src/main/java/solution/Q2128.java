package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove All Ones With Row and Column Flips",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips/"
)
public class Q2128 {
    /**
     * Time:  O(M * N)
     * Space: O(1)
     * */
    public boolean removeOnes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 1; i < m; i++) {
            boolean isOk1 = true;
            boolean isOk2 = true;
            for(int j = 0; j < n; j++) {
                if(grid[i][j] != grid[0][j]) {
                    isOk1 = false;
                    break;
                }
            }

            for(int j = 0; j < n; j++) {
                if((grid[i][j] ^ 1) != grid[0][j]) {
                    isOk2 = false;
                    break;
                }
            }

            if(!isOk1 && !isOk2) return false;
        }

        return true;
    }
}
