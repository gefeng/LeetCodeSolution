package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Insufficient Nodes in Root to Leaf Paths",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths/"
)
public class Q1080 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        return dfs(root, limit);
    }

    private TreeNode dfs(TreeNode root, int limit) {
        if(root == null) {
            return null;
        }
        if(root.left == null && root.right == null) {
            return root.val < limit ? null : root;
        }

        root.left = dfs(root.left, limit - root.val);
        root.right = dfs(root.right, limit - root.val);

        return root.left == root.right ? null : root;
    }
}
