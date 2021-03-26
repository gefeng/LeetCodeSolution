package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Snakes and Ladders",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/snakes-and-ladders/"
)
public class Q909 {
    private int N;
    public int snakesAndLadders(int[][] board) {
        N = board.length;
        int end = N * N;
        int steps = 0;
        boolean[] visited = new boolean[N * N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int cell = queue.poll();
                if(cell == end)
                    return steps;
                for(int j = 1; j <= 6; j++) {
                    int dest = move(board, cell, j);
                    if(dest <= end && !visited[dest]) {
                        queue.offer(dest);
                        visited[dest] = true;
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    private int move(int[][] board, int start, int offset) {
        int dest = start + offset;
        if(dest > N * N)
            return dest;

        int r = N - 1 - (dest - 1) / N;
        int c = 0;
        if(N % 2 == 0)
            c = r % 2 == 0 ? N - 1 - (dest - 1) % N : (dest - 1) % N;
        else
            c = r % 2 == 0 ? (dest - 1) % N : N - 1 - (dest - 1) % N;

        if(board[r][c] != -1)
            dest = board[r][c];
        return dest;
    }
}
