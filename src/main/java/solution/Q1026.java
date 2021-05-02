package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Difference Between Node and Ancestor",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/"
)
public class Q1026 {
    /*
    * keep passing current max and min to children.
    * update max, min comparing to current root value.
    * if hit leaf, calculate difference, it's guaranteed this is the largest difference for this branch
    * return difference and compare to the difference returned from other branches.
    * */
    public int maxAncestorDiff(TreeNode root) {
        return  recursiveMaxDiff(root, root.val, root.val);
    }

    private int recursiveMaxDiff(TreeNode root, int max, int min) {
        if(root == null) {
            return max - min;
        }

        max = Math.max(root.val, max);
        min = Math.min(root.val, min);

        int lMaxDiff = recursiveMaxDiff(root.left, max, min);
        int rMaxDiff = recursiveMaxDiff(root.right, max, min);

        return Math.max(lMaxDiff, rMaxDiff);
    }
}
