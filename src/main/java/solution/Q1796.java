package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Second Largest Digit in a String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/second-largest-digit-in-a-string/"
)
public class Q1796 {
    public int secondHighest(String s) {
        int[] freq = new int[10];
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                freq[c - '0']++;
            }
        }

        for(int i = 9, j = 0; i >= 0; i--) {
            if(freq[i] != 0) {
                j++;
            }
            if(j == 2) {
                return i;
            }
        }
        return -1;
    }
}
