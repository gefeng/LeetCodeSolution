package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Power of Three",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/power-of-three/"
)
public class Q326 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public boolean isPowerOfThree(int n) {
        return n > 0 && (1162261467 % n == 0);
    }
}
