package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Plus One",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/plus-one/"
)
public class Q66 {
    /**注意如果需要建新数组（当遇到99，999 etc），由于数组新建默认值为0所以只需要首位设1即可**/
    public int[] plusOne(int[] digits) {
        int carry = 0;
        for(int i = digits.length - 1; i >= 0; i--) {
            digits[i] += 1;
            carry = digits[i] / 10;
            digits[i] = digits[i] % 10;
            if(carry == 0)
                return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
