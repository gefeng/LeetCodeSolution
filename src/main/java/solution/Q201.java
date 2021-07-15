package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Bitwise AND of Numbers Range",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/bitwise-and-of-numbers-range/"
)
public class Q201 {
    /*
    * compare bit by bit until finding a different pair
    * */
    public int rangeBitwiseAnd(int left, int right) {
        int ans = 0;
        for(int i = 30; i >= 0; i--) {
            int bitL = (1 << i) & left;
            int bitR = (1 << i) & right;
            if(bitL != bitR) {
                return ans;
            } else {
                ans = bitR == 0 ? ans : (ans | (1 << i));
            }
        }

        return ans;
    }

    private int turnOffRightMostBit(int left, int right) {
        while(left < right) {
            right &= (right - 1);
        }

        return left & right;
    }
}
