package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Design Tic-Tac-Toe",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-tic-tac-toe/"
)
public class Q348 {
    private int n;
    private int[] rowStatus;
    private int[] colStatus;
    private int diagonalStatus;
    private int offDiagonalStatus;
    public Q348(int n) {
        this.n = n;
        rowStatus = new int[n];
        colStatus = new int[n];
        diagonalStatus = 0;
        offDiagonalStatus = 0;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int score = player == 1 ? 1 : -1;
        if(Math.abs(rowStatus[row] += score) == n) return player;
        if(Math.abs(colStatus[col] += score) == n) return player;
        if(row == col && Math.abs(diagonalStatus += score) == n) return player;
        if(row == n - 1 - col && Math.abs(offDiagonalStatus += score) == n) return player;
        return 0;
    }
}
