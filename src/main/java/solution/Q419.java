package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Battleships in a Board",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.NONE,
        url = "https://leetcode.com/problems/battleships-in-a-board/"
)
public class Q419 {
    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int count = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // check head of the ship only
                if(board[i][j] == 'X' &&
                        (i == 0 || board[i - 1][j] == '.') &&
                        (j == 0 || board[i][j - 1] == '.')) {
                    count++;
                }
            }
        }

        return count;
    }
}
