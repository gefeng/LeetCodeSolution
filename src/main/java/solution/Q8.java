package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "String to Integer (atoi)",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/string-to-integer-atoi/"
)
public class Q8 {
    public int myAtoi(String s) {
        if(s == null || s.length() == 0)
            return 0;

        int idx = 0;
        // remove leading white space
        while(idx < s.length() && s.charAt(idx) == ' ')
            idx++;
        if(idx == s.length())
            return 0;

        // read sign
        int sign = 0;
        char c = s.charAt(idx);
        if(Character.isDigit(c))
            sign = 1;
        else if(c == '-' || c == '+') {
            sign = c == '-' ? -1 : 1;
            idx++;
        }
        if(sign == 0 || idx == s.length())
            return 0;

        // read digits
        List<Character> digits = new ArrayList<>();
        while(idx < s.length() && Character.isDigit(s.charAt(idx)))
            digits.add(s.charAt(idx++));
        if(digits.size() == 0)
            return 0;

        long num = 0;
        for(int i = 0; i < digits.size(); i++) {
            char digit = digits.get(i);
            num = num * 10 + (digit - '0');
            if(num * sign > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if(num * sign < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
        }

        return (int)num * sign;
    }
}
