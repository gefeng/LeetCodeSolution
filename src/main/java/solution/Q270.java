package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Closest Binary Search Tree Value",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/closest-binary-search-tree-value/"
)
public class Q270 {
    public int closestValue(TreeNode root, double target) {
        TreeNode curr = root;
        int closest = curr.val;
        while(curr != null) {
            closest = Math.abs(curr.val - target) < Math.abs(closest - target) ? curr.val : closest;
            if(target < curr.val)
                curr = curr.left;
            else
                curr = curr.right;
        }
        return closest;
    }
}
