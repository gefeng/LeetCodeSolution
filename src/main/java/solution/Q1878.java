package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Get Biggest Three Rhombus Sums in a Grid",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/get-biggest-three-rhombus-sums-in-a-grid/"
)
public class Q1878 {
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int[] getBiggestThree(int[][] grid) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Set<Integer> seenSum = new HashSet<>();

        int m = grid.length;
        int n = grid[0].length;


        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                bfs(grid, i, j, minHeap, seenSum);
            }
        }

        int[] ans = new int[minHeap.size()];
        int i = ans.length - 1;
        while(!minHeap.isEmpty()) {
            ans[i--] = minHeap.poll();
        }
        return ans;
    }

    private void bfs(int[][] grid, int sr, int sc, PriorityQueue<Integer> heap, Set<Integer> seenSum) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        queue.offer(new int[] {sr, sc});
        visited[sr][sc] = true;

        while(!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            boolean keepGoing = true;
            for(int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                int r = pos[0];
                int c = pos[1];
                sum += grid[r][c];
                for(int[] dir : DIRECTIONS) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr < 0 || nc < 0 || nr > m - 1 || nc > n - 1) {
                        keepGoing = false;
                        break;
                    }
                    if(keepGoing) {
                        if(visited[nr][nc]) {
                            continue;
                        }
                        queue.offer(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }

            if(!seenSum.contains(sum)) {
                heap.offer(sum);
                seenSum.add(sum);
            }

            if(heap.size() > 3) {
                heap.poll();
            }
            if(!keepGoing) {
                break;
            }
        }
    }
}
