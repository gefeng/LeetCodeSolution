package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;

@Problem(
        title = "Valid Sudoku",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/valid-sudoku/"
)
public class Q36 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public boolean isValidSudoku(char [][] board) {
        int n = board.length;
        boolean[][] rows = new boolean[n][n + 1];
        boolean[][] cols = new boolean[n][n + 1];
        boolean[][] blks = new boolean[n][n + 1];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                char c = board[i][j];
                if(c == '.') {
                    continue;
                }
                if(rows[i][c - '0'] || cols[j][c - '0'] || blks[i / 3 * 3 + j / 3][c - '0']) {
                    return false;
                }
                rows[i][c - '0'] = true;
                cols[j][c - '0'] = true;
                blks[i / 3 * 3 + j / 3][c - '0'] = true;
            }
        }

        return true;
    }
}
