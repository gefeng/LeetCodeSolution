package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

@Problem(
        title = "Minimum Remove to Make Valid Parentheses",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/"
)
public class Q1249 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> toRemove = new HashSet<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(')
                stack.push(i);
            else if(c == ')') {
                if(stack.isEmpty())
                    toRemove.add(i);
                else
                    stack.pop();
            }
        }

        while(!stack.isEmpty())
            toRemove.add(stack.pop());

        if(toRemove.isEmpty())
            return s;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(toRemove.contains(i))
                continue;
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}
