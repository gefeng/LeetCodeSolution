package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Verify Preorder Serialization of a Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/"
)
public class Q331 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean isValidSerialization(String preorder) {
        String[] tree = preorder.split(",");
        Deque<String> stack = new ArrayDeque<>();
        boolean res = false;
        int i = 0;
        int n = tree.length;
        while(i < n) {
            while(i < n && !tree[i].equals("#")) {
                stack.push(tree[i++]);
            }

            if(stack.isEmpty()) {
                res = i == n - 1;
                break;
            }

            stack.pop();
            i++;
        }

        return res;
    }
}
