package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Binary Tree Maximum Path Sum",
        difficulty = QDifficulty.HARD,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/binary-tree-maximum-path-sum/"
)
public class Q124 {
    int maxPathSum = Integer.MIN_VALUE;
    private int dfs(TreeNode node) {
        if(node == null)
            return 0;
        int x = dfs(node.left);
        int y = dfs(node.right);
        maxPathSum = Math.max(maxPathSum, x + y + node.val);
        return Math.max(Math.max(x, y) + node.val, 0);
    }
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxPathSum;
    }
}
