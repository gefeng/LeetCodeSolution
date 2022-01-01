package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Swim in Raising Water",
        difficulty = QDifficulty.HARD,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/swim-in-rising-water/"
)
public class Q778 {
    /**
     * Time:  O(N ^ 2 * log(N ^ 2))
     * Space: O(N ^ 2)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int swimInWater(int[][] grid) {
        int n = grid.length;

        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        boolean[][] visited = new boolean[n][n];

        minHeap.offer(new int[] {0, 0, grid[0][0]});
        visited[0][0] = true;

        int time = 0;
        while(!minHeap.isEmpty()) {
            int[] pos = minHeap.poll();
            if(pos[0] == n - 1 && pos[1] == n - 1) {
                time = pos[2];
                break;
            }

            for(int[] dir : DIRECTIONS) {
                int nr = pos[0] + dir[0];
                int nc = pos[1] + dir[1];
                if(nr >= 0 && nc >= 0 && nr < n && nc < n && !visited[nr][nc]) {
                    minHeap.offer(new int[] {nr, nc, Math.max(pos[2], grid[nr][nc])});
                    visited[nr][nc] = true;
                }
            }
        }

        return time;
    }
}
