package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "132 Pattern",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/132-pattern/"
)
public class Q456 {
    /*
    * similar to next greater element
    * 1st pass - try to find next smaller ns[i] for each elements (monotonic stack)
    * 2nd pass - keep track of the curr minimum as '1', current element as '3', ns[i] as '2'
    *            check curr > currMin && ns[i] > currMin
    *
    * good question but hard, hidden monotonic stack, hard to get the idea behind
    * */
    public boolean find132pattern(int[] nums) {
        if(nums.length < 3)
            return false;

        int[] nextSmaller = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = nums.length - 1; i >= 0; i--) {
            nextSmaller[i] = Integer.MIN_VALUE;
            while(!stack.isEmpty() && nums[i] > stack.peek()) {
                nextSmaller[i] = stack.pop();
            }

            stack.push(nums[i]);
        }

        int currMin = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            currMin = Math.min(currMin, nums[i]);
            if(nums[i] > currMin && nextSmaller[i] > currMin)
                return true;
        }

        return false;
    }
}
