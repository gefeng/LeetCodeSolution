package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Island Perimeter",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/island-perimeter/"
)
public class Q463 {
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int islandPerimeter(int[][] grid) {
        return checkTwoDir(grid);
    }

    private int checkFourDir(int[][] grid) {
        int perimeter = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    for(int[] dir : DIRECTIONS) {
                        int nr = i + dir[0];
                        int nc = j + dir[1];
                        if(nr < 0 || nc < 0 || nr > m - 1 || nc > n - 1 || grid[nr][nc] == 0) {
                            perimeter++;
                        }
                    }
                }
            }
        }

        return perimeter;
    }

    private int checkTwoDir(int[][] grid) {
        int perimeter = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    perimeter += 4;
                    if(i > 0 && grid[i - 1][j] == 1) {
                        perimeter -= 2;
                    }
                    if(j > 0 && grid[i][j - 1] == 1) {
                        perimeter -= 2;
                    }
                }
            }
        }

        return perimeter;
    }
}
