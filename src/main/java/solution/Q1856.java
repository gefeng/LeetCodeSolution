package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Maximum Subarray Min-Product",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-subarray-min-product/"
)
public class Q1856 {
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        int[] prevSmaller = new int[n];
        int[] nextSmaller = new int[n];
        long[] prefixSum = new long[n + 1];

        for(int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }

            prevSmaller[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack = new ArrayDeque<>();
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            nextSmaller[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        long max = 0;
        for(int i = 0; i < n; i++) {
            int lo = prevSmaller[i] + 1;
            int hi = nextSmaller[i] - 1;
            //System.out.println(lo + " " + hi);
            max = Math.max(max, nums[i] * (prefixSum[hi + 1] - prefixSum[lo]));
        }

        return (int)(max % 1000000007);
    }
}
