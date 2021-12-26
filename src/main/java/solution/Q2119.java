package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "A Number After a Double Reversal",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/a-number-after-a-double-reversal/"
)
public class Q2119 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public boolean isSameAfterReversals(int num) {
        return num == 0 || num % 10 != 0;
    }
}
