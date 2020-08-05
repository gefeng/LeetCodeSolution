package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;

@Problem(
        title = "Sudoku Solver",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/sudoku-solver/"
)
public class Q37 {
    int[][] rLookup = new int[9][9];
    int[][] cLookup = new int[9][9];
    int[][] bLookup = new int[9][9];

    public void solveSudoku(char[][] board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.')
                    addToLookUp(i, j, board[i][j] - '0');
            }
        }

        backTrack(board, 0, 0);
    }

    private boolean backTrack(char[][] board, int row, int col) {
        if(row == 9)
            return true;
        if(board[row][col] != '.')
            return backTrack(board, row + (col + 1) / 9, (col + 1) % 9);
        else {
            boolean isSolved = false;
            for (int i = 1; i <= 9; i++) {
                if (isValid(row, col, i)) {
                    board[row][col] = (char)(i + '0');
                    addToLookUp(row, col, i);

                    isSolved = backTrack(board, row + (col + 1) / 9, (col + 1) % 9);

                    if(!isSolved) {
                        board[row][col] = '.';
                        removeFromLookUp(row, col, i);
                    }
                }
            }
            return isSolved;
        }
    }

    private void addToLookUp(int row, int col, int val) {
        rLookup[row][val - 1]++;
        cLookup[col][val - 1]++;
        bLookup[(row / 3) * 3 + col / 3][val - 1]++;
    }

    private void removeFromLookUp(int row, int col, int val) {
        rLookup[row][val - 1]--;
        cLookup[col][val - 1]--;
        bLookup[(row / 3) * 3 + col / 3][val - 1]--;
    }

    private boolean isValid(int row, int col, int val) {
        return rLookup[row][val - 1] + cLookup[col][val - 1] + bLookup[(row / 3) * 3 + col / 3][val - 1] == 0;
    }
}
