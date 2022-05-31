package solution.weekly295;

import java.util.*;
import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Obstacle Removal to Reach Corner",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/contest/weekly-contest-295/problems/minimum-obstacle-removal-to-reach-corner/"
)
public class Q2290 {
    /**
     * This is called 0-1 BFS
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dr = new int[] {0, 1, -1, 0};
        int[] dc = new int[] {1, 0, 0, -1};
        int[][] best = new int[m][n];
        Deque<int[]> deq = new ArrayDeque<>();

        for(int i = 0; i < m; i++) {
            Arrays.fill(best[i], Integer.MAX_VALUE);
        }

        deq.offerLast(new int[] {0, 0});
        best[0][0] = 0;

        while(!deq.isEmpty()) {
            int[] cur = deq.pollFirst();
            int r = cur[0];
            int c = cur[1];

            for(int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr >= 0 && nc >= 0 && nr < m && nc < n) {
                    if(grid[nr][nc] == 0 && best[nr][nc] > best[r][c]) {
                        best[nr][nc] = best[r][c];
                        deq.offerFirst(new int[] {nr, nc});
                    }
                    if(grid[nr][nc] == 1 && best[nr][nc] > best[r][c] + 1) {
                        best[nr][nc] = best[r][c] + 1;
                        deq.offerLast(new int[] {nr, nc});
                    }
                }
            }
        }

        return best[m - 1][n - 1];
    }
}
