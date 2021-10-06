package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Design a Stack With Increment Operation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/design-a-stack-with-increment-operation/"
)
public class Q1381 {
    /**
     * We do lazy increasing.
     *
     * Time:  O(1)
     * Space: O(N)
     * */
    private int max;
    private Deque<Integer> stack;
    private int[] inc;
    public Q1381(int maxSize) {
        max = maxSize;
        stack = new ArrayDeque<>();
        inc = new int[max];
    }

    public void push(int x) {
        if(stack.size() < max) {
            stack.push(x);
        }
    }

    public int pop() {
        if(stack.isEmpty()) {
            return -1;
        }

        int sz = stack.size();
        int ret = stack.pop() + inc[sz - 1];

        if(sz > 1) {
            inc[sz - 2] += inc[sz - 1];
        }
        inc[sz - 1] = 0;

        return ret;
    }

    public void increment(int k, int val) {
        if(stack.isEmpty()) {
            return;
        }
        inc[Math.min(k, stack.size()) - 1] += val;
    }
}
