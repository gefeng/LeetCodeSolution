package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Implement Queue using Stacks",
        difficulty = QDifficulty.EASY,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/implement-queue-using-stacks/"
)
public class Q232 {
    Stack<Integer> stack;
    /** Initialize your data structure here. */
    public Q232() {
        stack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        Stack<Integer> tempS = new Stack<>();
        int size = stack.size();
        for(int i = 0; i < size; i++)
            tempS.push(stack.pop());

        stack.push(x);

        for(int i = 0; i < size; i++)
            stack.push(tempS.pop());
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack.pop();
    }

    /** Get the front element. */
    public int peek() {
        return stack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}
