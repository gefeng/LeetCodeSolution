package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Ternary Expression Parser",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/ternary-expression-parser/"
)
public class Q439 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String parseTernary(String expression) {
        int n = expression.length();
        Deque<Character> stack = new ArrayDeque<>();

        for(int i = n - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if((c == 'T' || c == 'F') && !stack.isEmpty() && stack.peek() == '?') {
                stack.pop();
                char x = stack.pop();
                stack.pop();
                char y = stack.pop();
                stack.push(c == 'T' ? x : y);
            } else {
                stack.push(c);
            }
        }

        return "" + stack.pop();
    }
}
