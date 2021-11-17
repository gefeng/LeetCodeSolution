package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Divisor Game",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/divisor-game/"
)
public class Q1025 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}
