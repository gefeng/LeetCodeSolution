package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Insert into a Binary Search Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/insert-into-a-binary-search-tree/"
)
public class Q701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null)
            return new TreeNode(val);
        if(val < root.val)
            root.left = insertIntoBST(root.left, val);
        else
            root.right = insertIntoBST(root.right, val);

        return root;
    }

    public TreeNode insertIntoBSTIterative(TreeNode root, int val) {
        if(root == null)
            return new TreeNode(val);

        TreeNode curr = root;
        TreeNode prev = null;
        while(curr != null) {
            prev = curr;
            curr = val < curr.val ? curr.left : curr.right;
        }

        if(val < prev.val)
            prev.left = new TreeNode(val);
        else
            prev.right = new TreeNode(val);

        return root;
    }
}
