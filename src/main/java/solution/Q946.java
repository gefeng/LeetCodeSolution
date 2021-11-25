package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Validate Stack Sequences",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/validate-stack-sequences/"
)
public class Q946 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        Deque<Integer> stack = new ArrayDeque<>();

        int i = 0;
        int j = 0;
        while(i < n) {
            stack.push(pushed[i++]);

            while(!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return j == n;
    }
}
