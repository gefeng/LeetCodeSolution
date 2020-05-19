package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Valid Palindrome II",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/valid-palindrome-ii/"
)
public class Q680 {
    public boolean validPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        int tolerance = 0;

        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) {
                return isPalindromeRange(s, start + 1, end) || isPalindromeRange(s, start, end - 1);
            }
            start++;
            end--;
        }

        return true;
    }
    private boolean isPalindromeRange(String s, int start, int end) {
        while(start < end) {
            if(s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }
}
