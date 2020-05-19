package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Add Binary",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/add-binary/"
)
public class Q67 {
    public String addBinary(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        int i = 0;
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        while(i < lenA || i < lenB || carry != 0) {
            int bitA = (i < lenA) ? a.charAt(lenA - i - 1) - '0' : 0;
            int bitB = (i < lenB) ? b.charAt(lenB - i - 1) - '0' : 0;
            sb.insert(0, bitA ^ bitB ^ carry);
            carry = (bitA + bitB + carry) / 2;
            i++;
        }

        return sb.toString();
    }
}
