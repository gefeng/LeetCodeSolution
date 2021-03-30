package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Basic Calculator II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/basic-calculator-ii/"
)
public class Q227 {
    public int calculate(String s) {
        return stackSolution(s);
    }

    private int stackSolution(String s) {
        if(s.isEmpty())
            return 0;
        Stack<Integer> stack = new Stack<>();
        int number = 0;
        char operator = '+';
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ' ')
                continue;
            else if(Character.isDigit(c))
                number = number * 10 + c - '0';
            else {
                resolve(operator, number, stack);
                operator = c;
                number = 0;
            }
        }
        resolve(operator, number, stack);

        int res = 0;
        while(!stack.isEmpty())
            res += stack.pop();
        return res;
    }

    private void resolve(char operator, int number, Stack<Integer> stack) {
        if(operator == '+')
            stack.push(number);
        else if(operator == '-')
            stack.push(-number);
        else if(operator == '*')
            stack.push(stack.pop() * number);
        else
            stack.push(stack.pop() / number);
    }
}
