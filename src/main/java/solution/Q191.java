package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of 1 Bits",
        difficulty = QDifficulty.EASY,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/number-of-1-bits/"
)
public class Q191 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int hammingWeight(int n) {
        int res = 0;
        for(int i = 0; i < 32; i++) {
            res = ((1 << i) & n) == 0 ? res : res + 1;
        }

        return res;
    }
}
