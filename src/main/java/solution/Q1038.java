package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Binary Search Tree to Greater Sum Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH_TREE,
        url = "https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/"
)
public class Q1038 {
    /**
     * Reverse inorder traversal.
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    private int pre = 0;
    public TreeNode bstToGst(TreeNode root) {
        if(root == null) {
            return null;
        }

        bstToGst(root.right);

        pre += root.val;
        root.val = pre;

        bstToGst(root.left);

        return root;
    }
}
