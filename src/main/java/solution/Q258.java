package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Add Digits",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/add-digits/"
)
public class Q258 {
    public int addDigits(int num) {
        if(num == 0) {
            return 0;
        }

        return num % 9 == 0 ? 9 : num % 9;
    }
}
