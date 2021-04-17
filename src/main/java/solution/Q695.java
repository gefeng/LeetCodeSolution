package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Max Area of Island",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/max-area-of-island/"
)
public class Q695 {
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1)
                    maxArea = Math.max(maxArea, bfsArea(grid, i, j));
            }
        }

        return maxArea;
    }

    private int bfsArea(int[][] grid, int row, int col) {
        int area = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {row, col});
        grid[row][col] = 0;
        while(!queue.isEmpty()) {
            int[] pos = queue.poll();
            area++;

            for(int[] dir : DIRECTIONS) {
                int nr = pos[0] + dir[0];
                int nc = pos[1] + dir[1];
                if(nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length && grid[nr][nc] == 1) {
                    queue.offer(new int[] {nr, nc});
                    grid[nr][nc] = 0;
                }
            }
        }

        return area;
    }

    private int dfsArea(int[][] grid, int row, int col) {
        grid[row][col] = 0;

        int area = 0;
        for(int[] dir : DIRECTIONS ) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if(nextRow >= 0 && nextCol >= 0 && nextRow < grid.length && nextCol < grid[0].length && grid[nextRow][nextCol] == 1)
                area += dfsArea(grid, nextRow, nextCol);
        }

        return area + 1;
    }
}
