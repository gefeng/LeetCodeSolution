package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number Complement",
        difficulty = QDifficulty.EASY,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/number-complement/"
)
public class Q476 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int findComplement(int num) {
        int res = 0;
        int i = 0;
        while(num != 0) {
            res |= (num & 1 ^ 1) << i;
            num >>= 1;
            i++;
        }

        return res;
    }

    private int javaImp(int num) {
        int bitmask = num;
        bitmask |= bitmask >> 1;
        bitmask |= bitmask >> 2;
        bitmask |= bitmask >> 4;
        bitmask |= bitmask >> 8;
        bitmask |= bitmask >> 16;

        return bitmask ^ num;
    }
}
