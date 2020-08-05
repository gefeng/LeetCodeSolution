package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Univalue Path",
        difficulty = QDifficulty.EASY,
        tag = QTag.RECURSION,
        url = "https://leetcode.com/problems/longest-univalue-path/"
)
public class Q687 {
    int max = 0;
    private int findPath(TreeNode root) {
        if(root == null || (root.left == null && root.right == null))
            return 0;

        int l = findPath(root.left);
        int r = findPath(root.right);

        if(root.left != null && root.val == root.left.val)
            l++;
        else
            l = 0;

        if(root.right != null && root.val == root.right.val)
            r++;
        else
            r = 0;

        max = Math.max(max, l + r);
        return Math.max(l, r);
    }
    public int longestUnivaluePath(TreeNode root) {
        findPath(root);
        return max;
    }
}
