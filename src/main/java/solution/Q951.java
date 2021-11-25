package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Flip Equivalent Binary Trees",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/flip-equivalent-binary-trees/"
)
public class Q951 {
    /**
     * Time:  O(min(M, N))
     * Space: O(min(M, N))
     * */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) {
            return true;
        }
        if(root1 == null || root2 == null) {
            return false;
        }
        if(root1.val != root2.val) {
            return false;
        }

        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) ||
                (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}
