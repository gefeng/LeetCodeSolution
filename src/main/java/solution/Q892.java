package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Surface Area of 3D Shapes",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/surface-area-of-3d-shapes/"
)
public class Q892 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(1)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int surfaceArea(int[][] grid) {
        int n = grid.length;
        int ans = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] != 0) {
                    int area = 2 + 4 * grid[i][j];

                    for(int[] dir : DIRECTIONS) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if(ni >= 0 && nj >= 0 && ni < n && nj < n) {
                            area -= Math.min(grid[i][j], grid[ni][nj]);
                        }
                    }

                    ans += area;
                }
            }
        }

        return ans;
    }
}
