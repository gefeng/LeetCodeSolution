package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Find the Most Competitive Subsequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/find-the-most-competitive-subsequence/"
)
public class Q1673 {
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[k];

        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            while(!deque.isEmpty() && deque.peekLast() > nums[i] && n - i - 1 + deque.size() >= k) {
                deque.pollLast();
            }

            deque.offerLast(nums[i]);
        }

        for(int i = 0; i < k; i++) {
            ans[i] = deque.pollFirst();
        }

        return ans;
    }
}
