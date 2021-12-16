package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Shortest Subarray with Sum at Least K",
        difficulty = QDifficulty.HARD,
        tag = QTag.QUEUE,
        url = "https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/"
)
public class Q862 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int ans = n + 1;
        long[] preSum = new long[n + 1];

        for(int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);
        for(int i = 1; i <= n; i++) {
            while(!deque.isEmpty() && preSum[i] - preSum[deque.peekFirst()] >= k) {
                ans = Math.min(ans, i - deque.pollFirst());
            }

            while(!deque.isEmpty() && preSum[i] <= preSum[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return ans == n + 1 ? -1 : ans;
    }
}
