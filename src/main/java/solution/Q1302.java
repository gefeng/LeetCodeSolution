package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Deepest Leaves Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/deepest-leaves-sum/"
)
public class Q1302 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int deepestLeavesSum(TreeNode root) {
        int max = dfs1(root);

        return dfs2(root, 0, max);
    }

    private int dfs1(TreeNode root) {
        if(root == null) {
            return -1;
        }

        return Math.max(dfs1(root.left), dfs1(root.right)) + 1;
    }

    private int dfs2(TreeNode root, int cur, int max) {
        if(root == null) {
            return 0;
        }

        if(cur == max) {
            return root.val;
        }

        return dfs2(root.left, cur + 1, max) + dfs2(root.right, cur + 1, max);
    }
}
