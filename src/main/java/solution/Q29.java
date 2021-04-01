package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Divide Two Integers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/divide-two-integers/"
)
public class Q29 {
    private static final int HALF_MIN_INT = Integer.MIN_VALUE / 2;
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        int negativeCount = 0;
        int x = dividend > 0 ? -dividend : dividend;
        int y = divisor > 0 ? -divisor : divisor;
        negativeCount = dividend < 0 ? negativeCount + 1 : negativeCount;
        negativeCount = divisor < 0 ? negativeCount + 1 : negativeCount;

        int ans = 0;
        while(y >= x) {
            int count = 1;
            int copyY = y;
            while(copyY >= HALF_MIN_INT && copyY + copyY > x) {
                copyY += copyY;
                count += count;
            }
            ans += count;
            x = x - copyY;
        }

        if(negativeCount == 1)
            return -ans;
        return ans;
    }
}
