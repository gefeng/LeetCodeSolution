package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Basic Calculator",
        difficulty = QDifficulty.HARD,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/basic-calculator/"
)
public class Q224 {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        int currNum = 0;
        int currSign = 1;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ' ')
                continue;
            if(Character.isDigit(c))
                currNum = currNum * 10 + c - '0';
            else if(c == '+' || c == '-') {
                res += (currNum * currSign);
                currNum = 0;
                currSign = c == '+' ? 1 : -1;
            } else if(c == '(') {
                stack.push(res);
                stack.push(currSign);
                res = 0;
                currSign = 1;
            } else if(c == ')') {
                res += (currNum * currSign);
                res *= stack.pop(); // saved sign
                res += stack.pop();

                currNum = 0;
                currSign = 1;
            }
        }

        res += (currNum * currSign);
        return res;
    }
}
