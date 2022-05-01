package solution.biweekly77;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

@Problem(
        title = "Escape the Spreading Fire",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/contest/biweekly-contest-77/problems/escape-the-spreading-fire/"
)
public class Q2258 {
    /**
     * Time:  O(M * N * log(M * N))
     * Space: O(M * N)
     * */
    int[][] DIRS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int maximumMinutes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;


        int lo = 0;
        int hi = m * n;
        int ans = -1;
        while(lo <= hi) {
            int mid = lo + hi >> 1;

            int[][] g = new int[m][n];
            for(int i = 0; i < m; i++) {
                g[i] = Arrays.copyOf(grid[i], n);
            }
            if(isOk(g, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans == m * n ? (int)1e9 : ans;
    }

    private boolean isOk(int[][] grid, int wt) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> qf = new ArrayDeque<>();
        Queue<int[]> qp = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    qf.offer(new int[] {i, j});
                }
            }
        }

        qp.offer(new int[] {0, 0});
        visited[0][0] = true;

        int t = 0;

        while(!qp.isEmpty()) {
            if(t <= wt && grid[0][0] == 1) {
                return false;
            }

            if(t >= wt) {
                int sz = qp.size();

                for(int i = 0; i < sz; i++) {
                    int[] p = qp.poll();
                    int r = p[0];
                    int c = p[1];

                    for(int[] dir : DIRS) {
                        int nr = r + dir[0];
                        int nc = c + dir[1];
                        if(nr >= 0 && nc >= 0 && nr < m && nc < n && !visited[nr][nc] && grid[nr][nc] == 0) {
                            if(nr == m - 1 && nc == n - 1) {
                                return true;
                            }

                            if(!surrounded(grid, nr, nc)) {
                                qp.offer(new int[] {nr, nc});
                                visited[nr][nc] = true;
                            }
                        }
                    }
                }
            }
            if(!qf.isEmpty()) {
                int sz = qf.size();

                for(int i = 0; i < sz; i++) {
                    int[] f = qf.poll();
                    int r = f[0];
                    int c = f[1];
                    for(int[] dir : DIRS) {
                        int nr = r + dir[0];
                        int nc = c + dir[1];
                        if(nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] == 0) {
                            grid[nr][nc] = 1;
                            qf.offer(new int[] {nr, nc});
                        }
                    }
                }
            }

            t++;
        }

        return false;
    }

    private boolean surrounded(int[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        for(int[] dir : DIRS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] == 1) {
                return true;
            }
        }

        return false;
    }
}
