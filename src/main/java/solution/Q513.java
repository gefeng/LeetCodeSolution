package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Bottom Left Tree Value",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/find-bottom-left-tree-value/"
)
public class Q513 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    int maxDepth = -1;
    int res = 0;
    public int findBottomLeftValue(TreeNode root) {
        preorder(root, 0);
        return res;
    }

    private void preorder(TreeNode root, int depth) {
        if(root == null) {
            return;
        }

        if(depth > maxDepth) {
            res = root.val;
            maxDepth = depth;
        }

        preorder(root.left, depth + 1);
        preorder(root.right, depth + 1);
    }
}
