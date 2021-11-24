package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Univalued Binary Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/univalued-binary-tree/"
)
public class Q965 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean isUnivalTree(TreeNode root) {
        if(root == null) {
            return true;
        }

        if(root.left != null && root.left.val != root.val)  {
            return false;
        }
        if(root.right != null && root.right.val != root.val) {
            return false;
        }

        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }
}
