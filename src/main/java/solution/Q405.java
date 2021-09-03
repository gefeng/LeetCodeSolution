package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Convert a Number to Hexadecimal",
        difficulty = QDifficulty.EASY,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/convert-a-number-to-hexadecimal/"
)
public class Q405 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    private static final String HEXDIG = "0123456789abcdef";
    private int b;

    public String toHex(int num) {
        StringBuilder sb = new StringBuilder();

        while(num != 0) {
            int b = num & 15;
            sb.append(HEXDIG.charAt(b));
            num >>>= 4;
        }

        return sb.length() == 0 ? "0" : sb.reverse().toString();
    }
}
