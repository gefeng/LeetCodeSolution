package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Shortest Unsorted Continuous Subarray",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/shortest-unsorted-continuous-subarray/"
)
public class Q581 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int res = 0;
        int l = n, r = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                l = Math.min(l, stack.pop());
            }

            stack.push(i);
        }

        stack = new ArrayDeque<>();
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                r = Math.max(r, stack.pop());
            }

            stack.push(i);
        }

        return l == n ? 0 : r - l + 1;
    }
}
