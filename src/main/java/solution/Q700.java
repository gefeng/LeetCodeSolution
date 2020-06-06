package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Search in a Binary Search Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/search-in-a-binary-search-tree/"
)
public class Q700 {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null || root.val == val)
            return root;
        else if(root.val > val)
            return searchBST(root.left, val);
        else
            return searchBST(root.right, val);
    }

    public TreeNode searchBSTIterative(TreeNode root, int val) {
        while(root != null && root.val != val) {
            root = val < root.val ? root.left : root.right;
        }
        return root;
    }
}
