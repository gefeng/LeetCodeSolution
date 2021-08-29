package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Add One Row to Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/add-one-row-to-tree/"
)
public class Q623 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        return dfs(root, val, depth, 0, true);
    }

    private TreeNode dfs(TreeNode root, int val, int depth, int cur, boolean left) {
        if(cur != depth - 1 && root == null) {
            return null;
        }

        if(cur == depth - 1) {
            TreeNode node = new TreeNode(val);
            if(left) {
                node.left = root;
            } else {
                node.right = root;
            }

            return node;
        } else {
            root.left = dfs(root.left, val, depth, cur + 1, true);
            root.right = dfs(root.right, val, depth, cur + 1, false);

            return root;
        }
    }
}
