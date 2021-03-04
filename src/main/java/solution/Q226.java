package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Invert Binary Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/invert-binary-tree/"
)
public class Q226 {
    public TreeNode invertTree(TreeNode root) {
        return iterative(root);
    }

    private TreeNode recursive(TreeNode root) {
        if(root == null)
            return null;
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }

    private TreeNode iterative(TreeNode root) {
        if(root == null)
            return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            if(curr.left != null)
                queue.offer(curr.left);
            if(curr.right != null)
                queue.offer(curr.right);
        }

        return root;
    }
}
