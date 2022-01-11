package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Candy Crush",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SIMULATION,
        url = "https://leetcode.com/problems/candy-crush/"
)
public class Q723 {
    /**
     * Time:  O((M * N) ^ 2)
     * Space: O(1)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {1, 0}};
    public int[][] candyCrush(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        while(true) {
            boolean stable = true;
            for(int r = 0; r < m; r++) {
                for(int i = 0; i < n;) {
                    if(board[r][i] == 0) {
                        i++;
                        continue;
                    }

                    int j = i;
                    while(j < n && Math.abs(board[r][j]) == Math.abs(board[r][i])) {
                        j++;
                    }

                    if(j - i > 2) {
                        stable = false;
                        for(int k = i; k < j; k++) {
                            board[r][k] = board[r][k] > 0 ? -board[r][k] : board[r][k];
                        }
                    }

                    i = j;
                }
            }

            for(int c = 0; c < n; c++) {
                for(int i = 0; i < m;) {
                    if(board[i][c] == 0) {
                        i++;
                        continue;
                    }

                    int j = i;
                    while(j < m && Math.abs(board[j][c]) == Math.abs(board[i][c])) {
                        j++;
                    }

                    if(j - i > 2) {
                        stable = false;
                        for(int k = i; k < j; k++) {
                            board[k][c] = board[k][c] > 0 ? -board[k][c] : board[k][c];
                        }
                    }

                    i = j;
                }
            }

            if(stable) break;

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(board[i][j] < 0) board[i][j] = 0;
                }
            }

            // fall
            for(int c = 0; c < n; c++) {
                int bottom = m - 1;
                while(bottom >= 0 && board[bottom][c] != 0) {
                    bottom--;
                }

                for(int r = bottom - 1; r >= 0; r--) {
                    if(board[r][c] == 0) continue;

                    board[bottom--][c] = board[r][c];
                    board[r][c] = 0;
                }
            }
        }

        return board;
    }
}
