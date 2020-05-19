package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Add Strings",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING
)
public class Q415 {
    public String addStrings(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int i = len1 - 1;
        int j = len2 - 1;
        int carry = 0;
        StringBuilder sum = new StringBuilder();

        while(i >= 0 || j >= 0) {
            int a = (i >= 0 ? num1.charAt(i) : '0') - '0';
            int b = (j >= 0 ? num2.charAt(j) : '0') - '0';
            int s = a + b + carry;
            sum.insert(0, s % 10);
            carry = s / 10;
            i--;
            j--;
        }

        if(carry != 0) sum.insert(0, '1');

        return sum.toString();
    }
}
