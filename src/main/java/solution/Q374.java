package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Guess Number Higher or Lower",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/guess-number-higher-or-lower/"
)
public class Q374 {
    private int guess(int num) {
        return 0;
    }
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        int middle = 0;
        while(left <= right) {
            middle = left + (right - left) / 2;
            int result = guess(middle);
            if(result == 0)
                return middle;
            else if(result == -1)
                right = middle - 1;
            else if(result == 1)
                left = middle + 1;
        }
        return middle;
    }
}
