package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number Of Rectangles That Can Form The Largest Square",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/number-of-rectangles-that-can-form-the-largest-square/"
)
public class Q1725 {
    public int countGoodRectangles(int[][] rectangles) {
        int cnt = 0;
        int maxLen = 0;
        for(int[] rect : rectangles) {
            maxLen = Math.max(maxLen, Math.min(rect[0], rect[1]));
        }

        for(int[] rect : rectangles) {
            cnt = Math.min(rect[0], rect[1]) == maxLen ? cnt + 1 : cnt;
        }

        return cnt;
    }
}
