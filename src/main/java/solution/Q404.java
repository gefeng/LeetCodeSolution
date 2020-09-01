package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Left Leaves",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/sum-of-left-leaves/"
)
public class Q404 {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null)
            return 0;

        int sum = sumOfLeftLeaves(root.left);
        sum += sumOfLeftLeaves(root.right);

        if(root.left != null && root.left.left == null && root.left.right == null)
            sum += root.left.val;

        return sum;
    }
}
