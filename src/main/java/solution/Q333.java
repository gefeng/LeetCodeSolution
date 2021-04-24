package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest BST Subtree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/largest-bst-subtree/"
)
public class Q333 {
    public int largestBSTSubtree(TreeNode root) {
        return helper(root)[0];
    }

    // int[0] ans int[1] upper int[2] lower
    private int[] helper(TreeNode root) {
        if(root == null) {
            return new int[] { 0, Integer.MIN_VALUE, Integer.MAX_VALUE };
        }

        int[] l = helper(root.left);
        int[] r = helper(root.right);

        if(root.val > l[1] && root.val < r[2]) {
            return new int[] { l[0] + r[0] + 1, Math.max(root.val, r[1]), Math.min(root.val, l[2]) };
        }

        return new int[] { Math.max(l[0], r[0]), Integer.MAX_VALUE, Integer.MIN_VALUE };
    }
}
