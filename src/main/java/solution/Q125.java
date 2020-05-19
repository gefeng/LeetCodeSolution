package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Valid Palindrome",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/valid-palindrome/"
)
public class Q125 {
    public boolean isPalindrome(String s) {
        int head = 0;
        int tail = s.length() - 1;

        while(head < s.length() - 1 && tail >= 0 && head <= tail) {
            char cHead = Character.toLowerCase(s.charAt(head));
            char cTail = Character.toLowerCase(s.charAt(tail));

            if(Character.isLetterOrDigit(cHead) && Character.isLetterOrDigit(cTail)) {
                if(cHead != cTail)
                    return false;
                else {
                    head++;
                    tail--;
                }
            } else if(!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if(!Character.isLetterOrDigit(cTail)) {
                tail--;
            }
        }
        return true;
    }
}
