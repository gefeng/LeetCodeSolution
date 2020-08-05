package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Search a 2D Matrix II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/search-a-2d-matrix-ii/"
)
public class Q240 {
    private boolean bsHorizontal(int[][] matrix, int target, int row) {
        int lo = row;
        int hi = matrix[0].length - 1;
        int mid = 0;
        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if(matrix[row][mid] == target)
                return true;
            if(matrix[row][mid] < target)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return false;
    }
    private boolean bsVertical(int[][] matrix, int target, int col) {
        int lo = col;
        int hi = matrix.length - 1;
        int mid = 0;
        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if(matrix[mid][col] == target)
                return true;
            if(matrix[mid][col] < target)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return false;
    }


    public boolean searchMatrixBinarySearch(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0)
            return false;

        int diameter = Math.min(matrix.length, matrix[0].length);
        for(int i = 0; i < diameter; i++) {
            if(bsHorizontal(matrix, target, i))
                return true;
            if(bsVertical(matrix, target, i))
                return true;
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0)
            return false;

        int[] pos = new int[] { matrix.length - 1, 0 };
        int posX = matrix.length - 1;
        int posY = 0;
        while(posX >= 0 && posY < matrix[0].length) {
            if(matrix[posX][posY] == target)
                return true;
            if(matrix[posX][posY] < target)
                posY++;
            else
                posX--;
        }

        return false;
    }
}
