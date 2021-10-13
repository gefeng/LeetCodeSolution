package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Delete Leaves With a Given Value",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/delete-leaves-with-a-given-value/"
)
public class Q1325 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        return dfs(root, target);
    }

    private TreeNode dfs(TreeNode root, int target) {
        if(root == null) {
            return null;
        }

        root.left = dfs(root.left, target);
        root.right = dfs(root.right, target);

        if(root.left == null && root.right == null && root.val == target) {
            return null;
        }

        return root;
    }
}
