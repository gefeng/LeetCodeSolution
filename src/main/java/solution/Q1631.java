package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Path With Minimum Effort",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/path-with-minimum-effort/"
)
public class Q1631 {
    /**
     * Dijkstra, treat max effort of a path as distance
     *
     * Time:  O(E * logV)
     * Space: O(M * N)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        int[][] efforts = new int[m][n];
        for(int i = 0; i < m; i++) {
            Arrays.fill(efforts[i], Integer.MAX_VALUE);
        }

        queue.offer(new int[] {0, 0, 0});
        efforts[0][0] = 0;
        while(!queue.isEmpty()) {
            int[] pos = queue.poll();
            int r = pos[0];
            int c = pos[1];
            int e = pos[2];

            if(r == m - 1 && c == n - 1) {
                break;
            }

            for(int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if(nr >= 0 && nc >= 0 && nr < m && nc < n) {
                    int ne = Math.max(e, Math.abs(heights[nr][nc] - heights[r][c]));
                    if(ne < efforts[nr][nc]) {
                        efforts[nr][nc] = ne;
                        queue.offer(new int[] {nr, nc, ne});
                    }
                }
            }
        }

        return efforts[m - 1][n - 1];
    }
}
