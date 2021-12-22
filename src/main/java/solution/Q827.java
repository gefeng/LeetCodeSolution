package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Making a Large Island",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/making-a-large-island/"
)
public class Q827 {
    /**
     * Time:  O(N * N)
     * Space: O(N * N)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int largestIsland(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        DJSet djs = new DJSet(n * n + n + 1);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    for(int[] dir : DIRECTIONS) {
                        int nr = i + dir[0];
                        int nc = j + dir[1];
                        if(nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] == 1) {
                            djs.union(i * n + j, nr * n + nc);
                        }
                    }
                    ans = Math.max(ans, djs.size(i * n + j));
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    int area = 1;
                    Set<Integer> groups = new HashSet<>();
                    for(int[] dir : DIRECTIONS) {
                        int nr = i + dir[0];
                        int nc = j + dir[1];
                        if(nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] == 1) {
                            groups.add(djs.find(nr * n + nc));
                        }
                    }

                    for(int g : groups) {
                        area += djs.size(g);
                    }

                    ans = Math.max(area, ans);
                }
            }
        }

        return ans;
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
