package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Odd Number in String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/largest-odd-number-in-string/"
)
public class Q1903 {
    public String largestOddNumber(String num) {
        int n = num.length();
        for(int i = n - 1; i >= 0; i--) {
            int digit = num.charAt(i) - '0';
            if(digit % 2 != 0) {
                return num.substring(0, i + 1);
            }
        }

        return "";
    }
}
