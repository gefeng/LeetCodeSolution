package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Score of Parentheses",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/score-of-parentheses/"
)
public class Q856 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int scoreOfParentheses(String s) {
        Deque<int[]> stack = new ArrayDeque<>();
        int n = s.length();
        int ans = 0;

        for(int i = 0, d = 0; i < n; i++) {
            if(s.charAt(i) == '(') {
                d++;
                stack.push(new int[] {d, 0});
            } else {
                if(stack.peek()[0] == d) {
                    stack.peek()[1] = 1;
                } else {
                    int[] top = stack.pop();
                    while(!stack.isEmpty() && top[0] == stack.peek()[0]) {
                        top[1] += stack.pop()[1];
                    }

                    stack.peek()[1] = 2 * top[1];
                }
                d--;
            }
        }

        while(!stack.isEmpty()) {
            ans += stack.pop()[1];
        }

        return ans;
    }
}
