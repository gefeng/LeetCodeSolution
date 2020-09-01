package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "The Maze",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/the-maze/"
)
public class Q490 {
    private int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }

    private boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        if(visited[start[0]][start[1]])
            return false;
        if(start[0] == destination[0] && start[1] == destination[1])
            return true;

        visited[start[0]][start[1]] = true;

        for(int[] dir : directions) {
            int prevRow = start[0];
            int prevCol = start[1];
            int newRow = start[0] + dir[0];
            int newCol = start[1] + dir[1];
            while(newRow >= 0 && newRow < maze.length && newCol >= 0 && newCol < maze[0].length && maze[newRow][newCol] != 1) {
                prevRow = newRow;
                prevCol = newCol;
                newRow += dir[0];
                newCol += dir[1];
            }

            if(dfs(maze, new int[] {prevRow, prevCol}, destination, visited))
                return true;
        }

        return false;
    }
}
