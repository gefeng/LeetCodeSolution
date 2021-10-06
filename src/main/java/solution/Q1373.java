package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Sum BST in Binary Tree",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH_TREE,
        url = "https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/"
)
public class Q1373 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private class Info {
        int min;
        int max;
        int sum;
        int maxSum;
        boolean isBST;
        Info(int min, int max, int sum, int maxSum, boolean isBST) {
            this.min = min;
            this.max = max;
            this.sum = sum;
            this.maxSum = maxSum;
            this.isBST = isBST;
        }
    }
    public int maxSumBST(TreeNode root) {
        Info info = postOrderBST(root);
        return info.maxSum;
    }

    private Info postOrderBST(TreeNode root) {
        if(root == null)
            return new Info(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 0, true);

        Info l = postOrderBST(root.left);
        Info r = postOrderBST(root.right);

        boolean isBST = l.isBST && r.isBST && root.val > l.max && root.val < r.min;
        int min = Math.min(Math.min(l.min, r.min), root.val);
        int max = Math.max(Math.max(l.max, r.max), root.val);
        int sum = l.sum + r.sum + root.val;
        int maxSum = isBST ? Math.max(Math.max(l.maxSum, r.maxSum), sum) : Math.max(l.maxSum, r.maxSum);

        return new Info(min, max, sum, maxSum, isBST);
    }
}
