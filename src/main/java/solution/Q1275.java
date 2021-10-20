package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Winner on a Tic Tac Toe Game",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/"
)
public class Q1275 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public String tictactoe(int[][] moves) {
        int[] rows = new int[3];
        int[] cols = new int[3];
        int dia1 = 0;
        int dia2 = 0;

        int t = 1;
        for(int[] m : moves) {
            int r = m[0];
            int c = m[1];
            rows[r] += t;
            cols[c] += t;
            if(r == c) {
                dia1 += t;
            }
            if(r + c == 2) {
                dia2 += t;
            }

            if(Math.abs(rows[r]) == 3 || Math.abs(cols[c]) == 3 || Math.abs(dia1) == 3 || Math.abs(dia2) == 3) {
                return t > 0 ? "A" : "B";
            }

            t = -t;
        }

        return moves.length == 9 ? "Draw" : "Pending";
    }
}
