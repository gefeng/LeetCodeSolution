package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Robot Return to Origin",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/robot-return-to-origin/"
)
public class Q657 {
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;

        for(int i = 0; i < moves.length(); i++) {
            if(moves.charAt(i) == 'R') x++;
            else if(moves.charAt(i) == 'L') x--;
            else if(moves.charAt(i) == 'U') y++;
            else if(moves.charAt(i) == 'D') y--;
        }

        return x == 0 && y == 0;
    }
}
