package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Palindrome Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/palindrome-number/"
)
public class Q9 {
    public boolean isPalindrome(int x) {
        if(x < 0 || (x != 0 && x % 10 == 0))
            return false;

        int rev = 0; // rev = rev * 10 + x % 10;
        while(x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        return x == rev || x == rev / 10;
    }
}
