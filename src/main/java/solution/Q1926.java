package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Nearest Exit from Entrance in Maze",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/"
)
public class Q1926 {
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        queue.offer(entrance);
        visited[entrance[0]][entrance[1]] = true;

        int cntStep = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                int r = pos[0];
                int c = pos[1];
                if(atBorder(r, c, m, n) && (r != entrance[0] || c != entrance[1])) {
                    return cntStep;
                }

                for(int[] dir : DIRECTIONS) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if(nr < 0 || nc < 0 || nr > m - 1 || nc > n - 1 || maze[nr][nc] == '+' || visited[nr][nc]) {
                        continue;
                    }

                    queue.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
            cntStep++;
        }

        return -1;
    }

    private boolean atBorder(int r, int c, int m, int n) {
        return r == 0 || c == 0 || r == m - 1 || c == n - 1;
    }
}
