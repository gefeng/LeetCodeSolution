package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Pacific Atlantic Water Flow",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/pacific-atlantic-water-flow/"
)
public class Q417 {
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        return reverseBfsSol(heights);
    }

    /**
     * Time:  O(M ^ 2 * N ^ 2)
     * Space: O(M * N)
     * */
    private List<List<Integer>> bfsOnEachCellSol(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<List<Integer>> res = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(bfs(heights, i, j)) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private boolean bfs(int[][] h, int r, int c) {
        int m = h.length;
        int n = h[0].length;
        boolean pac = false;
        boolean atl = false;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        queue.offer(new int[] {r, c});
        visited[r][c] = true;

        while(!queue.isEmpty()) {
            int[] pos = queue.poll();

            if(pos[0] == 0 || pos[1] == 0) {
                pac = true;
            }
            if(pos[0] == m - 1 || pos[1] == n - 1) {
                atl = true;
            }

            if(pac && atl) {
                return true;
            }

            for(int[] dir : DIRECTIONS) {
                int nr = pos[0] + dir[0];
                int nc = pos[1] + dir[1];
                if(nr >= 0 && nc >= 0 && nr < m && nc < n && !visited[nr][nc] && h[nr][nc] <= h[pos[0]][pos[1]]) {
                    queue.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }

        return false;
    }

    /**
     * We can start with cells connected to one of the oceans and reverse
     * back check contiguous increasing neighbors.
     *
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    private List<List<Integer>> reverseBfsSol(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<List<Integer>> res = new ArrayList<>();
        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        Queue<int[]> pacq = new ArrayDeque<>();
        Queue<int[]> atlq = new ArrayDeque<>();

        for(int i = 0; i < m; i++) {
            pacq.offer(new int[] {i, 0});
            pac[i][0] = true;

            atlq.offer(new int[] {i, n - 1});
            atl[i][n - 1] = true;
        }

        for(int i = 0; i < n; i++) {
            pacq.offer(new int[] {0, i});
            pac[0][i] = true;

            atlq.offer(new int[] {m - 1, i});
            atl[m - 1][i] = true;
        }

        bfs(heights, pacq, pac);
        bfs(heights, atlq, atl);

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(pac[i][j] && atl[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void bfs(int[][] h, Queue<int[]> queue, boolean[][] connected) {
        int m = h.length;
        int n = h[0].length;
        while(!queue.isEmpty()) {
            int[] pos = queue.poll();
            int r = pos[0];
            int c = pos[1];

            connected[r][c] = true;

            for(int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if(nr >= 0 && nc >= 0 && nr < m && nc < n && !connected[nr][nc] && h[nr][nc] >= h[r][c]) {
                    queue.offer(new int[] {nr, nc});
                    connected[nr][nc] = true;
                }
            }
        }
    }
}
