package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Wiggle Subsequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/wiggle-subsequence/"
)
public class Q376 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int wiggleMaxLength(int[] nums) {
        return Math.max(test(nums, 1), test(nums, -1));
    }

    private int test(int[] nums, int sign) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[0]);

        for(int i = 1; i < n; i++) {
            if((nums[i] - stack.peek()) * sign <= 0) {
                stack.pop();
            } else {
                sign *= -1;
            }
            stack.push(nums[i]);
        }

        return stack.size();
    }
}
