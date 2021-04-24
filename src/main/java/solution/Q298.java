package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Binary Tree Longest Consecutive Sequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/"
)
public class Q298 {
    int maxLen = 0;
    public int longestConsecutive(TreeNode root) {
        //preorder(root, null, 0);
        postorder(root);
        return maxLen;
    }

    private void preorder(TreeNode root, TreeNode parent, int length) {
        if(root == null)
            return;

        int l = 1;
        int r = 1;
        if(parent != null) {
            l = (parent.val + 1 == root.val) ? length + 1 : 1;
            r = (parent.val + 1 == root.val) ? length + 1 : 1;
        }

        maxLen = Math.max(maxLen, Math.max(l, r));

        preorder(root.left, root, l);
        preorder(root.right, root, r);
    }

    private int postorder(TreeNode root) {
        if(root == null)
            return 0;

        int l = postorder(root.left);
        int r = postorder(root.right);

        if(root.left != null && root.left.val == root.val + 1) {
            l++;
        } else {
            l = 1;
        }
        if(root.right != null && root.right.val == root.val + 1) {
            r++;
        } else {
            r = 1;
        }

        maxLen = Math.max(maxLen, Math.max(l, r));
        return Math.max(l, r);
    }
}
