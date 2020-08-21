package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Power of Four",
        difficulty = QDifficulty.EASY,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/power-of-four/"
)
public class Q342 {
    public boolean isPowerOfFourIterative(int num) {
        if(num == 0)
            return false;
        while(num != 1) {
            if(num % 4 != 0)
                return false;
            num = num / 4;
        }
        return true;
    }

    public boolean isPowerOfFourRecursive(int num) {
        if(num == 1)
            return true;
        if(num == 0 || num % 4 != 0)
            return false;

        return isPowerOfFourRecursive(num / 4);
    }

    public boolean isPowerOfFourBit(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num & 0xaaaaaaaa) == 0;
    }
}
