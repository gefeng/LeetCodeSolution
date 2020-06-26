package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Spiral Matrix",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/spiral-matrix/"
)
public class Q54 {
    /**move along with top, right, bottom and left then move inside (offset + 1)**/
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiral = new ArrayList<>();

        if(matrix == null || matrix.length == 0)
            return spiral;

        int width = matrix[0].length;
        int height = matrix.length;
        int row = 0;
        int col = 0;
        int offset = 0;
        for(int i = 0; i < width * height; i++) {
            spiral.add(matrix[row][col]);
            if(row == offset && col != width - offset - 1)
                col++;
            else if(row != height - offset - 1 && col == width - offset - 1)
                row++;
            else if(row == height - offset - 1 && col != offset)
                col--;
            else if(row != offset + 1 && col == offset)
                row--;
            else {
                offset++;
                col++;
            }
        }

        return spiral;
    }
}
