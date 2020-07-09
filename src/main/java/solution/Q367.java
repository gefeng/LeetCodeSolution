package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Valid Perfect Square",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/valid-perfect-square/"
)
public class Q367 {
    public boolean isPerfectSquare(int num) {
        if(num == 0 || num == 1)
            return true;

        long left = 2;
        long right = num / 2;
        long mid = 0;
        while(left <= right) {
            mid = left + (right - left) / 2;
            long sqr = mid * mid;
            if(sqr == num)
                return true;
            else if(sqr < num)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return false;
    }
}
