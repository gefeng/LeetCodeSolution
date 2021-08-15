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
     * */
    public int latestDayToCross(int row, int col, int[][] cells) {
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

    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
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
}
