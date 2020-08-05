package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Distance Between BST Nodes",
        difficulty = QDifficulty.EASY,
        tag = QTag.RECURSION,
        url = "https://leetcode.com/problems/minimum-distance-between-bst-nodes/"
)
public class Q783 {
    private int min;
    private TreeNode prev;
    private void inorder(TreeNode root) {
        if(root == null)
            return;
        inorder(root.left);
        if(prev != null)
            min = Math.min(min, Math.abs(root.val - prev.val));
        prev = root;
        inorder(root.right);
    }
    public int minDiffInBST(TreeNode root) {
        min = Integer.MAX_VALUE;
        prev = null;
        inorder(root);
        return min;
    }
}
