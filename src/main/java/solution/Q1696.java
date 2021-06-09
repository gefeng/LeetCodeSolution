package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Jump Game VI",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/jump-game-vi/"
)
public class Q1696 {
    /*
        state
            dp[i] means max score ends at position i
        transition:
            dp[i] = max(dp[i - j] + nums[i]) for j in [1..k]


         use maximum sliding window technique to do O(1) maximum dp[i - k.. i - 1] lookup

    */
    public int maxResult(int[] nums, int k) {
        return bottomUpDp(nums, k);
    }

    private int bottomUpDp(int[] nums, int k) {
        int n = nums.length;

        int[] dp = new int[n];
        dp[0] = nums[0];

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);
        for(int i = 1; i < n; i++) {

            if(i - deque.peekFirst() > k) {
                deque.pollFirst();
            }

            dp[i] = dp[deque.peekFirst()] + nums[i];

            while(!deque.isEmpty() && dp[i] > dp[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return dp[n - 1];
    }
}
