package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Available Captures for Rook",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/available-captures-for-rook/"
)
public class Q999 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int numRookCaptures(char[][] board) {
        int ans = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(board[i][j] == 'R') {
                    for(int[] dir : DIRECTIONS) {
                        boolean isAttack = false;
                        int r = i;
                        int c = j;
                        while(true) {
                            int nr = r + dir[0];
                            int nc = c + dir[1];

                            if(nr < 0 || nc < 0 || nr >= 8 || nc >= 8) {
                                break;
                            }

                            if(board[nr][nc] == 'p') {
                                isAttack = true;
                                break;
                            }

                            if(board[nr][nc] == 'B') {
                                break;
                            }

                            r = nr;
                            c = nc;
                        }

                        if(isAttack) {
                            ans += 1;
                        }
                    }

                }
            }
        }

        return ans;
    }
}
