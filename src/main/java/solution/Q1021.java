package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove Outermost Parentheses",
        difficulty = QDifficulty.EASY,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/remove-outermost-parentheses/"
)
public class Q1021 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        int n = s.length();

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(c == '(') {
                cnt++;
                if(cnt > 1) {
                    sb.append(c);
                }
            } else {
                cnt--;
                if(cnt > 0) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}
