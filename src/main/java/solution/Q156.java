package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Binary Tree Upside Down",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/binary-tree-upside-down/"
)
public class Q156 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null) {
            return null;
        }

        if(root.left == null && root.right == null) {
            return root;
        }

        TreeNode rootL = upsideDownBinaryTree(root.left);

        root.left.right = root;
        root.left.left = root.right;

        root.left = null;
        root.right = null;

        return rootL;
    }
}
