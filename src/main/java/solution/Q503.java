package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

@Problem(
        title = "Next Greater Element II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/next-greater-element-ii/"
)
public class Q503 {
    /*
    * Monotonic Stack + Two Pass
    * */
    public int[] nextGreaterElements(int[] nums) {
        int[] nextGreater = new int[nums.length];
        Arrays.fill(nextGreater, -1);

        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];

            while(!stack.isEmpty() && num > nums[stack.peek()]) {
                nextGreater[stack.pop()] = num;
            }
            stack.push(i);
        }

        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];

            while(!stack.isEmpty() && num > nums[stack.peek()]) {
                nextGreater[stack.pop()] = num;
            }
        }

        return nextGreater;
    }
}
