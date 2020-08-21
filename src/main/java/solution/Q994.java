package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Rotting Oranges",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/rotting-oranges/"
)
public class Q994 {
    public int orangesRotting(int[][] grid) {
        int[][] directions = new int[][] { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 2)
                    queue.offer(new int[] {i, j});
                else if(grid[i][j] == 1)
                    freshCount++;
            }
        }

        int mins = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                for(int[] dir : directions) {
                    int newRow = pos[0] + dir[0];
                    int newCol = pos[1] + dir[1];
                    if(newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        queue.offer(new int[] { newRow, newCol });
                        freshCount--;
                    }
                }
            }
            mins++;
        }

        if(freshCount != 0)
            return -1;

        return mins == -1 ? 0 : mins;
    }
}
