package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Stack;

@Problem(
        title = "Evaluate Reverse Polish Notation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/evaluate-reverse-polish-notation/"
)
public class Q150 {
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        HashSet<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");

        for(String token : tokens) {
            if(operators.contains(token)) {
                int operand1 = Integer.parseInt(stack.pop());
                int operand2 = Integer.parseInt(stack.pop());
                int result;
                if(token.equals("+"))
                    result = operand1 + operand2;
                else if(token.equals("-"))
                    result = operand2 - operand1;
                else if(token.equals("*"))
                    result = operand1 * operand2;
                else
                    result = operand2 / operand1;

                stack.push(Integer.toString(result));
            } else
                stack.push(token);
        }

        return Integer.parseInt(stack.pop());
    }
}
