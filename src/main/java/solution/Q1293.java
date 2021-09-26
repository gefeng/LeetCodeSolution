package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Shortest Path in a Grid with Obstacles Elimination",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/"
)
public class Q1293 {
    /**
     * Time:  O(M * N * K)
     * Space: O(M * N * K)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][][] visited = new boolean[m][n][k + 1];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0, k});
        Arrays.fill(visited[0][0], true);

        int d = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                int r = curr[0];
                int c = curr[1];
                int left = curr[2];

                if(r == m - 1 && c == n - 1) {
                    return d;
                }

                for(int[] dir : DIRECTIONS) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if(nr >= 0 && nc >= 0 && nr < m && nc < n) {
                        if(grid[nr][nc] == 1 && left == 0) {
                            continue;
                        }

                        int nLeft = grid[nr][nc] == 1 ? left - 1 : left;

                        if(!visited[nr][nc][nLeft]) {
                            queue.offer(new int[] {nr, nc, nLeft});
                            visited[nr][nc][nLeft] = true;
                        }
                    }
                }
            }

            d++;
        }

        return -1;
    }
}
