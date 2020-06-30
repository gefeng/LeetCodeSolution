package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sqrt(x)",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/sqrtx/"
)
public class Q69 {
    public int mySqrt(int x) {
        if(x < 2) return x;

        int left = 0;
        int right = x / 2;
        int middle = 0;
        while(left <= right) {
            middle = left + (right - left) / 2;
            long sqr = (long)middle * middle;
            if(sqr == x)
                return middle;
            else if(sqr > x)
                right = middle - 1;
            else
                left = middle + 1;
        }

        return right;
    }
}
