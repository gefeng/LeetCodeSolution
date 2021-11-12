package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Parsing A Boolean Expression",
        difficulty = QDifficulty.HARD,
        tag = QTag.RECURSION,
        url = "https://leetcode.com/problems/parsing-a-boolean-expression/"
)
public class Q1106 {
    /**
     * Polish notation is easy to solve by stack. Just push everything onto stack until hit ')'.
     * Then pop until finding a operator. Since it's boolean operations we just need to count
     * how many 'f' and 't' we get. 
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    int pos = 0;
    public boolean parseBoolExpr(String expression) {
        return stackSol(expression);
        //return solve(expression);
    }

    private boolean solve(String exp) {
        int n = exp.length();
        char op = exp.charAt(pos);

        boolean res = op == '&';

        pos += 1;

        boolean operand;
        while(pos < n) {
            char c = exp.charAt(pos);

            if(c == ',') {

            } else if(c == ')') {
                break;
            } else if(c == '(') {

            } else {
                if(Character.isLetter(c)) {
                    operand = c == 't';
                } else {
                    operand = solve(exp);
                }

                if(op == '!') {
                    res = !operand;
                } else if(op == '&') {
                    res = res && operand;
                } else {
                    res = res || operand;
                }
            }

            pos++;
        }

        return res;
    }

    private boolean stackSol(String exp) {
        int n = exp.length();
        Deque<Character> stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            char c = exp.charAt(i);

            if(c == '(' || c == ',') {
                continue;
            }

            if(c != ')') {
                stack.push(c);
            } else {
                boolean res;
                int cntT = 0, cntF = 0;
                while(stack.peek() == 't' || stack.peek() == 'f') {
                    if(stack.pop() == 't') {
                        cntT += 1;
                    } else {
                        cntF += 1;
                    }
                }

                char op = stack.pop();
                if(op == '!') {
                    res = cntF > cntT;
                } else if(op == '&') {
                    res = cntF == 0;
                } else {
                    res = cntT > 0;
                }

                stack.push(res ? 't' : 'f');
            }
        }

        return stack.peek() == 't';
    }
}
