package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Check Completeness of a Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/check-completeness-of-a-binary-tree/"
)
public class Q958 {
    /*
        left justified
        as soon as find a null child, stop enqueue. If enqueue happens, return false
    */
    public boolean isCompleteTree(TreeNode root) {
        if(root == null)
            return true;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        boolean end = false;
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            if(curr.left != null) {
                if(end) {
                    return false;
                }
                queue.offer(curr.left);
            } else {
                end = true;
            }

            if(curr.right != null) {
                if(end) {
                    return false;
                }
                queue.offer(curr.right);
            } else {
                end = true;
            }
        }

        return true;
    }
}
