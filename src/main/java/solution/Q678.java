package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Valid Parenthesis String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/valid-parenthesis-string/"
)
public class Q678 {
    public boolean checkValidString(String s) {
        return stackSolution(s);
    }

    private boolean stackSolution(String s) {
        Deque<Integer> pStack = new ArrayDeque<>();
        Deque<Integer> sStack = new ArrayDeque<>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '*') {
                sStack.push(i);
            } else if(c == '(') {
                pStack.push(i);
            } else if(c == ')') {
                if(pStack.isEmpty() && sStack.isEmpty()) {
                    return false;
                }

                if(!pStack.isEmpty()) {
                    pStack.pop();
                } else {
                    sStack.pop();
                }
            }
        }

        while(!pStack.isEmpty() && !sStack.isEmpty() && pStack.peek() < sStack.peek()) {
            pStack.pop();
            sStack.pop();
        }

        return pStack.isEmpty();
    }
}
