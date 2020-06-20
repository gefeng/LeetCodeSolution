package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Stack;

@Problem(
        title = "Min Stack",
        difficulty = QDifficulty.EASY,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/min-stack/"
)
public class Q155 {
    Stack<Integer> stack;
    int min;

    /** initialize your data structure here. */
    public Q155() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if(x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if(stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
