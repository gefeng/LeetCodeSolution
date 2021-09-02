package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Nim Game",
        difficulty = QDifficulty.EASY,
        tag = QTag.GAME_THEORY,
        url = "https://leetcode.com/problems/nim-game/"
)
public class Q292 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
