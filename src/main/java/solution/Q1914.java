package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Cyclically Rotating a Grid",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/cyclically-rotating-a-grid/"
)
public class Q1914 {
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int l = Math.min(m, n) / 2;

        for(int i = 0; i < l; i++) {
            int tr = i;
            int tc = i;
            int br = m - i - 1;
            int bc = n - i - 1;
            int size = 2 * (br - tr + 1) + 2 * (bc - tc + 1 - 2);
            int kk = k % size;
            int[] arr = new int[size];
            int j = 0;
            for(int c = tc; c <= bc - 1; c++) {
                arr[j++] = grid[tr][c];
            }
            for(int r = tr; r <= br - 1; r++) {
                arr[j++] = grid[r][bc];
            }
            for(int c = bc; c >= tc + 1; c--) {
                arr[j++] = grid[br][c];
            }
            for(int r = br; r >= tr + 1; r--) {
                arr[j++] = grid[r][tc];
            }

            j = 0;
            for(int c = tc; c <= bc - 1; c++) {
                grid[tr][c] = arr[(j + kk) % size];
                j++;
            }
            for(int r = tr; r <= br - 1; r++) {
                grid[r][bc] = arr[(j + kk) % size];
                j++;
            }
            for(int c = bc; c >= tc + 1; c--) {
                grid[br][c] = arr[(j + kk) % size];
                j++;
            }
            for(int r = br; r >= tr + 1; r--) {
                grid[r][tc] = arr[(j + kk) % size];
                j++;
            }
        }

        return grid;
    }
}
