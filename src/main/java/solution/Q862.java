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
    /*
    * monotonic queue
    * */
    public int shortestSubarray(int[] nums, int k) {
        return dequeSolution(nums, k);
    }

    private int dequeSolution(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for(int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int minLen = n + 1;
        for(int i = 0; i < n + 1; i++) {
            while(!deque.isEmpty() && prefixSum[deque.peekLast()] >= prefixSum[i]) {
                deque.pollLast();
            }

            while(!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                minLen = Math.min(minLen, i - deque.pollFirst());
            }

            deque.offerLast(i);
        }

        return minLen == n + 1 ? -1 : minLen;
    }

    private int bruteForce(int[] nums, int k) {
        int n = nums.length;
        int minLen = n + 1;
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = i; j < n; j++) {
                sum += nums[j];
                if(sum >= k) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }

        return minLen == n + 1 ? -1 : minLen;
    }
}
