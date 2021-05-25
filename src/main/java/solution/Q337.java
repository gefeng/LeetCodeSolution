package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "House Robber III",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/house-robber-iii/"
)
public class Q337 {
    /*
        state:
            dp[node][0] means maximum profit by robbing house start with node down to the leaf
            dp[node][1] means sum of the maximum profit by robbing house start from left child and right child down to the leaf
        transition:
            dp[node][0] = max(dp[l][1] + dp[r][1] + val, dp[l][0] + dp[r][0])
            dp[node][1] = dp[l][0] + dp[r][0]
    */
    public int rob(TreeNode root) {
        int[] dp = postorder(root);
        return Math.max(dp[0], dp[1]);
    }

    private int[] postorder(TreeNode root) {
        if(root == null) {
            return new int[2];
        }

        int[] l = postorder(root.left);
        int[] r = postorder(root.right);

        int take = l[1] + r[1] + root.val;
        int skip = l[0] + r[0];

        return new int[] { Math.max(take, skip), skip };
    }
}
