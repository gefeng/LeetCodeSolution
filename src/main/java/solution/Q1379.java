package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find a Corresponding Node of a Binary Tree in a Clone of That Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/"
)
public class Q1379 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if(original == null) {
            return null;
        }

        if(target == original) {
            return cloned;
        }

        TreeNode l = getTargetCopy(original.left, cloned.left, target);
        TreeNode r = getTargetCopy(original.right, cloned.right, target);

        return l == null ? r : l;
    }
}
