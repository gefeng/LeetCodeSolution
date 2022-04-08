package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Trapping Rain Water II",
        difficulty = QDifficulty.HARD,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/trapping-rain-water-ii/"
)
public class Q407 {
    /**
     * Time:  O(M * N * log(M * N))
     * Space: O(M * N)
     * */
    private int[][] directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int ans = 0;

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < n; i++) {
            pq.offer(new int[] {0, i, heightMap[0][i]});
            pq.offer(new int[] {m - 1, i, heightMap[m - 1][i]});
            visited[0][i] = true;
            visited[m - 1][i] = true;
        }

        for(int i = 1; i < m - 1; i++) {
            pq.offer(new int[] {i, 0, heightMap[i][0]});
            pq.offer(new int[] {i, n - 1, heightMap[i][n - 1]});
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0], c = cur[1], h = cur[2];

            ans += h - heightMap[r][c];

            for(int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if(nr >= 0 && nc >= 0 && nr < m && nc < n && !visited[nr][nc]) {
                    pq.offer(new int[] {nr, nc, Math.max(heightMap[nr][nc], h)});
                    visited[nr][nc] = true;
                }
            }
        }

        return ans;
    }
}
