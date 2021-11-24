package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Maximum Width Ramp",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/maximum-width-ramp/"
)
public class Q962 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            if(stack.isEmpty() || nums[i] < nums[stack.peek()]) {
                stack.push(i);
            }
        }

        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[i] >= nums[stack.peek()]) {
                ans = Math.max(ans, i - stack.pop());
            }
        }

        return ans;
    }
}
