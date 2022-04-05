package solution.biweekly75;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Bit Flips to Convert Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/contest/biweekly-contest-75/problems/minimum-bit-flips-to-convert-number/"
)
public class Q2220 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public int minBitFlips(int start, int goal) {
        return Integer.bitCount(start ^ goal);
    }
}
