package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Baseball Game",
        difficulty = QDifficulty.EASY,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/baseball-game/"
)
public class Q682 {
    public int calPoints(String[] ops) {
        Deque<Integer> stack = new ArrayDeque<>();
        int sum = 0;
        int n = ops.length;

        for(int i = 0; i < n; i++) {
            String s = ops[i];
            if(s.equals("C")) {
                stack.pop();
            } else if(s.equals("D")) {
                stack.push(stack.peek() * 2);
            } else if(s.equals("+")) {
                int prev = stack.pop();
                int curr = stack.peek() + prev;
                stack.push(prev);
                stack.push(curr);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }

        while(!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}
