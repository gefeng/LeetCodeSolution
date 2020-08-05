package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Search a 2D Matrix",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/search-a-2d-matrix/"
)
public class Q74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int row = matrix.length;
        int col = matrix[0].length;

        int lo = 0;
        int hi = row * col - 1;
        int mid = 0;
        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;
            int i = mid / col;
            int j = mid % col;
            if(matrix[i][j] == target)
                return true;
            if(matrix[i][j] < target)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return false;
    }
}
