package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Add to Make Parentheses Valid",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/"
)
public class Q921 {
    public int minAddToMakeValid(String S) {
        int countOpen = 0;
        int countClose = 0;
        for(int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if(c == '(') {
                countOpen++;
            } else {
                if(countOpen == 0) {
                    countClose++;
                } else {
                    countOpen--;
                }
            }
        }

        return countOpen + countClose;
    }
}
