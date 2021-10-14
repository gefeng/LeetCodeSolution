package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Nodes with Even-Valued Grandparent",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/"
)
public class Q1315 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int sumEvenGrandparent(TreeNode root) {
        return dfs(root, null, null);
    }

    private int dfs(TreeNode root, TreeNode gp, TreeNode p) {
        if(root == null) {
            return 0;
        }

        int l = dfs(root.left, p, root);
        int r = dfs(root.right, p, root);

        if(gp != null && gp.val % 2 == 0) {
            return root.val + l + r;
        }
        return l + r;
    }
}
