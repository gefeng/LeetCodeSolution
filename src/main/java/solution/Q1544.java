package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Make The String Great",
        difficulty = QDifficulty.EASY,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/make-the-string-great/"
)
public class Q1544 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String makeGood(String s) {
        int n = s.length();
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            if(!stack.isEmpty()) {
                char c2 = stack.peek();
                if(c1 != c2 && (Character.toUpperCase(c1) == c2 || Character.toLowerCase(c1) == c2)) {
                    stack.pop();
                } else {
                    stack.push(c1);
                }
            } else {
                stack.push(c1);
            }
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
