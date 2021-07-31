package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Even Odd Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/even-odd-tree/"
)
public class Q1609 {
    /**
     * Time:  O(N)
     * Space: O(N)
     */
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        int level = 0;

        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();

            int prev = 0;
            for(int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                if(level % 2 == 0) {
                    if((prev != 0 && curr.val <= prev) || curr.val % 2 == 0) {
                        return false;
                    }
                } else {
                    if((prev != 0 && curr.val >= prev) || curr.val % 2 == 1) {
                        return false;
                    }
                }

                prev = curr.val;

                if(curr.left != null) {
                    queue.offer(curr.left);
                }
                if(curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            level++;
        }

        return true;
    }
}
