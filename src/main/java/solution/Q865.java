package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Smallest Subtree with all the Deepest Nodes",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/"
)
public class Q865 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private class Info {
        TreeNode candidate;
        int depth;
        Info(TreeNode candidate, int depth) {
            this.candidate = candidate;
            this.depth = depth;
        }
    }
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
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
