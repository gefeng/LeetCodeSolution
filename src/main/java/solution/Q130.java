package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Surrounded Regions",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/surrounded-regions/"
)
public class Q130 {
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int m;
    private int n;
    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        for(int i = 0; i < n; i++) {
            if(board[0][i] == 'O') {
                dfsMark(board, 0, i, '1');
            }
            if(board[m - 1][i] == 'O') {
                dfsMark(board, m - 1, i, '1');
            }
        }

        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') {
                dfsMark(board, i, 0, '1');
            }
            if(board[i][n - 1] == 'O') {
                dfsMark(board, i, n - 1, '1');
            }
        }


        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    dfsMark(board, i, j, 'X');
                } else if(board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfsMark(char[][] board, int r, int c, char mark) {
        board[r][c] = mark;

        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'O') {
                dfsMark(board, nr, nc, mark);
            }
        }
    }
}
