package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Minesweeper",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/minesweeper/"
)
public class Q529 {
    private final static int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0];
        int col = click[1];
        if(board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }

        dfsReveal(board, row, col);

        return board;
    }

    private void dfsReveal(char[][] board, int row, int col) {
        board[row][col] = 'B';

        int count = countMines(board, row, col);

        if(count == 0) {
            for(int[] dir : DIRECTIONS) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                if(nextRow >= 0 && nextCol >= 0 && nextRow < board.length && nextCol < board[0].length &&
                        board[nextRow][nextCol] == 'E') {
                    dfsReveal(board, nextRow, nextCol);
                }
            }
        } else {
            board[row][col] = (char)('0' + count);
        }
    }

    private void bfsReveal(char[][] board, int row, int col) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {row, col});
        board[row][col] = 'B';

        while(!queue.isEmpty()) {
            int[] pos = queue.poll();
            int r = pos[0];
            int c = pos[1];
            int count = countMines(board, r, c);

            if(count != 0) {
                board[r][c] = (char)('0' + count);
                continue;
            }

            for(int[] dir : DIRECTIONS) {
                int nRow = r + dir[0];
                int nCol = c + dir[1];
                if(nRow >= 0 && nCol >= 0 && nRow < board.length && nCol < board[0].length) {
                    if(board[nRow][nCol] == 'E') {
                        board[nRow][nCol] = 'B';
                        queue.offer(new int[] {nRow, nCol});
                    }
                }
            }
        }
    }

    private int countMines(char[][] board, int row, int col) {
        int count = 0;
        for(int[] dir : DIRECTIONS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if(nextRow >= 0 && nextCol >= 0 && nextRow < board.length && nextCol < board[0].length) {
                if(board[nextRow][nextCol] == 'M')
                    count++;
            }
        }

        return count;
    }
}
