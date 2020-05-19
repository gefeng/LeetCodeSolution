package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reverse String in place",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/reverse-string/"
)
public class Q344 {
    public void reverseString(char[] s) {
        int len = s.length;
        int head = 0;
        int tail = len - 1;
        while(head < tail) {
            char temp = s[head];
            s[head] = s[tail];
            s[tail] = temp;
            head++;
            tail--;
        }
    }
}
