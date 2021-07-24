package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Binary Tree Pruning",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/binary-tree-pruning/"
)
public class Q814 {
    public TreeNode pruneTree(TreeNode root) {
        if(root == null) {
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if(root.left == null && root.right == null && root.val == 0) {
            return null;
        }

        return root;
    }
}
