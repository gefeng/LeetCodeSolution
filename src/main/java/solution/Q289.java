package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Game of Life",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/game-of-life/"
)
public class Q289 {
    /*
    * 这题in place的技巧是用另一些状态表示一些状态的转换
    *   0 dead
        1 live
        2 dead live->dead
        3 live dead->live
        4 dead-dead
        5 live-live
    * 这题还有一个follow up是如果board很大怎么办？
    * 可以只保存live cell的位置
    * */
    public void gameOfLife(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                int count = getLiveNeighbors(board, i, j);
                if(board[i][j] == 1) {
                    if(count < 2 || count > 3)
                        board[i][j] = 2;
                    else
                        board[i][j] = 5;
                } else {
                    if(count == 3)
                        board[i][j] = 3;
                }
            }
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 2)
                    board[i][j] = 0;
                else if(board[i][j] == 5 || board[i][j] == 3)
                    board[i][j] = 1;
            }
        }
    }

    private int getLiveNeighbors(int[][] board, int i, int j) {
        int count = 0;
        for(int r = i - 1; r <= i + 1; r++) {
            for(int c = j - 1; c <= j + 1; c++) {
                if(r >= 0 && r < board.length && c >= 0 && c < board[0].length) {
                    if(r == i && c == j)
                        continue;
                    if(board[r][c] == 1 || board[r][c] == 2 || board[r][c] == 5)
                        count++;
                }
            }
        }
        return count;
    }
}
