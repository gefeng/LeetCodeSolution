package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest ZigZag Path in a Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/"
)
public class Q1372 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int longestZigZag(TreeNode root) {
        return dfs(root)[2] - 1;
    }

    // 0: left_path  1: right_path 2: max
    private int[] dfs(TreeNode root) {
        if(root == null) {
            return new int[3];
        }

        int[] l = dfs(root.left);
        int[] r = dfs(root.right);

        int[] ret = new int[3];
        ret[0] = l[1] + 1;
        ret[1] = r[0] + 1;
        ret[2] = Math.max(Math.max(ret[0], ret[1]), Math.max(l[2], r[2]));

        return ret;
    }
}
