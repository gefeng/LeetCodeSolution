package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Projection Area of 3D Shapes",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/projection-area-of-3d-shapes/"
)
public class Q883 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(1)
     * */
    public int projectionArea(int[][] grid) {
        int n = grid.length;
        int area = 0;

        for(int i = 0; i < n; i++) {
            int max = 0;
            for(int j = 0; j < n; j++) {
                max = Math.max(max, grid[i][j]);
                area += grid[i][j] != 0 ? 1 : 0;
            }
            area += max;
        }

        for(int i = 0; i < n; i++) {
            int max = 0;
            for(int j = 0; j < n; j++) {
                max = Math.max(max, grid[j][i]);
            }
            area += max;
        }

        return area;
    }
}
