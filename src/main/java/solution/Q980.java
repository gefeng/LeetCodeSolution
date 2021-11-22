package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Unique Paths III",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/unique-paths-iii/"
)
public class Q980 {
    /**
     * Time:  O(3 ^ (M * N))
     * Space: O(M * N)
     * */
    private int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int gridSize = 0;
    int numOfObstacles = 0;
    int ans = 0;
    public int uniquePathsIII(int[][] grid) {
        Set<Integer> visited = new HashSet<>();
        int[] startPos = new int[2];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == -1)
                    numOfObstacles++;
                else if(grid[i][j] == 1) {
                    startPos[0] = i;
                    startPos[1] = j;
                }
            }
        }

        gridSize = grid.length * grid[0].length;

        backTrack(grid, visited, startPos[0], startPos[1]);

        return ans;
    }

    private void backTrack(int[][] grid, Set<Integer> visited, int row, int col) {
        if(grid[row][col] == 2) {
            if((visited.size() + numOfObstacles) == (gridSize - 1))
                ans++;
            return;
        }

        int key = row * grid[0].length + col;
        visited.add(key);

        for(int i = 0; i < 4; i++) {
            int[] dir = directions[i];
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            int newKey = newRow * grid[0].length + newCol;
            if(newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && grid[newRow][newCol] != -1 && !visited.contains(newKey)) {
                backTrack(grid, visited, newRow, newCol);
            }
        }

        visited.remove(key);
    }
}
