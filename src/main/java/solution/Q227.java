package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

@Problem(
        title = "Basic Calculator II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/basic-calculator-ii/"
)
public class Q227 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int calculate(String s) {
        int n = s.length();
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Character> s2 = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(c == ' ') continue;

            if(Character.isDigit(c)) {
                int operand = 0;
                while(i < n && Character.isDigit(s.charAt(i))) {
                    operand = operand * 10 + s.charAt(i++) - '0';
                }
                s1.push(operand);
                i--;
            } else {
                while(!s2.isEmpty() && priority(s2.peek()) >= priority(c)) {
                    cal(s1, s2);
                }
                s2.push(c);
            }
        }

        while(!s2.isEmpty()) {
            cal(s1, s2);
        }

        return s1.peek();
    }

    private int priority(char c) {
        if(c == '+' || c == '-') return 1;
        return 2;
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
}
