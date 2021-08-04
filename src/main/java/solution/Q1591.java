package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "Strange Printer II",
        difficulty = QDifficulty.HARD,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/strange-printer-ii/"
)
public class Q1591 {
    /**
     * Since it can only print rectangle, we can find boarder of each color by
     * searching top left corner and bottom right corner.
     *
     * Time:  O(C * M * N)
     * Space: O(C * M * N)
     * */
    public boolean isPrintable(int[][] targetGrid) {
        int m = targetGrid.length;
        int n = targetGrid[0].length;
        List<Integer>[] adj = new List[61];
        int[] indegree = new int[61];

        for(int c = 1; c < 61; c++) {
            adj[c] = new ArrayList<>();
        }

        for(int c = 1; c < 61; c++) {
            int maxr = 0;
            int maxc = 0;
            int minr = Integer.MAX_VALUE;
            int minc = Integer.MAX_VALUE;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(targetGrid[i][j] == c) {
                        maxr = Math.max(maxr, i);
                        maxc = Math.max(maxc, j);
                        minr = Math.min(minr, i);
                        minc = Math.min(minc, j);
                    }
                }
            }

            for(int i = minr; i <= maxr; i++) {
                for(int j = minc; j <= maxc; j++) {
                    if(targetGrid[i][j] != c) {
                        adj[c].add(targetGrid[i][j]);
                        indegree[targetGrid[i][j]]++;
                    }
                }
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for(int c = 1; c < 61; c++) {
            if(indegree[c] == 0) {
                queue.offer(c);
            }
        }

        int cnt = 0;
        while(!queue.isEmpty()) {
            int c = queue.poll();
            cnt++;

            for(int nc : adj[c]) {
                indegree[nc]--;
                if(indegree[nc] == 0) {
                    queue.offer(nc);
                }
            }
        }

        return cnt == 60;
    }
}
