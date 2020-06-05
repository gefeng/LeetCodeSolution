package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Inorder Successor in BST",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/inorder-successor-in-bst/"
)
public class Q285 {
    TreeNode ans = null;
    private void dfs(TreeNode node, TreeNode p) {
        if(node == null)
            return;
        dfs(node.left, p);
        if(ans != null)
            return;
        if(node.val > p.val) {
            ans = node;
            return;
        }
        dfs(node.right, p);
    }
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        dfs(root, p);
        return ans;
    }

    public TreeNode inorderSuccessorIterative(TreeNode root, TreeNode p) {
        TreeNode curr = p.right;
        while(curr != null) {
            if(curr.left == null)
                return curr;
            curr = curr.left;
        }

        Stack<TreeNode> stack = new Stack<>();
        curr = root;
        while(!stack.isEmpty() || curr != null) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            if(curr.val > p.val)
                return curr;

            curr = curr.right;
        }
        return null;
    }

    /** very simple solution whose idea is similar to binary search in sorted list */
    public TreeNode inorderSuccessorSimple(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while(root != null) {
            if(root.val > p.val) {
                successor = root; // possible successor
                root = root.left;
            } else
                root = root.right;
        }
        return successor;
    }
}
