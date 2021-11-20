package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Check If Word Is Valid After Substitutions",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/"
)
public class Q1003 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int n = s.length();

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(c == 'a') {
                stack.push(c);
            } else if(c == 'b') {
                if(stack.isEmpty() || stack.peek() != 'a') {
                    return false;
                }
                stack.push(c);
            } else {
                if(stack.isEmpty() || stack.peek() != 'b') {
                    return false;
                }
                stack.pop();
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
