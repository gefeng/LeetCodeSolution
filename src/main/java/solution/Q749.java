package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Contain Virus",
        difficulty = QDifficulty.HARD,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/contain-virus/"
)
public class Q749 {
    /**
     * Time:  O(M * N * log(M * N))
     * Space: O(M * N)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int[][] g;
    private int m;
    private int n;
    public int containVirus(int[][] isInfected) {
        g = isInfected;
        m = g.length;
        n = g[0].length;
        int ans = 0;

        while(true) {
            boolean[][] visited = new boolean[m][n];
            int max = 0;
            int r = -1, c = -1;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(g[i][j] == 1 && !visited[i][j]) {
                        int cnt = dfs(g, visited, new HashSet<>(), i, j);
                        if(cnt > max) {
                            max = cnt;
                            r = i;
                            c = j;
                        }
                    }
                }
            }

            if(r == -1) return ans;

            int walls = block(g, r, c);

            ans += walls;

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(g[i][j] == 1) {
                        for(int[] dir : DIRECTIONS) {
                            int ni = i + dir[0];
                            int nj = j + dir[1];
                            if(isValid(ni, nj) && g[ni][nj] == 0) {
                                g[ni][nj] = 3;
                            }
                        }
                    }
                }
            }

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(g[i][j] == 3) {
                        g[i][j] = 1;
                    }
                }
            }
        }
    }

    private int block(int[][] g, int r, int c) {
        g[r][c] = 2;
        int walls = 0;
        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(isValid(nr, nc) && g[nr][nc] == 0) {
                walls++;
            }
        }

        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(isValid(nr, nc) && g[nr][nc] == 1) {
                walls += block(g, nr, nc);
            }
        }

        return walls;
    }

    private int dfs(int[][] g, boolean[][] visited, Set<Integer> infected, int r, int c) {
        visited[r][c] = true;
        int cnt = 0;
        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(isValid(nr, nc)) {
                if(g[nr][nc] == 0 && !infected.contains(nr * n + nc)) {
                    cnt++;
                    infected.add(nr * n + nc);
                } else if(g[nr][nc] == 1 && !visited[nr][nc]) {
                    cnt += dfs(g, visited, infected, nr, nc);
                }
            }
        }

        return cnt;
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < m && j < n;
    }
}
