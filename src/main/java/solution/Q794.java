package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Valid Tic-Tac-Toe State",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/valid-tic-tac-toe-state/"
)
public class Q794 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public boolean validTicTacToe(String[] board) {
        int[] row = new int[3];
        int[] col = new int[3];
        int diag1 = 0;
        int diag2 = 0;
        int cnt1 = 0;
        int cnt2 = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i].charAt(j) == ' ') continue;

                if(board[i].charAt(j) == 'X') {
                    cnt1++;
                    row[i]++;
                    col[j]++;
                    if(i == j) diag1++;
                    if(i + j == 2) diag2++;
                } else {
                    cnt2++;
                    row[i]--;
                    col[j]--;
                    if(i == j) diag1--;
                    if(i + j == 2) diag2--;
                }
            }
        }

        if(cnt2 > cnt1 || cnt1 - cnt2 > 1) return false;

        int winA = 0;
        int winB = 0;
        for(int i = 0; i < 3; i++) {
            if(row[i] == 3) winA++;
            if(row[i] == -3) winB++;
            if(col[i] == 3) winA++;
            if(col[i] == -3) winB++;
        }

        if(diag1 == 3) winA++;
        if(diag2 == 3) winA++;
        if(diag1 == -3) winB++;
        if(diag2 == -3) winB++;

        if(winA > 0 && winB > 0 || winB > 1) return false;

        if(winA > 0 && cnt1 == cnt2) return false;
        if(winB > 0 && cnt1 > cnt2) return false;

        return true;
    }
}
