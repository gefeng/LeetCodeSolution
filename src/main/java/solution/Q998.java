package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Maximum Binary Tree II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/maximum-binary-tree-ii/"
)
public class Q998 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if(root == null) {
            return new TreeNode(val);
        }

        if(root.val > val) {
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        } else {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
    }
}
