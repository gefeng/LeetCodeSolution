package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Shortest Path to Get All Keys",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/shortest-path-to-get-all-keys/"
)
public class Q864 {
    /**
     * The idea is to do BFS to find shortest path.
     * Add a dimension to save keys we got from this path.
     *
     * Time:  O(M * N * (1 << 6))
     * Space: O(M * N * (1 << 6))
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        char[][] g = new char[m][n];

        int sr = 0;
        int sc = 0;
        int tot = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                g[i][j] = grid[i].charAt(j);
                if(g[i][j] == '@') {
                    sr = i;
                    sc = j;
                } else if(g[i][j] >= 'a' && g[i][j] <= 'f') {
                    tot++;
                }
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] seen = new boolean[m][n][1 << tot];
        q.offer(new int[] {sr, sc, 0});
        seen[sr][sc][0] = true;

        int steps = 0;
        while(!q.isEmpty()) {
            int sz = q.size();
            for(int i = 0; i < sz; i++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                int k = cur[2];
                if(k == (1 << tot) - 1) return steps;

                for(int[] dir : DIRECTIONS) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    int nk = k;
                    if(nr >= 0 && nc >= 0 && nr < m && nc < n && g[nr][nc] != '#') {
                        if(g[nr][nc] >= 'a' && g[nr][nc] <= 'f') {
                            nk = k | (1 << g[nr][nc] - 'a');
                        } else if(g[nr][nc] >= 'A' && g[nr][nc] <= 'F') {
                            if(((1 << g[nr][nc] - 'A') & k) == 0) {
                                continue;
                            }
                        }

                        if(!seen[nr][nc][nk]) {
                            q.offer(new int[] {nr, nc, nk});
                            seen[nr][nc][nk] = true;
                        }
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}
