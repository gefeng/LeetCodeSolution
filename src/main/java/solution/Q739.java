package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Daily Temperatures",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/daily-temperatures/"
)
public class Q739 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = n - 1; i >= 0; i--) {
            int t = temperatures[i];
            while(!stack.isEmpty() && temperatures[stack.peek()] <= t) {
                stack.pop();
            }

            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }

        return ans;
    }
}
