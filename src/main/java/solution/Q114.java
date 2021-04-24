package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Flatten Binary Tree to Linked List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/flatten-binary-tree-to-linked-list/"
)
public class Q114 {
    public void flatten(TreeNode root) {
        iterative(root);
    }

    private void iterative(TreeNode root) {
        if(root == null)
            return;

        TreeNode prev = new TreeNode(0);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            prev.right = curr;
            prev.left = null;
            prev = curr;
            if(curr.right != null) {
                stack.push(curr.right);
            }
            if(curr.left != null) {
                stack.push(curr.left);
            }
        }
    }

    TreeNode prev = new TreeNode(0);
    private void preorder(TreeNode root) {
        if(root == null)
            return;

        prev.right = root;
        prev.left = null;
        prev = root;

        TreeNode lChild = root.left;
        TreeNode rChild = root.right;

        preorder(lChild);
        preorder(rChild);
    }

    private TreeNode postorder(TreeNode root) {
        if(root == null)
            return null;

        TreeNode lTail = postorder(root.left);
        TreeNode rTail = postorder(root.right);

        if(lTail == null && rTail == null)
            return root;

        if(lTail != null) {
            lTail.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        return rTail == null ? lTail : rTail;
    }
}
