package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Binary Number with Alternating Bits",
        difficulty = QDifficulty.EASY,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/binary-number-with-alternating-bits/"
)
public class Q693 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public boolean hasAlternatingBits(int n) {
        int pre = -1;
        while(n > 0) {
            int b = n % 2;
            if(b == pre) return false;
            pre = b;
            n /= 2;
        }
        return true;
    }
}
