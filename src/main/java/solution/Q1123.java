package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Lowest Common Ancestor of Deepest Leaves",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/"
)
public class Q1123 {
    private class Info {
        TreeNode candidate;
        int depth;
        Info(TreeNode node, int depth) {
            this.candidate = node;
            this.depth = depth;
        }
    }
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).candidate;
    }

    private Info dfs(TreeNode root) {
        if(root == null) {
            return new Info(null, 0);
        }

        Info lInfo = dfs(root.left);
        Info rInfo = dfs(root.right);

        if(lInfo.depth == rInfo.depth) {
            return new Info(root, lInfo.depth + 1);
        }

        return lInfo.depth > rInfo.depth ?
                new Info(lInfo.candidate, lInfo.depth + 1) : new Info(rInfo.candidate, rInfo.depth + 1);
    }
}
