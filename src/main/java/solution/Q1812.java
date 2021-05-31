package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Determine Color of a Chessboard Square",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/biweekly-contest-49/problems/determine-color-of-a-chessboard-square/"
)
public class Q1812 {
    public boolean squareIsWhite(String coordinates) {
        int row = coordinates.charAt(1);
        int col = coordinates.charAt(0) - 'a' + 1;

        boolean rOdd = row % 2 != 0;
        boolean cOdd = col % 2 != 0;

        if(rOdd == cOdd) {
            return false;
        }
        return true;
    }
}
