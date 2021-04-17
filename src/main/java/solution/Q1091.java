package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Shortest Path in Binary Matrix",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/shortest-path-in-binary-matrix/"
)
public class Q1091 {
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0});
        grid[0][0] = 1;
        int path = 1;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                int row = pos[0];
                int col = pos[1];

                if(row == n - 1 && col == n - 1)
                    return path;

                for(int[] dir : DIRECTIONS) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];
                    if(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < n &&
                            grid[nextRow][nextCol] != 1) {
                        queue.offer(new int[] {nextRow, nextCol});
                        grid[nextRow][nextCol] = 1;
                    }
                }
            }
            path++;
        }

        return -1;
    }
}
