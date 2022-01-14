package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Trim a Binary Search Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/trim-a-binary-search-tree/"
)
public class Q669 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        return trim(root, low, high);
    }

    private TreeNode trim(TreeNode root, int low, int high) {
        if(root == null) return null;

        TreeNode l = trim(root.left, low, high);
        TreeNode r = trim(root.right, low, high);

        if(root.val >= low && root.val <= high) {
            root.left = l;
            root.right = r;
            return root;
        } else {
            if(root.val < low) {
                return r;
            } else {
                return l;
            }
        }
    }
}
