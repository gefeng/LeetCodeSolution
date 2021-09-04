package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Absolute Difference BST",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH_TREE,
        url = "https://leetcode.com/problems/minimum-absolute-difference-in-bst/"
)
public class Q530 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private int min = Integer.MAX_VALUE;
    private int pre = -1;
    public int getMinimumDifference(TreeNode root) {
        findMin(root);
        return min;
    }

    private void findMin(TreeNode root) {
        if(root == null) {
            return;
        }

        findMin(root.left);

        if(pre != -1) {
            min = Math.min(min, root.val - pre);
        }

        pre = root.val;

        findMin(root.right);
    }
}
