package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Problem(
        title = "Expression Add Operators",
        difficulty = QDifficulty.HARD,
        tag = QTag.DIVIDE_AND_CONQUER,
        url = "https://leetcode.com/problems/expression-add-operators/"
)
public class Q282 {
    /**
     * Time:  O(4 ^ N * N)
     * Space: O(4 ^ N * N)
     * */
    private static final char[] OPERATORS = new char[] {
            '+', '-', '*'
    };
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        backTrack(num, target, 0, new StringBuilder(), ans);
        return ans;
    }

    private void backTrack(String num, int target, int idx, StringBuilder candidate, List<String> ans) {
        if(idx == num.length()) {
            String expression = candidate.toString();
            if(evaluate(expression, target)) {
                ans.add(expression);
            }
            return;
        }

        if(idx > 0 && Character.isDigit(candidate.charAt(candidate.length() - 1))) {
            for(char operator : OPERATORS) {
                candidate.append(operator);
                backTrack(num, target, idx, candidate, ans);
                candidate.deleteCharAt(candidate.length() - 1);
            }
        }

        // check if operand is start with '0';
        int currLen = candidate.length();
        if(currLen > 0 && candidate.charAt(currLen - 1) == '0') {
            if(currLen == 1) // init with '0', no more digit
                return;
            if(!Character.isDigit(candidate.charAt(currLen - 2))) // curr operand starts with '0'
                return;
        }

        candidate.append(num.charAt(idx));
        backTrack(num, target, idx + 1, candidate, ans);
        candidate.deleteCharAt(candidate.length() - 1);
    }

    private boolean evaluate(String expression, int target) {
        Deque<Long> stack = new ArrayDeque<>();
        long currNum = 0;
        char currOperator = '+';
        long res = 0;
        for(int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if(Character.isDigit(c)) {
                currNum = currNum * 10 + c - '0';
            }
            if(i == expression.length() - 1 || !Character.isDigit(c)) {
                if(currOperator == '*') {
                    stack.push(stack.pop() * currNum);
                } else {
                    int sign = currOperator == '+' ? 1 : -1;
                    stack.push(currNum * sign);
                }
                currOperator = c;
                currNum = 0;
            }
        }

        while(!stack.isEmpty()) {
            res += stack.pop();
        }

        //System.out.println(res);
        return res == target;
    }
}
