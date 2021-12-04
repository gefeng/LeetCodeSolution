package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Increasing Order Search Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/increasing-order-search-tree/"
)
public class Q897 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public TreeNode increasingBST(TreeNode root) {
        return inorder(root)[0];
    }

    private TreeNode[] inorder(TreeNode root) {
        if(root == null) {
            return null;
        }

        TreeNode[] l = inorder(root.left);

        if(l != null) {
            l[1].right = root;
        }
        root.left = null;

        TreeNode[] r = inorder(root.right);

        if(r != null) {
            root.right = r[0];
        }

        if(l == null && r == null) return new TreeNode[] {root, root};
        if(l == null) return new TreeNode[] {root, r[1]};
        if(r == null) return new TreeNode[] {l[0], root};
        return new TreeNode[] {l[0], r[1]};
    }
}
