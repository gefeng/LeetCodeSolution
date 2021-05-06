package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Longest Valid Parentheses",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/longest-valid-parentheses/"
)
public class Q32 {
    public int longestValidParentheses(String s) {
        return dpSolution(s);
    }

    private int stackSolution(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // push prev invalid position
        int maxLen = 0;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }

    private int dpSolution(String s) {
        int[] dp = new int[s.length()];
        int maxLen = 0;

        for(int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ')') {
                if(s.charAt(i - 1) == ')') {
                    if(i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + 2;
                        if(i - dp[i - 1] - 2 >= 0) {
                            dp[i] += dp[i - dp[i - 1] - 2];
                        }
                    }
                } else {
                    dp[i] = i > 1 ? dp[i - 2] + 2 : 2;
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }

        return maxLen;
    }
}
