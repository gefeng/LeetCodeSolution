package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Stack;

@Problem(
        title = "Valid Parentheses",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING
)
public class Q20 {
    public boolean isValid(String s) {
        HashMap<Character, Character> mapping = new HashMap<>();
        mapping.put(')', '(');
        mapping.put(']', '[');
        mapping.put('}', '{');
        Stack<Character> stack = new Stack();

        for(char c : s.toCharArray()) {
            if(mapping.containsKey(c)) {
                char top = stack.empty() ? '#' : stack.pop();
                if(top != mapping.get(c))
                    return false;
            } else
                stack.push(c);
        }

        return stack.empty();
    }
}
