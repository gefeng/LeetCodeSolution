package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

@Problem(
        title = "Shortest Bridge",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/shortest-bridge/"
)
public class Q934 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    int n;
    int[][] g;
    Set<Integer> isl;
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestBridge(int[][] grid) {
        this.g = grid;
        this.n = g.length;
        this.isl = new HashSet<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    dfs(i, j);
                    break;
                }
            }
            if(!isl.isEmpty()) {
                break;
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];

        for(int x : isl) {
            int r = x / n;
            int c = x % n;
            q.offer(new int[] {r, c});
            visited[r][c] = true;
        }

        int ans = 0;
        while(!q.isEmpty()) {
            int sz = q.size();
            for(int i = 0; i < sz; i++) {
                int[] pos = q.poll();
                int r = pos[0];
                int c = pos[1];
                int k = r * n + c;

                if(g[r][c] == 1 && !isl.contains(k)) {
                    return ans - 1;
                }

                for(int[] dir : DIRECTIONS) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    int nk = nr * n + nc;
                    if(nr >= 0 && nc >= 0 && nr < n && nc < n && !visited[nr][nc]) {
                        q.offer(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            ans++;
        }

        return -1;
    }

    private void dfs(int r, int c) {
        isl.add(r * n + c);

        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < n && nc < n && g[nr][nc] == 1 && !isl.contains(nr * n + nc)) {
                dfs(nr, nc);
            }
        }
    }
}
