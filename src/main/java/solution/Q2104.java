package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Sum of Subarray Ranges",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/sum-of-subarray-ranges/"
)
public class Q2104 {
    /**
     * O(N ^ 2) solution is trivial. Let's solve it in O(N) time.
     * If I know,
     * 1. the sum of the max elements of each subarray x.
     * 2. the sum of the min elements of each subarray y.
     * => ans = x - y
     *
     * we can use monotonic stack to calculate x and y
     *
     * min sum
     * 3 xxx 5 xxx  add 2
     * i     j          k
     * 0     4          8
     * nums[j] * (j - i) + (k - j - 1)
     * calculate sum while popping from stack.
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public long subArrayRanges(int[] nums) {
        long sum = 0;
        int n = nums.length;
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();

        long mins = 0;
        for(int i = 0; i <= n; i++) {
            while(!s1.isEmpty() && (i == n || nums[s1.peek()] >= nums[i])) {
                int j = s1.pop();
                int k = s1.isEmpty() ? -1 : s1.peek();
                mins += (long)nums[j] * (j - k) * (i - j);
            }

            s1.push(i);
        }

        long maxs = 0;
        for(int i = 0; i <= n; i++) {
            while(!s2.isEmpty() && (i == n || nums[s2.peek()] <= nums[i])) {
                int j = s2.pop();
                int k = s2.isEmpty() ? -1 : s2.peek();
                maxs += (long)nums[j] * (j - k) * (i - j);
            }

            s2.push(i);
        }

        return maxs - mins;
    }
}
