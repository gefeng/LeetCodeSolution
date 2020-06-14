package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Balanced Binary Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/balanced-binary-tree/"
)
public class Q110 {
    /**注意用bottom-up可以避免重复递归**/
    private int dfsHeight(TreeNode root) {
        if(root == null)
            return 0;

        int hLeft = dfsHeight(root.left);
        int hRight = dfsHeight(root.right);

        //return hLeft > hRight ? hLeft + 1 : hRight + 1;
        if(hLeft == -1 || hRight == - 1 || Math.abs(hLeft - hRight) > 1)
            return -1;
        return Math.max(hLeft, hRight) + 1;
    }
    public boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }
}
