package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree/"
)
public class Q1430 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return dfs(root, arr, 0);
    }

    private boolean dfs(TreeNode root, int[] arr, int cur) {
        if(root == null || cur == arr.length) return false;
        if(root.val != arr[cur]) return false;

        if(root.left == null && root.right == null && cur == arr.length - 1) return true;

        return dfs(root.left, arr, cur + 1) || dfs(root.right, arr, cur + 1);
    }
}
