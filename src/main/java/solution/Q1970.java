package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Last Day Where You Can Still Cross",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/last-day-where-you-can-still-cross/"
)
public class Q1970 {
    /**
     * Approach1: Binary Search + BFS
     * Time:  O(logN * R * C)
     * Space: O(R * C)
     *
     * Approach2: DSU
     * Time:  O(R * C)
     * Space: O(R * C)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int latestDayToCross(int row, int col, int[][] cells) {
        return dsuSol(row, col, cells);
    }

    private int binarySearchBFSSol(int row, int col, int[][] cells) {
        int n = cells.length;
        int lo = 1;
        int hi = n;
        int res = 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(canCross(cells, row, col, mid)) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return res;
    }
    private boolean canCross(int[][] cells, int row, int col, int day) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[row][col];
        int[][] g = new int[row][col];

        for(int i = 0; i < day; i++) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            g[r][c] = 1;
        }

        for(int i = 0; i < col; i++) {
            if(g[0][i] == 0) {
                queue.offer(new int[] {0, i});
                visited[0][i] = true;
            }
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] pos = queue.poll();

                if(pos[0] == row - 1) {
                    return true;
                }

                for(int[] dir : DIRECTIONS) {
                    int nr = dir[0] + pos[0];
                    int nc = dir[1] + pos[1];
                    if(nr >= 0 && nc >= 0 && nr < row && nc < col && g[nr][nc] != 1 && !visited[nr][nc]) {
                        queue.offer(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
        return false;
    }

    private class DSU {
        private int[] p;
        private int[] w;
        DSU(int n) {
            p = new int[n];
            w = new int[n];
            for(int i = 0; i < n; i++) {
                p[i] = i;
            }
        }

        int find(int i) {
            if(p[i] != i) {
                p[i] = find(p[i]);
            }
            return p[i];
        }

        void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) {
                return;
            }

            if(w[x] == w[y]) {
                p[y] = x;
                w[x]++;
            } else if(w[x] > w[y]) {
                p[y] = x;
            } else {
                p[x] = y;
            }
        }
    }
    private int dsuSol(int row, int col, int[][] cells) {
        int n = cells.length;
        int[][] grid = new int[row][col];
        DSU dsu = new DSU(row * col + 2);

        for(int[] c : cells) {
            grid[c[0] - 1][c[1] - 1] = 1;
        }

        for(int i = n - 1; i >= 0; i--) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            grid[r][c] = 0;

            for(int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if(nr >= 0 && nc >= 0 && nr < row && nc < col && grid[nr][nc] == 0) {
                    dsu.union(r * col + c + 1, nr * col + nc + 1);
                }
            }

            if(r == 0) {
                dsu.union(r * col + c + 1, 0);
            }
            if(r == row - 1) {
                dsu.union(r * col + c + 1, row * col - 1);
            }

            if(dsu.find(0) == dsu.find(row * col - 1)) {
                return i;
            }
        }

        return -1;
    }
}
