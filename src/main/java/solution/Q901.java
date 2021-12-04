package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Online Stock Span",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/online-stock-span/"
)
public class Q901 {
    /**
     * Time:  O(N) amortized O(1) for next()
     * Space: O(N)
     * */
    private Deque<int[]> stack;
    public Q901() {
        stack = new ArrayDeque<>();
    }

    public int next(int price) {
        int span = 1;
        while(!stack.isEmpty() && price >= stack.peek()[0]) {
            span += stack.pop()[1];
        }
        stack.push(new int[] {price, span});

        return span;
    }
}
