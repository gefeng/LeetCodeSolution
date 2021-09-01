package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Power of Two",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/power-of-two/"
)
public class Q231 {
    /**
     * Turn off rightmost bit.
     *
     * Time:  O(1)
     * Space: O(1)
     * */
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) {
            return false;
        }

        return (n & (n - 1)) == 0;
    }
}
