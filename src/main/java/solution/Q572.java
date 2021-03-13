package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Subtree of Another Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/subtree-of-another-tree/"
)
public class Q572 {
    // O(m * n)
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(isEqualTree(s, t))
            return true;
        if(s == null)
            return false;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isEqualTree(TreeNode x, TreeNode y) {
        if(x == null && y == null)
            return true;
        if(x == null || y == null)
            return false;
        if(x.val != y.val)
            return false;

        return isEqualTree(x.left, y.left) && isEqualTree(x.right, y.right);
    }
}
