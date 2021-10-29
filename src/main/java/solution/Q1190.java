package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Reverse Substrings Between Each Pair of Parentheses",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/"
)
public class Q1190 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public String reverseParentheses(String s) {
        int n = s.length();
        Deque<StringBuilder> stack = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(c == '(') {
                stack.push(sb);
                sb = new StringBuilder();
            } else if(c == ')') {
                sb = stack.pop().append(sb.reverse());
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
