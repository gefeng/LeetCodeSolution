package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Minimum Cost to Make at Least One Valid Path in a Grid",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/"
)
public class Q1368 {
    /**
     * Time:  O(M * N * log(M * N))
     * Space: O(M * N)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minCost(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;

        int[][] best = new int[m][n];
        for(int i = 0; i < m; i++) {
            Arrays.fill(best[i], Integer.MAX_VALUE);
        }

        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        minHeap.offer(new int[] {0, 0, 0});
        best[0][0] = 0;

        while(!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int r = cur[0];
            int c = cur[1];
            int d = cur[2];

            if(r == m - 1 && c == n - 1) {
                ans = d;
                break;
            }

            for(int i = 0; i < 4; i++) {
                int[] dir = DIRECTIONS[i];
                int nr = r + dir[0];
                int nc = c + dir[1];

                if(nr >= 0 && nc >= 0 && nr < m && nc < n) {
                    int cost = grid[r][c] == i + 1 ? 0 : 1;
                    if(d + cost < best[nr][nc]) {
                        minHeap.offer(new int[] {nr, nc, d + cost});
                        best[nr][nc] = d + cost;
                    }
                }
            }
        }

        return ans;
    }
}
