package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Distribute Coins in Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/distribute-coins-in-binary-tree/"
)
public class Q979 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    int ans = 0;
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int l = dfs(root.left);
        int r = dfs(root.right);
        int tot = l + r;
        ans += Math.abs(l) + Math.abs(r);

        if(root.val == 0) {
            tot--;
        } else if(root.val > 0) {
            tot += root.val - 1;
        }

        return tot;
    }
}
