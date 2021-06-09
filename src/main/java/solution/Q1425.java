package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Constrained Subsequence Sum",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/constrained-subsequence-sum/"
)
public class Q1425 {
    /*
        window size = k

        state:
            dp[i] means max sum of subsequence ends at nums[i]
        transition:
            dp[i] = max(nums[i], dp[i - j] + nums[i]) for each j in [1, k + 1]

        Use a monotonic deque to to constant time maximum in sliding window lookup
    */
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);
        dp[0] = nums[0];
        int maxSum = dp[0];
        for(int i = 1; i < n; i++) {
            if(i - deque.peekFirst() > k) {
                deque.pollFirst();
            }

            dp[i] = Math.max(nums[i], dp[deque.peekFirst()] + nums[i]);

            while(!deque.isEmpty() && dp[i] > dp[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }
}
