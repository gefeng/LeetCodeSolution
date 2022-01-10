package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if Every Row and Column Contains All Numbers",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/check-if-every-row-and-column-contains-all-numbers/"
)
public class Q2133 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;

        for(int i = 0; i < n; i++) {
            boolean[] seen = new boolean[n + 1];

            for(int j = 0; j < n; j++) {
                if(seen[matrix[i][j]]) return false;
                seen[matrix[i][j]] = true;
            }
        }

        for(int j = 0; j < n; j++) {
            boolean[] seen = new boolean[n + 1];

            for(int i = 0; i < n; i++) {
                if(seen[matrix[i][j]]) return false;
                seen[matrix[i][j]] = true;
            }
        }

        return true;
    }
}
