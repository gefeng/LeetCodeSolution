package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Bricks Falling When Hit",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/bricks-falling-when-hit/"
)
public class Q803 {
    /**
     * Add bricks back.
     *
     * Time:  O(M * N + Q)
     * Space: O(M * N + Q)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length;
        int n = grid[0].length;
        int len = hits.length;
        int[] ans = new int[len];
        Arrays.fill(ans, -1);

        for(int i = 0; i < len; i++) {
            int[] hit = hits[i];
            if(grid[hit[0]][hit[1]] == 0) ans[i] = 0;
            else grid[hit[0]][hit[1]] = 0;
        }

        int wall = m * n + n + 2;
        DJSet djs = new DJSet(m * n + n + 5);
        djs.setW(wall, wall);

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    if(i == 0) {
                        djs.union(i * n + j, wall);
                    } else {
                        for(int[] dir : DIRECTIONS) {
                            int ni = i + dir[0];
                            int nj = j + dir[1];
                            if(isValid(ni, nj, m, n) && grid[ni][nj] == 1) {
                                djs.union(i * n + j, ni * n + nj);
                            }
                        }
                    }
                }
            }
        }

        for(int i = len - 1; i >= 0; i--) {
            if(ans[i] != -1) continue;
            int r = hits[i][0];
            int c = hits[i][1];

            Set<Integer> groups = new HashSet<>();
            for(int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if(isValid(nr, nc, m, n) && grid[nr][nc] == 1) {
                    groups.add(djs.find(nr * n + nc));
                }
            }

            boolean hasWall = r == 0;
            int size = 0;
            for(int x : groups) {
                if(x == wall) hasWall = true;
                else size += djs.size(x);
            }

            if(hasWall) {
                ans[i] = size;
            } else {
                ans[i] = 0;
            }

            for(int x : groups) {
                djs.union(r * n + c, x);
            }

            grid[r][c] = 1;
            if(r == 0) djs.union(r * n + c, wall);
        }

        return ans;
    }

    private boolean isValid(int r, int c, int m, int n) {
        return r >= 0 && c >= 0 && r < m && c < n;
    }

    private class DJSet {
        int[] p;
        int[] w;
        DJSet(int n) {
            p = new int[n];
            w = new int[n];
            Arrays.fill(p, -1);
            Arrays.fill(w, 1);
        }

        void setW(int i, int weight) {
            w[i] = weight;
        }

        int find(int i) {
            if(p[i] < 0) return i;
            return p[i] = find(p[i]);
        }

        void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) return;

            if(w[x] >= w[y]) {
                p[y] = x;
                w[x] += w[y];
            } else {
                p[x] = y;
                w[y] += w[x];
            }
        }

        int size(int i) {
            return w[find(i)];
        }
    }
}
