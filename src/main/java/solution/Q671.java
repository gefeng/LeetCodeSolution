package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Second Minimum Node In a Binary Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/"
)
public class Q671 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    int min1 = -1;
    int min2 = -1;
    public int findSecondMinimumValue(TreeNode root) {
        min1 = root.val;
        dfs(root);
        return min2;
    }

    private void dfs(TreeNode root) {
        if(root == null) return;

        if(root.val > min1) {
            min2 = min2 == -1 ? root.val : Math.min(min2, root.val);
        } else {
            dfs(root.left);
            dfs(root.right);
        }
    }
}
