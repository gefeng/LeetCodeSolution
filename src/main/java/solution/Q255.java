package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Verify Preorder Sequence in Binary Search Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/"
)
public class Q255 {
    /*
    * upper = stack.peek()
    * lower = stack.pop()
    * when val > stack.peek() we are turning right, update lower
    * and make sure val in right subtree must larger than lower
    * */
    public boolean verifyPreorder(int[] preorder) {
        Deque<Integer> stack = new ArrayDeque<>();
        int lower = Integer.MIN_VALUE;

        for(int val : preorder) {
            while(!stack.isEmpty() && stack.peek() < val) {
                lower = stack.pop();
            }

            if(val > lower) {
                stack.push(val);
            } else {
                return false;
            }
        }

        return true;
    }
}
