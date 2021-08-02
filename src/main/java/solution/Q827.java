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
    private class DSU {
        private int[] parent;
        private int[] weight;
        DSU(int n) {
            parent = new int[n];
            weight = new int[n];

            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }

            Arrays.fill(weight, 1);
        }

        int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) {
                return;
            }

            if(weight[x] == weight[y]) {
                parent[y] = x;
                weight[x] += weight[y];
            } else if(weight[x] > weight[y]) {
                parent[y] = x;
                weight[x] += weight[y];
            } else {
                parent[x] = y;
                weight[y] += weight[x];
            }
        }

        int getChildren(int i) {
            return weight[i];
        }
    }
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int res = 0;
        DSU dsu = new DSU(n * n);

        boolean[][] visited = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, i, j, dsu, visited);
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    Set<Integer> islands = new HashSet<>();
                    for(int[] dir : DIRECTIONS) {
                        int nr = i + dir[0];
                        int nc = j + dir[1];
                        if(nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] == 1) {
                            islands.add(dsu.find(nr * n + nc));
                        }
                    }

                    int area = 1;
                    for(int island : islands) {
                        area += dsu.getChildren(island);
                    }
                    res = Math.max(res, area);
                }
            }
        }

        return res == 0 ? n * n : res;
    }

    private void dfs(int[][] grid, int r, int c, DSU dsu, boolean[][] visited) {
        int n = grid.length;

        visited[r][c] = true;

        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] == 1 && !visited[nr][nc]) {
                dsu.union(r * n + c , nr * n + nc);
                dfs(grid, nr, nc, dsu, visited);
            }
        }
    }
}
