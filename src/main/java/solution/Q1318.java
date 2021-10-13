package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Flips to Make a OR b Equal to c",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/"
)
public class Q1318 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int minFlips(int a, int b, int c) {
        int ans = 0;

        for(int i = 0; i < 32; i++) {
            int bitA = a & (1 << i);
            int bitB = b & (1 << i);
            int bitC = c & (1 << i);

            if((bitA | bitB) != bitC) {
                if(bitC == 0) {
                    ans += (bitA ^ bitB) == 0 ? 2 : 1;
                } else {
                    ans += 1;
                }
            }
        }

        return ans;
    }
}
