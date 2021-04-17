package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Path With Maximum Minimum Value",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/path-with-maximum-minimum-value/"
)
public class Q1102 {
    /*
    * 这题无法用dp解，违反了dp的无后效性
    * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int maximumMinimumPath(int[][] A) {
        Queue<int[]> heap = new PriorityQueue<>((a, b) -> (b[2] - a[2]));
        boolean[][] visited = new boolean[A.length][A[0].length];
        int pathScore = Integer.MAX_VALUE;

        heap.offer(new int[] {0, 0, A[0][0]});
        visited[0][0] = true;
        while(!heap.isEmpty()) {
            int[] cell = heap.poll();
            int row = cell[0];
            int col = cell[1];
            int score = cell[2];
            pathScore = Math.min(pathScore, cell[2]);

            if(row == A.length - 1 && col == A[0].length - 1)
                break;

            for(int[] dir : DIRECTIONS) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                if(nextRow >= 0 && nextCol >= 0 && nextRow < A.length && nextCol < A[0].length &&
                        !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    heap.offer(new int[] {nextRow, nextCol, A[nextRow][nextCol]});
                }
            }
        }

        return pathScore;
    }
}
