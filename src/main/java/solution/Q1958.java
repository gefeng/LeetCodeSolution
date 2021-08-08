package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if Move is Legal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/check-if-move-is-legal/"
)
public class Q1958 {
    /**
     * Time:  O(M + N + min(M, N))
     * Space: O(1)
     * */
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        int m = board.length;
        int n = board[0].length;

        // horizontal
        boolean isH1 = false;
        for(int i = cMove + 1; i < n; i++) {
            if(board[rMove][i] == '.') {
                break;
            }
            if(board[rMove][i] == color) {
                if(i - cMove + 1 > 2) {
                    isH1 = true;
                }
                break;
            }
        }

        boolean isH2 = false;
        for(int i = cMove - 1; i >= 0; i--) {
            if(board[rMove][i] == '.') {
                break;
            }
            if(board[rMove][i] == color) {
                if(cMove - i + 1 > 2) {
                    isH2 = true;
                }
                break;
            }
        }

        // vertical
        boolean isV1 = false;
        for(int i = rMove + 1; i < n; i++) {
            if(board[i][cMove] == '.') {
                break;
            }
            if(board[i][cMove] == color) {
                if(i - rMove + 1 > 2) {
                    isV1 = true;
                }
                break;
            }
        }

        boolean isV2 = false;
        for(int i = rMove - 1; i >= 0; i--) {
            if(board[i][cMove] == '.') {
                break;
            }
            if(board[i][cMove] == color) {
                if(rMove - i + 1 > 2) {
                    isV2 = true;
                }
                break;
            }
        }



        // diagnol
        boolean isOk1 = false;
        for(int i = rMove + 1, j = cMove + 1; i < m && j < n; i++, j++) {
            if(board[i][j] == '.') {
                break;
            }
            if(board[i][j] == color) {
                if(i - rMove + 1 > 2) {
                    isOk1 = true;
                }
                break;
            }
        }

        boolean isOk2 = false;
        for(int i = rMove - 1, j = cMove - 1; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == '.') {
                break;
            }
            if(board[i][j] == color) {
                if(rMove - i + 1 > 2) {
                    isOk2 = true;
                }
                break;
            }
        }

        boolean isOk3 = false;
        for(int i = rMove + 1, j = cMove - 1; i < m && j >= 0; i++, j--) {
            if(board[i][j] == '.') {
                break;
            }
            if(board[i][j] == color) {
                if(i - rMove + 1 > 2) {
                    isOk3 = true;
                }
                break;
            }
        }

        boolean isOk4 = false;
        for(int i = rMove - 1, j = cMove + 1; i >= 0 && j < n; i--, j++) {
            if(board[i][j] == '.') {
                break;
            }
            if(board[i][j] == color) {
                if(rMove - i + 1 > 2) {
                    isOk4 = true;
                }
                break;
            }
        }

        return isH1 || isH2 || isV1 || isV2 || isOk1 || isOk2 || isOk3 || isOk4;
    }
}
