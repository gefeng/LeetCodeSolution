package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Basic Calculator III",
        difficulty = QDifficulty.HARD,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/basic-calculator-iii/"
)
public class Q772 {
    /**
     * solve reverse polish
     *
     * if num push to stack
     * if op  pop two number and evaluate then push res back
     *
     * with parentheses and op priority
     * 2 stacks
     *
     * stack1: operands
     * stack2: operators and parentheses
     *
     * if "(" push onto stack2
     * if ")" pop and solve until "("
     * if "num" push onto stack1
     * if "op" solve high priority operator before push
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int calculate(String s) {
        int n = s.length();
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Character> stack2 = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(Character.isDigit(c)) {
                int num = 0;
                while(i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i++) - '0';
                }
                stack1.push(num);
                i--;
            } else if(c == '(') {
                stack2.push(c);
            } else if(c == ')') {
                while(stack2.peek() != '(') {
                    cal(stack1, stack2);
                }
                stack2.pop();
            } else {
                while(!stack2.isEmpty() && priority(stack2.peek()) >= priority(c)) {
                    cal(stack1, stack2);
                }
                stack2.push(c);
            }
        }

        while(!stack2.isEmpty()) {
            cal(stack1, stack2);
        }

        return stack1.peek();
    }

    private void cal(Deque<Integer> s1, Deque<Character> s2) {
        char op = s2.pop();
        int y = s1.pop();
        int x = s1.pop();

        if(op == '+') s1.push(x + y);
        else if(op == '-') s1.push(x - y);
        else if(op == '*') s1.push(x * y);
        else s1.push(x / y);
    }

    private int priority(char op) {
        if(op == '+' || op == '-') return 1;
        if(op == '*' || op == '/') return 2;
        return 0;
    }
}
