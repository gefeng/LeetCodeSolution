package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reverse Bits",
        difficulty = QDifficulty.EASY,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/reverse-bits/"
)
public class Q190 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int reverseBits(int n) {
        int res = 0;
        for(int i = 0; i < 32; i++) {
            int bit = (n & (1 << i)) == 0 ? 0 : 1;
            res = (res << 1) + bit;
        }

        return res;
    }
}
