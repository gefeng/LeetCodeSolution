package solution.biweekly82;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Evaluate Boolean Binary Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/contest/biweekly-contest-82/problems/evaluate-boolean-binary-tree/"
)
public class Q2331 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean evaluateTree(TreeNode root) {
        if(root.left == null && root.right == null) {
            return root.val != 0;
        }

        boolean l = evaluateTree(root.left);
        boolean r = evaluateTree(root.right);

        if(root.val == 2) {
            return l || r;
        }

        return l && r;
    }
}
