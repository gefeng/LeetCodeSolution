package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Remove K Digits",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/remove-k-digits/"
)
public class Q402 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String removeKdigits(String num, int k) {
        int n = num.length();

        if(k == n) {
            return "0";
        }

        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            char c = num.charAt(i);
            while(!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }

            stack.push(c);
        }

        while(!stack.isEmpty() && k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        while(sb.length() > 1 && sb.charAt(sb.length() - 1) == '0') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.reverse().toString();
    }
}
