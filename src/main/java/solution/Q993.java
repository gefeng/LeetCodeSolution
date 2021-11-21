package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Cousins in Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/cousins-in-binary-tree/"
)
public class Q993 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean isCousins(TreeNode root, int x, int y) {
        int d1 = dfs1(root, x);
        int d2 = dfs1(root, y);
        return d1 == d2 && dfs2(root, x) != dfs2(root, y);
    }

    private int dfs1(TreeNode root, int x) {
        if(root == null) {
            return -1;
        }
        if(root.val == x) {
            return 0;
        }

        int l = dfs1(root.left, x);
        int r = dfs1(root.right, x);

        if(l != -1) {
            return l + 1;
        }
        if(r != -1) {
            return r + 1;
        }

        return -1;
    }

    private TreeNode dfs2(TreeNode root, int x) {
        if(root == null) {
            return null;
        }

        if((root.left != null && root.left.val == x) || (root.right != null && root.right.val == x)) {
            return root;
        }

        TreeNode l = dfs2(root.left, x);
        TreeNode r = dfs2(root.right, x);
        if(l != null) {
            return l;
        }
        if(r != null) {
            return r;
        }
        return null;
    }
}
