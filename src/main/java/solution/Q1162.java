package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "As Far from Land as Possible",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/as-far-from-land-as-possible/"
)
public class Q1162 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        int ans = -1;

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    q.offer(new int[] {i, j});
                    visited[i][j] = true;
                }
            }
        }

        int d = 0;
        while(!q.isEmpty()) {
            int len = q.size();
            for(int i = 0; i < len; i++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];

                if(grid[r][c] == 0) {
                    ans = d;
                }

                for(int[] dir : DIRECTIONS) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr >= 0 && nc >= 0 && nr < n && nc < n && !visited[nr][nc]) {
                        q.offer(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            d++;
        }

        return ans;
    }
}
