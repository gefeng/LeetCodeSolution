package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "XOR Operation in an Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/xor-operation-in-an-array/"
)
public class Q1486 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int xorOperation(int n, int start) {
        int res = 0;
        int cur = 0;
        for(int i = 0; i < n; i++) {
            cur = start + 2 * i;
            res ^= cur;
        }

        return res;
    }
}
