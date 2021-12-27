package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

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
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else if(s.charAt(i) == ')') {
                if(!stack.isEmpty() && s.charAt(stack.peek()) == '(') stack.pop();
                else stack.push(i);
            }
        }

        Set<Integer> remove = new HashSet<>();
        while(!stack.isEmpty()) {
            remove.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            if(!remove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}
