package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Pseudo-Palindromic Paths in a Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/"
)
public class Q1457 {
    /**
     * Since the value's range is between [1, 9] we can use a bit mask to represent current
     * path. for example, 1100010000 means there are odd number of [4,8,9] on this path.
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public int pseudoPalindromicPaths (TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int path) {
        if(root == null) {
            return 0;
        }

        path = path ^ (1 << root.val);

        if(root.left == null && root.right == null) {
            return (path & (path - 1)) == 0 ? 1 : 0;
        } else {
            int l = dfs(root.left, path);
            int r = dfs(root.right, path);

            return l + r;
        }
    }
}
