package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Number of Islands",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/number-of-islands/"
)
public class Q200 {
    private final char LAND = '1';
    private final char WATER = '0';
    private final char VISITED = '2';
    private final int[][] DIRECTION = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };
    public int numIslands(char[][] grid) {
        int height = grid.length;
        if(height == 0)
            return 0;
        int width = grid[0].length;
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(grid[i][j] == LAND) {
                    queue.offer(i * width + j);
                    grid[i][j] = VISITED; // Mark as visited
                    while (!queue.isEmpty()) {
                        int index = queue.poll();
                        int row = index / width;
                        int col = index % width;
                        for (int[] dir : DIRECTION) {
                            int adjRow = row + dir[0];
                            int adjCol = col + dir[1];
                            if (adjRow < 0 || adjCol < 0 || adjRow >= height || adjCol >= width || grid[adjRow][adjCol] != LAND)
                                continue;
                            grid[adjRow][adjCol] = VISITED; // Mark as visited
                            queue.offer(adjRow * width + adjCol);
                        }
                    }
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int row, int col) {
        if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != '1')
            return;
        grid[row][col] = '0';
        dfs(grid, row + 1, col);
        dfs(grid, row - 1, col);
        dfs(grid, row, col + 1);
        dfs(grid, row, col - 1);
    }
    public int numIslandsDFS(char[][] grid) {
        int count = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }
}
