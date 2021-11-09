package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Longest Well-Performing Interval",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/longest-well-performing-interval/"
)
public class Q1124 {
    /**
     * This should be hard XD.
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int ans = 0;

        int[] preSum = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i <= n; i++) {
            if(stack.isEmpty() || preSum[stack.peek()] > preSum[i]) {
                stack.push(i);
            }
        }

        for(int i = n; i >= 0; i--) {
            while(!stack.isEmpty() && (i < stack.peek() || preSum[i] - preSum[stack.peek()] >= 1)) {
                ans = Math.max(ans, i - stack.pop());
            }
        }

        return ans;
    }
}
