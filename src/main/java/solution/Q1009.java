package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Complement of Base 10 Integer",
        difficulty = QDifficulty.EASY,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/complement-of-base-10-integer/"
)
public class Q1009 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int bitwiseComplement(int n) {
        if(n == 0) {
            return 1;
        }
        int ans = 0;
        int i = 0;
        while(n != 0) {
            int b = n & 1 ^ 1;

            ans = ans | (b << i);
            n >>= 1;
            i++;
        }

        return ans;
    }
}
