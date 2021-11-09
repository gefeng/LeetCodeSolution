package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Parsing A Boolean Expression",
        difficulty = QDifficulty.HARD,
        tag = QTag.RECURSION,
        url = "https://leetcode.com/problems/parsing-a-boolean-expression/"
)
public class Q1106 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    int i = 0;
    public boolean parseBoolExpr(String expression) {
        return solve(expression);
    }

    private boolean solve(String exp) {
        int n = exp.length();
        char op = exp.charAt(i);

        boolean res = op == '&';

        i += 1;

        boolean operand;
        while(i < n) {
            char c = exp.charAt(i);

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

            i++;
        }

        return res;
    }
}
