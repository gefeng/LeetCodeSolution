package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Base 7",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/base-7/"
)
public class Q504 {
    /**
     * Time:  O(logN)
     * Space: O(logN)
     * */
    public String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        int x = Math.abs(num);
        while(x != 0) {
            sb.append(x % 7);
            x /= 7;
        }

        if(num < 0) {
            sb.append("-");
        }

        return sb.length() == 0 ? "0" : sb.reverse().toString();
    }
}
